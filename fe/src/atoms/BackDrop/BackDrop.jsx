import styled from 'styled-components';
import { motion } from 'framer-motion';

export const BackDrop = styled(motion.div)`
	width: 160%;
	height: 550px;
	position: absolute;
	display: flex;
	flex-direction: column;
	border-radius: 50%;
	transform: rotate(60deg);
	top: -290px;
	left: -70px;
	background: rgb(241, 196, 15);
	background: linear-gradient(
		58deg,
		rgba(241, 196, 15, 1),
		rgba(243, 172, 18, 1)
	);
`;
