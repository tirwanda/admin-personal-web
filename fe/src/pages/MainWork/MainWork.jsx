import React, { useRef, useEffect } from 'react';
import styled, { ThemeProvider } from 'styled-components';

import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import { darkTheme } from '../../atoms/Themes/Themes';
import { motion } from 'framer-motion';

import { Work } from '../../data/WorkData';
import Card from '../../molecule/Card/Card';
import { YinYang } from '../../atoms/AllSvgs/AllSvgs';
import BigTitle from '../../atoms/BigTitle/BigTitle';

const Box = styled.div`
	background-color: ${(props) => props.theme.body};
	height: 400vh;
	position: relative;
	display: flex;
	align-items: center;
`;

const Main = styled(motion.ul)`
	position: fixed;
	top: 12rem;
	left: calc(10rem + 15vw);
	height: 40vh;
	display: flex;

	color: white;
`;

const Rotate = styled.span`
	display: block;
	position: fixed;
	right: 1rem;
	bottom: 1rem;
	width: 80px;
	height: 80px;
	z-index: 1;
`;

const container = {
	hidden: { opacity: 0 },
	show: {
		opacity: 1,

		transitions: {
			staggerChildren: 0.5,
			duration: 0.5,
		},
	},
};

const MainWork = () => {
	const ref = useRef(null);
	const yinyang = useRef(null);

	useEffect(() => {
		let element = ref.current;
		const rotate = () => {
			element.style.transform = `translateX(${-window.pageYOffset}px)`;
			return (yinyang.current.style.transform =
				`rotate(` + -window.pageYOffset + 'deg)');
		};
		window.addEventListener('scroll', rotate);
		return () => window.removeEventListener('scroll', rotate);
	}, []);

	return (
		<ThemeProvider theme={darkTheme}>
			<Box>
				<LogoComponent theme="dark" />
				<SocialIcons theme="dark" />
				<PowerButton />
				<Main
					ref={ref}
					variants={container}
					initial="hidden"
					animate="show"
				>
					{Work.map((data) => (
						<Card data={data} key={data.id} />
					))}
				</Main>
			</Box>

			<Rotate ref={yinyang}>
				<YinYang width={80} height={80} fill={darkTheme.text} />
			</Rotate>
			<BigTitle text="WORK" top="10%" right="20%" />
		</ThemeProvider>
	);
};

export default MainWork;
