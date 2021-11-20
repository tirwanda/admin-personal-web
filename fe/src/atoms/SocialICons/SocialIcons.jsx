import { NavLink } from 'react-router-dom';
import styled from 'styled-components';
import { Facebook, Github, YouTube } from '../AllSvgs/AllSvgs';
import { darkTheme } from '../Themes/Themes';

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
	background-color: ${(props) =>
		props.color === 'dark' ? darkTheme.text : darkTheme.body};
`;

const SocialIcons = (props) => {
	return (
		<Icons>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<Github
						width={25}
						height={25}
						fill={
							props.theme === 'dark'
								? darkTheme.text
								: darkTheme.body
						}
					/>
				</NavLink>
			</div>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<Facebook
						width={25}
						height={25}
						fill={
							props.theme === 'dark'
								? darkTheme.text
								: darkTheme.body
						}
					/>
				</NavLink>
			</div>
			<div>
				<NavLink
					style={{ color: 'inherit' }}
					targer="_blank"
					to={{ pathname: 'https://github.com/tirwanda' }}
				>
					<YouTube
						width={25}
						height={25}
						fill={
							props.theme === 'dark'
								? darkTheme.text
								: darkTheme.body
						}
					/>
				</NavLink>
			</div>
			<Line color={props.theme} />
		</Icons>
	);
};

export default SocialIcons;
