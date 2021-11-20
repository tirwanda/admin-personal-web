import React from 'react';
import styled from 'styled-components';

import me from '../../assets/images/main-content/profile-img.png';
const Box = styled.div`
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);

	width: 60vw;
	height: 55vh;
	display: flex;

	background: linear-gradient(
				to right,
				${(props) => props.theme.body} 50%,
				${(props) => props.theme.text} 50%
			)
			bottom,
		linear-gradient(
				to right,
				${(props) => props.theme.body} 50%,
				${(props) => props.theme.text} 50%
			)
			top;
	background-repeat: no-repeat;
	background-size: 100% 2px;
	border-left: 2px solid ${(props) => props.theme.body};
	border-right: 2px solid ${(props) => props.theme.text};
	z-index: 1;
`;

const SubBox = styled.div`
	width: 50%;
	position: relative;
	display: flex;

	.pic {
		position: absolute;
		bottom: 0;
		left: 50%;
		transform: translate(-50%, 0);
		width: 100%;
		height: auto;
	}
`;

const Text = styled.div`
	font-size: calc(0.75em + 1.5vw);
	color: ${(props) => props.theme.body};
	padding: 5rem 2rem;
	cursor: pointer;

	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	/* align-items: center; */

	h3 {
		font-size: 2rem;
	}

	& > *:last-child {
		color: ${(props) => `rgba(${props.theme.body}, 0,6)`};
		font-size: calc(0.3rem + 1.5vw);
		font-weight: 300;
	}
`;

const Intro = (props) => {
	return (
		<Box>
			<SubBox>
				<Text>
					<h2>Hi,</h2>
					<h3>I'm Edho D. Tirwanda</h3>
					<h6>and I'm a Sofware Engineer</h6>
				</Text>
			</SubBox>
			<SubBox>
				<div>
					<img className="pic" src={me} alt="Profile Pcture" />
				</div>
			</SubBox>
		</Box>
	);
};

export default Intro;
