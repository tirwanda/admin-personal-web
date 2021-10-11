import React from 'react';
import './about.scss';

import Profile from '../../assets/images/profile.jpg';
import Badge from '../../atoms/Badge/Badge';

const About = () => {
	return (
		<div className="home-about">
			<div className="home-about-detail">
				<div className="home-about-detail-info">
					<div className="avatar">
						<img src={Profile} alt="Profile" />
					</div>
					<div className="detail-text">
						<h3>Edho Dwi Tirwanda</h3>
						<h5>Software Engineer</h5>
					</div>
					<Badge content="Back-End" />
				</div>
				<div className="home-about-detail-desc">
					<p>
						High-quality professional with 1+ years of IT
						experiences in development, implementation and testing
						of Client-Server, Web Applications using JAVA/J2EE
						technologies. Interested in a challenging career in web
						application development environment. Skilled in
						JAVA/J2EE, Mongodb, MySQL, Reactjs, Sveltejs, React
						Native, Nodejs/Expressjs.
					</p>
				</div>
			</div>
		</div>
	);
};

export default About;
