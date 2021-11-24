import React from 'react';
import styled, { ThemeProvider } from 'styled-components';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import ParticleComponent from '../../atoms/ParticleComponent/ParticleComponent';
import { lightTheme } from '../../atoms/Themes/Themes';
import { Design, Develope } from '../../atoms/AllSvgs/AllSvgs';

const Box = styled.div`
	background-color: ${(props) => props.theme.body};
	width: 100vw;
	height: 100vh;
	position: relative;
	display: flex;
	justify-content: space-evenly;
	align-items: center;
`;

const Main = styled.div`
	border: 2px solid ${(props) => props.theme.text};
	color: ${(props) => props.theme.text};
	background-color: ${(props) => props.theme.body};
	padding: 2rem;
	width: 30vw;
	height: 60vh;
	z-index: 3;
	line-height: 1.5;

	font-family: 'Ubuntu Mono', monospace;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	transition: all 0.3s ease;

	&:hover {
		color: ${(props) => props.theme.body};
		background-color: ${(props) => props.theme.text};
		cursor: pointer;
	}
`;

const Title = styled.h2`
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: calc(1em + 1vw);

	${Main}:hover & {
		& > * {
			fill: ${(props) => props.theme.body};
		}
	}

	& > *:first-child {
		margin-right: 1rem;
	}
`;

const Description = styled.div`
	color: ${(props) => props.theme.text};
	font-size: calc(0.7em + 1vh);
	padding: 0.5rem 0;

	strong {
		margin-bottom: 1rem;
		text-transform: uppercase;
	}
	ul,
	p {
		margin-left: 2rem;
	}

	${Main}:hover & {
		color: ${(props) => props.theme.body};
	}
`;

const MainSkills = () => {
	return (
		<ThemeProvider theme={lightTheme}>
			<Box>
				<LogoComponent theme="light" />
				<SocialIcons theme="light" />
				<PowerButton />
				<ParticleComponent theme="light" />
				<Main>
					<Title>
						<Design width={40} height={40} />
						Front-end
					</Title>
					<Description>
						I love to create design which speaks, Keep it clean,
						minimal and simple.
					</Description>
					<Description>
						<strong>Skills</strong>
						<ul>
							<li>Web Design</li>
							<li>Mobile Apps</li>
						</ul>
					</Description>
					<Description>
						<strong>Library</strong>
						<ul>
							<li>React</li>
							<li>Material Ui</li>
						</ul>
					</Description>
				</Main>
				<Main>
					<Title>
						<Develope width={40} height={40} />
						Back-end
					</Title>
					<Description>
						I value business or brand for which i'm creating, thus i
						enjoy bringing new ideas to life.
					</Description>
					<Description>
						<strong>Skills</strong>
						<p>
							Java, Spring Boot, Node js, MySql, Postgree,
							Mongoodb
						</p>
					</Description>
					<Description>
						<strong>Tools</strong>
						<p>Vs Code, Postman, Github, IntelIj</p>
					</Description>
				</Main>
			</Box>
		</ThemeProvider>
	);
};

export default MainSkills;
