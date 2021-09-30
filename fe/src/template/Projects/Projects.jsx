import React from 'react';
import MotionHoc from '../../animation/MotionHoc';

const ProjectsComponent = () => {
	return (
		<div>
			<h1>Projects</h1>
		</div>
	);
};

const Projects = MotionHoc(ProjectsComponent);

export default Projects;
