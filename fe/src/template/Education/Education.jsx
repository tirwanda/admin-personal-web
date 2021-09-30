import React from 'react';
import MotionHoc from '../../animation/MotionHoc';

const EducationComponent = () => {
	return (
		<div>
			<h1>Education</h1>
		</div>
	);
};

const Education = MotionHoc(EducationComponent);

export default Education;
