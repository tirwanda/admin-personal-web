import React, { useState } from 'react';

import LoginForm from '../../molecule/LoginForm';
import { AccountContext } from '../../context/accountContext';
import SignupForm from '../../molecule/SignupForm';

import {
	AccountBoxContainer,
	TopContainer,
	HeaderContainer,
	InnerContainer,
} from '../../atoms/Container/Container';

import { BackDrop } from '../../atoms/BackDrop/BackDrop';
import { HeaderText, SmallText } from '../../atoms/Text/Text';

const backDropVariants = {
	expanded: {
		width: '233%',
		height: '1050px',
		borderRadius: '20%',
		transform: 'rotate(60deg)',
	},
	collapsed: {
		width: '160%',
		height: '550px',
		borderRadius: '50%',
		transform: 'rotate(60deg)',
	},
};

const expandingTransition = {
	type: 'spring',
	duration: 2.3,
	stiffness: 30,
};

function AccountBox(props) {
	const [isExpanded, setExpanded] = useState(false);
	const [active, setActive] = useState('signin');

	const playExpandingAnimation = () => {
		setExpanded(true);
		setTimeout(() => {
			setExpanded(false);
		}, expandingTransition.duration * 1000 - 1500);
	};

	const switchToSignup = () => {
		playExpandingAnimation();
		setTimeout(() => {
			setActive('signup');
		}, 400);
	};

	const switchToSignin = () => {
		playExpandingAnimation();
		setTimeout(() => {
			setActive('signin');
		}, 400);
	};

	const contextValue = { switchToSignup, switchToSignin };

	return (
		<AccountContext.Provider value={contextValue}>
			<AccountBoxContainer>
				<TopContainer>
					<BackDrop
						transition={expandingTransition}
						initial={false}
						variants={backDropVariants}
						animate={isExpanded ? 'expanded' : 'collapsed'}
					/>
					{active === 'signin' && (
						<HeaderContainer>
							<HeaderText>Welcome</HeaderText>
							<HeaderText>Back</HeaderText>
							<SmallText>Please Sign-in to continue</SmallText>
						</HeaderContainer>
					)}
					{active === 'signup' && (
						<HeaderContainer>
							<HeaderText>Create</HeaderText>
							<HeaderText>Account</HeaderText>
							<SmallText>Please Sign-up to continue</SmallText>
						</HeaderContainer>
					)}
				</TopContainer>
				<InnerContainer>
					{active === 'signin' && <LoginForm />}
					{active === 'signup' && <SignupForm />}
				</InnerContainer>
			</AccountBoxContainer>
		</AccountContext.Provider>
	);
}

export default AccountBox;
