import React from 'react';
import { NavLink } from 'react-router-dom';
import './mainHome.scss';
import styled from 'styled-components';
import { MainContainer } from '../../atoms/Container/Container';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';

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
	color: ${(props) => props.theme.text};
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
	color: ${(props) => props.theme.text};
	text-decoration: none;
	z-index: 1;
`;

const Skills = styled(NavLink)`
	color: ${(props) => props.theme.text};
	text-decoration: none;
	z-index: 1;
`;

const MainHome = () => {
	return (
		<MainContainer>
			<div className="mainHome-container">
				<PowerButton />
				<LogoComponent />
				<SocialIcons />

				<Contact
					target="_blank"
					to={{ pathname: 'mailto:edhodwitirwanda@gmail.com' }}
				>
					<h3>Say Hi..</h3>
				</Contact>

				<Blog to="/blog">
					<h3>Blog</h3>
				</Blog>

				<Work to="/work">
					<h3>Work</h3>
				</Work>

				<BottomBar>
					<About to="/ablout">
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
