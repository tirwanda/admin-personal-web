import React from 'react';
import './home.scss';
import { Grid, Container } from '@mui/material';
import MotionHoc from '../../animation/MotionHoc';

import Navbar from '../../organism/Navbar/Navbar';
import Earnings from '../../molecule/Visitor/Visitor';
import Info from '../../molecule/Info/Info';
import LastProject from '../../molecule/LastProject/LastProject';
import LastEducation from '../../molecule/LastEducation/LastEducation';
import About from '../../molecule/About/About';

const HomeComponent = () => {
	return (
		<Container className="dashboard top-60">
			<Grid item lg={12}>
				<Navbar />
			</Grid>

			<Grid container spacing={3} className="dashboard-top-section">
				<Grid item lg={7}>
					<Grid container>
						<Grid item lg={6}>
							<Earnings />
						</Grid>
						<Grid item lg={6}>
							<Info />
						</Grid>
					</Grid>
				</Grid>
				<Grid item lg={5} className="dashboard-top-section-project">
					<h3>Last Projects</h3>
					<LastProject />
				</Grid>
			</Grid>

			<Grid container spacing={3} className="dashboard-bottom-section">
				<Grid item lg={7} className="dashboard-bottom-section-left">
					<div className="last-education-container">
						<h3>Last Education</h3>
						<LastEducation />
					</div>
				</Grid>
				<Grid item lg={5} className="dashboard-bottom-section-right">
					<div className="about-container">
						<h3>About me</h3>
						<About />
					</div>
				</Grid>
			</Grid>
		</Container>
	);
};

const Home = MotionHoc(HomeComponent);

export default Home;
