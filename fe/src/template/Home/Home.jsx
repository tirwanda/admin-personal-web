import React from 'react';
import MotionHoc from '../../animation/MotionHoc';

const HomeComponent = () => {
	return (
		<div>
			<h1>Home</h1>
		</div>
	);
};

const Home = MotionHoc(HomeComponent);

export default Home;
