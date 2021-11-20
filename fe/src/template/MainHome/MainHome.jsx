import React from 'react';
import { NavLink } from 'react-router-dom';
import './mainHome.scss';
import styled, { keyframes } from 'styled-components';

import { MainContainer } from '../../atoms/Container/Container';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import { YinYang } from '../../atoms/AllSvgs/AllSvgs';
import { useState } from 'react';

const rotate = keyframes`
	from {
		transform: rotate(0);
	}
	to {
		transform: rotate(360deg);
	}
`;

const Contact = styled(NavLink)`
	color: ${(props) => props.theme.text};
	position: absolute;
	top: 2rem;
	right: calc(1rem + 2vw);
	text-decoration: none;
	z-index: 1;
`;

const Blog = styled(NavLink)`
	color: ${(props) => props.theme.text};
	position: absolute;
	top: 50%;
	right: calc(1rem + 2vw);
	transform: rotate(90deg) translate(-50%, -50%);
	text-decoration: none;
	z-index: 1;
`;

const Work = styled(NavLink)`
	color: ${(props) => (props.click ? props.theme.body : props.theme.text)};
	position: absolute;
	top: 50%;
	left: calc(1rem + 2vw);
	transform: translate(-50%, -50%) rotate(-90deg);
	text-decoration: none;
	z-index: 1;
`;

const BottomBar = styled.div`
	position: absolute;
	bottom: 1rem;
	left: 0;
	right: 0;
	width: 100%;
	display: flex;
	justify-content: space-evenly;
`;

const About = styled(NavLink)`
	color: ${(props) => (props.click ? props.theme.body : props.theme.text)};
	text-decoration: none;
	z-index: 1;
`;

const Skills = styled(NavLink)`
	color: ${(props) => props.theme.text};
	text-decoration: none;
	z-index: 1;
`;

const Center = styled.button`
	position: absolute;
	top: ${(props) => (props.click ? '85%' : '50%')};
	left: ${(props) => (props.click ? '92%' : '50%')};
	transform: translate(-50%, -50%);
	border: none;
	background-color: transparent;
	cursor: pointer;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	transition: all 1s ease;

	& > :first-child {
		animation: ${rotate} infinite 1.5s linear;
	}

	& > :last-child {
		padding-top: 1rem;
	}
`;

const DarkDiv = styled.div`
	position: absolute;
	top: 0;
	right: 50%;
	background-color: #000;
	width: ${(props) => (props.click ? '100%' : '0%')};
	height: ${(props) => (props.click ? '100%' : '0%')};
	z-index: 1;
	transition: height 0.5s ease, width 1s ease 0.5s;
`;

const MainHome = () => {
	const [click, setClick] = useState(false);

	const handleClick = () => setClick(!click);

	return (
		<MainContainer>
			<div className="mainHome-container">
				<DarkDiv click={click} />
				<PowerButton />
				<LogoComponent theme={click ? 'dark' : 'light'} />
				<SocialIcons theme={click ? 'dark' : 'light'} />

				<Center click={click}>
					<YinYang
						onClick={() => handleClick()}
						width={click ? 120 : 200}
						height={click ? 120 : 200}
						fill="currentColor"
					/>
					{click ? '' : <span>Click here..</span>}
				</Center>

				<Contact
					target="_blank"
					to={{ pathname: 'mailto:edhodwitirwanda@gmail.com' }}
				>
					<h3>Say Hi..</h3>
				</Contact>

				<Blog to="/blog">
					<h3>Blog</h3>
				</Blog>

				<Work click={click} to="/work">
					<h3>Work</h3>
				</Work>

				<BottomBar>
					<About click={click} to="/ablout">
						<h2>About me.</h2>
					</About>
					<Skills to="/Skills">
						<h2>Skills.</h2>
					</Skills>
				</BottomBar>
			</div>
		</MainContainer>
	);
};

export default MainHome;
