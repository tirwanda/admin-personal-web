import React from 'react';
import './lastProject.scss';

import MernProject from '../../assets/images/portfolios/mern.png';

const LastProject = () => {
	return (
		<div className="last-project">
			<div className="last-project-item">
				<div className="last-project-thumbnail">
					<img src={MernProject} alt="Project 1" />
				</div>
				<div className="last-project-detail">
					<h3>Staycation</h3>
					<h5>Tech: React js</h5>
				</div>
			</div>

			<div className="last-project-item">
				<div className="last-project-thumbnail">
					<img src={MernProject} alt="Project 1" />
				</div>
				<div className="last-project-detail">
					<h3>Staycation</h3>
					<h5>Tech: React js</h5>
				</div>
			</div>
			<h5>See all projects</h5>
		</div>
	);
};

export default LastProject;
