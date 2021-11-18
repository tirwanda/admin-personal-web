import React from 'react';
import './mainHome.scss';
import { MainContainer } from '../../atoms/Container/Container';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';

const MainHome = () => {
	return (
		<MainContainer>
			<div className="mainHome-container">
				<PowerButton />
				<LogoComponent />
				<SocialIcons />
			</div>
		</MainContainer>
	);
};

export default MainHome;
