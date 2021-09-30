import React, { useState } from 'react';
import styled from 'styled-components';

// Svg File
import Logo from '../../assets/logo.svg';
import Home from '../../assets/home-solid.svg';
import Team from '../../assets/social.svg';
import Calender from '../../assets/scheduled.svg';
import Projects from '../../assets/starred.svg';
import Documents from '../../assets/draft.svg';
import Power from '../../assets/power-off-solid.svg';
import { NavLink } from 'react-router-dom';

const Container = styled.div`
	position: fixed;

	.active {
		border-right: 4px solid var(--white);

		img {
			filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg)
				brightness(103%) contrast(103%);
		}
	}
`;

const Button = styled.button`
	display: flex;
	justify-content: center;
	align-items: center;

	background-color: var(--black);
	border: none;
	width: 2.5rem;
	height: 2.5rem;
	border-radius: 50%;
	margin: 0.5rem 0.5rem;
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

const ListItem = styled(NavLink)`
	width: 100%;
	padding: 1rem 0;
	cursor: pointer;
	text-decoration: none;
	color: var(--white);

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

const ProfilePicture = styled.div`
	width: ${(props) => (props.clicked ? '16rem' : '3rem')};
	height: 3rem;
	padding: 0.5rem 0.8rem;
	/* border: 2px solid var(--white); */
	border-radius: 20px;
	display: flex;
	align-items: center;
	justify-content: center;
	overflow: hidden;

	margin-left: ${(props) => (props.clicked ? '12rem' : '0')};
	background-color: var(--black);
	color: var(--white);
	transition: all 0.3s ease;

	img {
		width: 2.5rem;
		height: 2.5rem;
		border-radius: 50%;

		&:hover {
			border: 2px solid var(--grey);
			padding: 2px;
			cursor: pointer;
		}
	}
`;

const Name = styled.div`
	padding: 0 1.5rem;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	h4 {
		display: inline-block;
		font-weight: 500;
		margin: 0;
		font-size: 0.9rem;
	}

	a {
		font-size: 0.8rem;
		text-decoration: none;
		color: var(--grey);

		&:hover {
			text-decoration: underline;
		}
	}
`;

const Details = styled.div`
	display: ${(props) => (props.clicked ? 'flex' : 'none')};
	justify-content: space-between;
	align-items: center;
`;

const Logout = styled.button`
	border: none;
	width: 3rem;
	height: 3rem;
	background-color: transparent;

	img {
		width: 100%;
		height: auto;
		filter: invert(18%) sepia(77%) saturate(7295%) hue-rotate(357deg)
			brightness(100%) contrast(112%);
		transition: all 0.3s ease;

		&:hover {
			border: none;
			padding: 0;
			opacity: 0.5;
		}
	}
`;

const Sidebar = () => {
	const [click, setClick] = useState(false);
	const handleClick = () => setClick(!click);

	const [profileClick, setProfileClick] = useState(false);
	const handleProfileClick = () => setProfileClick(!profileClick);

	return (
		<Container>
			<Button clicked={click} onClick={() => handleClick()}>
				Click
			</Button>
			<SidebarContainer>
				<LogoContainer>
					<img src={Logo} alt="logo" />
				</LogoContainer>
				<SlickBar clicked={click}>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard"
					>
						<img src={Home} alt="Home" />
						<Text clicked={click}>Home</Text>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/skills"
					>
						<img src={Team} alt="Team" />
						<Text clicked={click}>Team</Text>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/achievments"
					>
						<img src={Calender} alt="Calender" />
						<Text clicked={click}>Calender</Text>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/education"
					>
						<img src={Documents} alt="Documents" />
						<Text clicked={click}>Documents</Text>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/projects"
					>
						<img src={Projects} alt="Projects" />
						<Text clicked={click}>Projects</Text>
					</ListItem>
				</SlickBar>

				<ProfilePicture clicked={profileClick}>
					<img
						onClick={() => handleProfileClick()}
						src="https://picsum.photos/200"
						alt="Profile"
					/>
					<Details clicked={profileClick}>
						<Name>
							<h4>Edho Dwi</h4>
							<a href="/#">View Profile</a>
						</Name>
						<Logout>
							<img src={Power} alt="Logout" />
						</Logout>
					</Details>
				</ProfilePicture>
			</SidebarContainer>
		</Container>
	);
};

export default Sidebar;
