import React from 'react';
import './lastEducation.scss';

import PNJ from '../../assets/images/educations/pnj.png';
import Binus from '../../assets/images/educations/binus.jpg';
import Badge from '../../atoms/Badge/Badge';

import { Grid } from '@mui/material';

const LastEducation = () => {
	return (
		<div className="last-education">
			<Grid container spacing={2} className="last-education-content">
				<Grid item lg={2} className="avatar">
					<img src={PNJ} alt="PNJ" />
				</Grid>
				<Grid item lg={5} className="education-detail">
					<h4>Politeknik Negeri Jakarta</h4>
					<h5>Electrical Engineering</h5>
				</Grid>
				<Grid item lg={5} className="education-detail">
					<Grid container spacing={5}>
						<Grid item lg={6}>
							<Badge content="Graduated" Graduated />
						</Grid>
						<Grid item lg={6}>
							<h4>IPK: 3.41</h4>
						</Grid>
					</Grid>
				</Grid>
			</Grid>

			<Grid container spacing={2} className="last-education-content">
				<Grid item lg={2} className="avatar">
					<img src={Binus} alt="Binus" />
				</Grid>
				<Grid item lg={5} className="education-detail">
					<h4>Binus University</h4>
					<h5>Computer Science</h5>
				</Grid>
				<Grid item lg={5} className="education-detail">
					<Grid container spacing={5}>
						<Grid item lg={6}>
							<Badge content="Present" Present />
						</Grid>
						<Grid item lg={6}>
							<h4>IPK: 3.48</h4>
						</Grid>
					</Grid>
				</Grid>
			</Grid>
		</div>
	);
};

export default LastEducation;
