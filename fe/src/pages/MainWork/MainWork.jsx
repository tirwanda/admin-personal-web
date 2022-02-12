import React, { useRef, useEffect } from 'react';
import styled, { ThemeProvider } from 'styled-components';

import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import { darkTheme } from '../../atoms/Themes/Themes';

import { Work } from '../../data/WorkData';
import Card from '../../molecule/Card/Card';

const Box = styled.div`
	background-color: ${(props) => props.theme.body};
	height: 400vh;
	position: relative;
	overflow: hidden;
`;

const Main = styled.ul`
	position: fixed;
	top: 12rem;
	left: calc(10rem + 15vw);
	height: 40vh;
	display: flex;

	color: white;
`;

const MainWork = () => {
	const ref = useRef(null);

	useEffect(() => {
		let element = ref.current;
		const rotate = () => {
			element.style.transform = `translateX(${-window.pageYOffset}px)`;
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
				<Main>
					{Work.map((data) => (
						<Card data={data} key={data.id} />
					))}
				</Main>
			</Box>
		</ThemeProvider>
	);
};

export default MainWork;
