import React, { useContext, useState } from 'react';
import axios from 'axios';
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
import { AuthContext } from '../../App';

const url = 'http://localhost:8080';

export function LoginForm(props) {
	const { switchToSignup } = useContext(AccountContext);
	const { dispatch } = useContext(AuthContext);

	const initialState = {
		username: '',
		password: '',
		isSubmit: false,
		errorMessage: null,
	};

	const [data, setData] = useState(initialState);

	const handleInputChange = (event) => {
		setData({
			...data,
			[event.target.name]: event.target.value,
		});

		console.log({ data });
	};

	const handleFormSubmit = (event) => {
		event.preventDefault();

		setData({
			...data,
			isSubmit: true,
			errorMessage: null,
		});

		const requestBody = {
			username: data.username,
			password: data.password,
		};

		const config = {
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
		};

		axios
			.post(`${url}/api/login`, qs.stringify(requestBody), config)
			.then((res) => {
				console.log(res);

				if (data.access_token) {
					dispatch({
						type: 'LOGIN',
						payload: res.data,
					});
				} else {
					setData({
						...data,
						isSubmit: false,
						errorMessage: res.data.errorMessage,
					});
				}
				throw res;
			});
	};

	return (
		<BoxContainer>
			{data.errorMessage && (
				<div className="alert alert-danger" role="alert">
					{data.errorMessage}
				</div>
			)}

			<FormContainer>
				<Input
					placeholder="Username"
					type="text"
					name="username"
					value={data.username}
					onChange={handleInputChange}
				/>

				<Input
					placeholder="Password"
					type="password"
					name="password"
					value={data.password}
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
				disabled={data.isSubmit}
				onClick={handleFormSubmit}
			>
				{data.isSubmit ? 'Loading...' : 'Signin'}
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
}
