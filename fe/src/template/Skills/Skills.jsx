import React from 'react';
import MotionHoc from '../../animation/MotionHoc';

const SkillsComponent = () => {
	return (
		<div>
			<h1>Skill</h1>
		</div>
	);
};

const Skills = MotionHoc(SkillsComponent);

export default Skills;
