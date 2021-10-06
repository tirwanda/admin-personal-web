import React, { useContext } from 'react';
import { MutedLink, BoldLink } from '../../atoms/Common';
import { BoxContainer, FormContainer } from '../../atoms/Container/Container';
import { SubmitButton } from '../../atoms/Button/Button';
import { Marginer } from '../../atoms/Marginer';
import { AccountContext } from '../../context/accountContext';
import { Input } from '../../atoms/Input/Input';

function SignupForm(props) {
	const { switchToSignin } = useContext(AccountContext);

	return (
		<BoxContainer>
			<FormContainer>
				<Input placeholder="Full Name" type="text" />
				<Input placeholder="Email" type="email" />
				<Input placeholder="Password" type="password" />
				<Input placeholder="Confirm Password" type="password" />
			</FormContainer>
			<Marginer direction="vertical" margin={5} />
			<MutedLink href="#">
				Forget your password? <BoldLink href="#">Click Here</BoldLink>
			</MutedLink>
			<Marginer direction="vertical" margin="1em" />
			<SubmitButton type="submit">Signup</SubmitButton>
			<Marginer direction="vertical" margin="1em" />
			<MutedLink>
				Already have an account?{' '}
				<BoldLink href="#" onClick={switchToSignin}>
					Signin
				</BoldLink>
			</MutedLink>
		</BoxContainer>
	);
}

export default SignupForm;
