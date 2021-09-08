import React, { useState } from 'react';

import styled from 'styled-components';
import { motion } from 'framer-motion';

import { LoginForm } from '../../molecule/LoginForm';
import { AccountContext } from '../../context/accountContext';
import SignupForm from '../../molecule/SignupForm';

const BoxContainer = styled.div`
	width: 280px;
	min-height: 550px;
	display: flex;
	flex-direction: column;
	border-radius: 19px;
	background-color: #fff;
	box-shadow: 0 0 2px rgba(15, 15, 15, 0.28);
	position: relative;
	overflow: hidden;
`;

const TopContainer = styled.div`
	width: 100%;
	height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
	padding: 0 1.8em;
	padding-bottom: 5em;
`;

const BackDrop = styled(motion.div)`
	width: 160%;
	height: 550px;
	position: absolute;
	display: flex;
	flex-direction: column;
	border-radius: 50%;
	transform: rotate(60deg);
	top: -290px;
	left: -70px;
	background: rgb(241, 196, 15);
	background: linear-gradient(
		58deg,
		rgba(241, 196, 15, 1),
		rgba(243, 172, 18, 1)
	);
`;

const HeaderContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

const HeaderText = styled.h2`
	font-size: 1.875rem;
	font-weight: 600;
	line-height: 1.24;
	z-index: 2;
	margin: 0;
	color: #fff;
`;

const SmallText = styled.h5`
	color: #fff;
	font-weight: 500;
	font-weight: 0.6875rem;
	z-index: 2;
	margin: 0;
	margin-top: 0.375rem;
`;

const InnerContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	padding: 0px 0.8em;
`;

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
			<BoxContainer>
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
			</BoxContainer>
		</AccountContext.Provider>
	);
}

export default AccountBox;