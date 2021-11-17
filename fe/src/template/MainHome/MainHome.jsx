import React from 'react';
import './mainHome.scss';
import { MainContainer } from '../../atoms/Container/Container';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';

const MainHome = () => {
	return (
		<MainContainer>
			<div className="mainHome-container">
				<PowerButton />
				<LogoComponent />
			</div>
		</MainContainer>
	);
};

export default MainHome;
