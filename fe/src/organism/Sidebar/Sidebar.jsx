import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';

import { MenuButton } from '../../atoms/Button/Button';
import {
	NavContainer,
	SidebarContainer,
	LogoContainer,
} from '../../atoms/Container/Container';
import { TextSpan, Name } from '../../atoms/Text/Text';

// Svg File
import Logo from '../../assets/svg/logo.svg';
import Home from '../../assets/svg/home-solid.svg';
import Team from '../../assets/svg/social.svg';
import Calender from '../../assets/svg/scheduled.svg';
import Projects from '../../assets/svg/starred.svg';
import Documents from '../../assets/svg/draft.svg';
import Power from '../../assets/svg/power-off-solid.svg';

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
		<NavContainer>
			<MenuButton clicked={click} onClick={() => handleClick()}>
				Click
			</MenuButton>
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
						<TextSpan clicked={click}>Home</TextSpan>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/skills"
					>
						<img src={Team} alt="Team" />
						<TextSpan clicked={click}>Team</TextSpan>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/achievments"
					>
						<img src={Calender} alt="Calender" />
						<TextSpan clicked={click}>Calender</TextSpan>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/education"
					>
						<img src={Documents} alt="Documents" />
						<TextSpan clicked={click}>Documents</TextSpan>
					</ListItem>
					<ListItem
						onClick={() => setClick(false)}
						exact
						activeClassName="active"
						to="/dashboard/projects"
					>
						<img src={Projects} alt="Projects" />
						<TextSpan clicked={click}>Projects</TextSpan>
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
		</NavContainer>
	);
};

export default Sidebar;
