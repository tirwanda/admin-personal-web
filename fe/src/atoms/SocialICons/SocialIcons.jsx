import { motion } from 'framer-motion';
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

const Line = styled(motion.span)`
	width: 2px;
	height: 8rem;
	background-color: ${(props) =>
		props.color === 'dark' ? darkTheme.text : darkTheme.body};
`;

const SocialIcons = (props) => {
	return (
		<Icons>
			<motion.div
				initial={{
					transform: 'scale(0)',
				}}
				animate={{
					scale: [0, 1, 1.5, 1],
				}}
				transition={{
					type: 'spring',
					duration: 1,
					delay: 1,
				}}
			>
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
			</motion.div>
			<motion.div
				initial={{
					transform: 'scale(0)',
				}}
				animate={{
					scale: [0, 1, 1.5, 1],
				}}
				transition={{
					type: 'spring',
					duration: 1,
					delay: 1.2,
				}}
			>
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
			</motion.div>
			<motion.div
				initial={{
					transform: 'scale(0)',
				}}
				animate={{
					scale: [0, 1, 1.5, 1],
				}}
				transition={{
					type: 'spring',
					duration: 1,
					delay: 1.4,
				}}
			>
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
			</motion.div>
			<Line
				color={props.theme}
				initial={{
					height: 0,
				}}
				animate={{
					height: '8rem',
				}}
				transition={{
					type: 'spring',
					duration: 1,
					delay: 0.8,
				}}
			/>
		</Icons>
	);
};

export default SocialIcons;
