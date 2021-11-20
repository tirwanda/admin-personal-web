import { NavLink } from 'react-router-dom';
import styled from 'styled-components';
import { darkTheme } from '../Themes/Themes';

const Logo = styled(NavLink)`
	display: inline-block;
	font-size: 2rem;
	color: ${(props) =>
		props.color === 'dark' ? darkTheme.text : darkTheme.body};
	font-family: 'Pacifico', cursive;
	position: fixed;
	left: 2rem;
	top: 2rem;
	text-decoration: none;
	z-index: 3;
`;

const LogoComponent = (props) => {
	return (
		<Logo to="/" color={props.theme}>
			Tirwanda
		</Logo>
	);
};

export default LogoComponent;
