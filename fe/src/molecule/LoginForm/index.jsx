import React, { useContext, useState } from 'react';
import qs from 'query-string';

import {
	BoxContainer,
	FormContainer,
	Input,
	SubmitButton,
	MutedLink,
	BoldLink,
} from '../../atoms/Common';
import { Marginer } from '../../atoms/Marginer';
import { AccountContext } from '../../context/accountContext';

import { connect } from 'react-redux';
import {
	authenticate,
	authFailure,
	authSuccess,
} from '../../redux/authActions';
import { userLogin } from '../../api/authenticationService';

const LoginForm = ({ loading, error, ...props }) => {
	const { switchToSignup } = useContext(AccountContext);

	const initialState = {
		username: '',
		password: '',
	};

	const [value, setValue] = useState(initialState);

	const handleInputChange = (event) => {
		setValue({
			...value,
			[event.target.name]: event.target.value,
		});
	};

	const handleFormSubmit = (event) => {
		event.preventDefault();
		props.authenticate();

		userLogin(qs.stringify(value))
			.then((response) => {
				console.log(response);

				if (response.status === 200) {
					props.setUser(response.data);
					props.history.push('/dashboard');
					console.log('Local Storage: ', localStorage);
				} else {
					props.loginFailure('Username or password is invalid');
				}
			})
			.catch((err) => {
				console.log('Error Message: ', err);
				if (err && err.response) {
					switch (err.response.status) {
						case 401:
							console.log('401 status');
							props.loginFailure(
								'Authentication Failed.Bad Credentials'
							);
							break;
						default:
							props.loginFailure(
								'Something Wrong!Please Try Again'
							);
					}
				} else {
					props.loginFailure('Something Wrong!Please Try Again');
				}
			});
	};

	return (
		<BoxContainer>
			{value.errorMessage && (
				<div className="alert alert-danger" role="alert">
					{value.errorMessage}
				</div>
			)}

			<FormContainer>
				<Input
					placeholder="Username"
					type="text"
					name="username"
					value={value.username}
					onChange={handleInputChange}
				/>

				<Input
					placeholder="Password"
					type="password"
					name="password"
					value={value.password}
					onChange={handleInputChange}
				/>
			</FormContainer>

			<Marginer direction="vertical" margin={5} />
			<MutedLink href="#">
				Forget your password? <BoldLink href="#">Click Here</BoldLink>
			</MutedLink>
			<Marginer direction="vertical" margin="1em" />

			<SubmitButton
				type="submit"
				disabled={value.isSubmit}
				onClick={handleFormSubmit}
			>
				{value.isSubmit ? 'Loading...' : 'Signin'}
			</SubmitButton>

			<Marginer direction="vertical" margin="1em" />
			<MutedLink href="3">
				Don't have an account?{' '}
				<BoldLink href="#" onClick={switchToSignup}>
					SignUp
				</BoldLink>
			</MutedLink>
		</BoxContainer>
	);
};

const mapStateToProps = ({ auth }) => {
	console.log('state: ', auth);
	return {
		loading: auth.loading,
		error: auth.error,
	};
};

const mapDispatchToProps = (dispatch) => {
	return {
		authenticate: () => dispatch(authenticate()),
		setUser: (data) => dispatch(authSuccess(data)),
		loginFailure: (message) => dispatch(authFailure(message)),
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
