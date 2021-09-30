import React from 'react';
import MotionHoc from '../../animation/MotionHoc';

const AchievmentsComponent = () => {
	return (
		<div>
			<h1>Achievment</h1>
		</div>
	);
};

const Achievments = MotionHoc(AchievmentsComponent);

export default Achievments;
