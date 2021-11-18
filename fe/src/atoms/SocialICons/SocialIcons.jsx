import react from 'react';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';
import { Facebook, Github, YouTube } from '../AllSvgs/AllSvgs';

const Icons = styled.div`
	display: flex;
	flex-direction: column;
	align-items: center;
	position: fixed;
	bottom: 0;
	left: 2rem;
	z-index: 3;

	& > *:not(:last-child) {
		margin: 0.5rem 0;
	}
`;

const Line = styled.span`
	width: 2px;
	height: 8rem;
	background-color: ${(props) => props.theme.text};
`;

const SocialIcons = () => {
	return (
		<Icons>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<Github width={25} height={25} fill="currentColor" />
				</NavLink>
			</div>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<Facebook width={25} height={25} fill="currentColor" />
				</NavLink>
			</div>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<YouTube width={25} height={25} fill="currentColor" />
				</NavLink>
			</div>
			<Line />
		</Icons>
	);
};

export default SocialIcons;
