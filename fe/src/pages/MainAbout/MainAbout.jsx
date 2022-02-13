import React from 'react';
import styled, { keyframes, ThemeProvider } from 'styled-components';

import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import ParticleComponent from '../../atoms/ParticleComponent/ParticleComponent';
import astronout from '../../assets/images/main-content/spaceman.png';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import { darkTheme } from '../../atoms/Themes/Themes';
import BigTitle from '../../atoms/BigTitle/BigTitle';

const float = keyframes`
	0% {
		transform: translateY(-10px)
	}
	50% {
		transform: translateY(15px) translateX(15px)
	}
	100% {
		transform: translateY(-10px)
	}
`;

const Box = styled.div`
	background-color: ${(props) => props.theme.body};
	width: 100vw;
	height: 100vh;
	position: relative;
	overflow: hidden;
`;

const SpaceMan = styled.div`
	position: absolute;
	top: 10%;
	right: 5%;
	width: 20vw;

	animation: ${float} 4s ease infinite;
	img {
		width: 100%;
		height: auto;
	}
`;

const Main = styled.div`
	border: 2px solid ${(props) => props.theme.text};
	color: ${(props) => props.theme.text};
	padding: 2rem;
	width: 50vw;
	height: 60vh;
	z-index: 3;
	line-height: 1.5;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: calc(0.6rem + 1vw);
	backdrop-filter: blur(4px);
	position: absolute;
	left: calc(5rem + 5vw);
	top: 10rem;
	font-family: 'Ubuntu Mono', monospace;
	font-style: italic;
`;

const MainAbout = () => {
	return (
		<ThemeProvider theme={darkTheme}>
			<Box>
				<LogoComponent theme="dark" />
				<SocialIcons theme="dark" />
				<PowerButton />
				<ParticleComponent theme="dark" />
				<SpaceMan>
					<img src={astronout} alt="Space Man" />
				</SpaceMan>
				<Main>
					High-quality professional with 1+ years of IT experiences in
					development, implementation and testing of Client-Server,
					Web Applications using JAVA/J2EE technologies.
					<br /> <br />
					Interested in a challenging career in web application
					development environment.
					<br /> <br />
					Skilled in JAVA/J2EE, Mongodb, MySQL, Reactjs, Sveltejs,
					React Native, Nodejs/Expressjs.
				</Main>
				<BigTitle text="ABOUT" top="10%" left="5%" />
			</Box>
		</ThemeProvider>
	);
};

export default MainAbout;
