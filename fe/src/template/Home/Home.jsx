import React from 'react';
import './home.scss';
import { Grid, Container } from '@mui/material';
import MotionHoc from '../../animation/MotionHoc';

import Navbar from '../../organism/Navbar/Navbar';
import Earnings from '../../molecule/Visitor/Visitor';
import Info from '../../molecule/Info/Info';
import LastProject from '../../molecule/LastProject/LastProject';

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
					<h3>Your Projects</h3>
					<LastProject />
				</Grid>
			</Grid>
		</Container>
	);
};

const Home = MotionHoc(HomeComponent);

export default Home;
