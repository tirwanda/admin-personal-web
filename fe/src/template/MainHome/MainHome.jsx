import React from 'react';
import './mainHome.scss';
import { MainContainer } from '../../atoms/Container/Container';
import PowerButton from '../../atoms/PowerButton/PowerButton';

const MainHome = () => {
	return (
		<MainContainer>
			<div className="mainHome-container">
				<PowerButton />
			</div>
		</MainContainer>
	);
};

export default MainHome;
