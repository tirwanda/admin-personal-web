import React, { useState } from 'react';
import styled from 'styled-components';

// Svg File
import Logo from '../../assets/logo.svg';
import Home from '../../assets/home-solid.svg';
import Team from '../../assets/social.svg';
import Calender from '../../assets/scheduled.svg';
import Projects from '../../assets/starred.svg';
import Documents from '../../assets/draft.svg';
import Logout from '../../assets/power-off-solid.svg';

const Button = styled.button`
	display: flex;
	justify-content: center;
	align-items: center;

	background-color: var(--black);
	border: none;
	width: 2.5rem;
	height: 2.5rem;
	border-radius: 50%;
	margin: 0.5rem 0;
	cursor: pointer;

	position: relative;

	&::before,
	&::after {
		content: '';
		background-color: var(--white);
		height: 2px;
		width: 1rem;
		position: absolute;
		transition: all 0.3s ease-in-out;
	}

	&::before {
		top: ${(props) => (props.clicked ? '1.5' : '1rem')};
		transform: ${(props) =>
			props.clicked ? 'rotate(135deg)' : 'rotate(0)'};
	}

	&::after {
		top: ${(props) => (props.clicked ? '1.2' : '1.5rem')};
		transform: ${(props) =>
			props.clicked ? 'rotate(-135deg)' : 'rotate(0)'};
	}
`;

const SidebarContainer = styled.div`
	background-color: var(--black);
	width: 3.5rem;
	height: 80vh;
	margin-top: 1rem;
	border-radius: 0 30px 30px 0;
	padding: 1rem 0;

	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;

	position: relative;
`;

const LogoContainer = styled.div`
	width: 2.5rem;

	img {
		width: 100%;
		height: auto;
	}
`;

const SlickBar = styled.ul`
	color: var(--white);
	list-style: none;
	display: flex;
	flex-direction: column;
	align-items: center;
	background-color: var(--black);
	padding: 2rem 0;

	position: absolute;
	top: 5rem;
	left: 0;

	width: ${(props) => (props.clicked ? '12rem' : '3.5rem')};
	transition: all 0.3s ease-in-out;
	border-radius: 0 30px 30px 0;
`;

const ListItem = styled.li`
	width: 100%;
	padding: 1rem 0;
	cursor: pointer;

	display: flex;
	padding-left: 1rem;

	&:hover {
		border-right: 4px solid var(--white);

		img {
			filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg)
				brightness(103%) contrast(103%);
		}
	}

	img {
		width: 1.2rem;
		height: auto;
		filter: invert(82%) sepia(22%) saturate(170%) hue-rotate(162deg)
			brightness(85%) contrast(82%);
	}
`;

const Text = styled.span`
	width: ${(props) => (props.clicked ? '100%' : '0')};
	overflow: hidden;
	margin-left: ${(props) => (props.clicked ? '1.5rem' : '0')};
	transition: all 0.3s ease;
`;

const Sidebar = () => {
	const [click, setClick] = useState(false);
	const handleClick = () => setClick(!click);

	return (
		<>
			<Button clicked={click} onClick={() => handleClick()}>
				Click
			</Button>
			<SidebarContainer>
				<LogoContainer>
					<img src={Logo} alt="logo" />
				</LogoContainer>
				<SlickBar clicked={click}>
					<ListItem>
						<img src={Home} alt="Home" />
						<Text clicked={click}>Home</Text>
					</ListItem>
					<ListItem>
						<img src={Team} alt="Team" />
						<Text clicked={click}>Team</Text>
					</ListItem>
					<ListItem>
						<img src={Calender} alt="Calender" />
						<Text clicked={click}>Calender</Text>
					</ListItem>
					<ListItem>
						<img src={Documents} alt="Documents" />
						<Text clicked={click}>Documents</Text>
					</ListItem>
					<ListItem>
						<img src={Projects} alt="Projects" />
						<Text clicked={click}>Projects</Text>
					</ListItem>
				</SlickBar>

				<div>
					<img src="https://picsum.photos/200" alt="Profile" />
					<div>
						<div>
							<h4>Edho Dwi</h4>
							<a href="/#">View Profile</a>
						</div>
						<button>
							<img src={Logout} alt="Logout" />
						</button>
					</div>
				</div>
			</SidebarContainer>
		</>
	);
};

export default Sidebar;
