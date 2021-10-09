import React from 'react';
import './navbar.scss';

import { Search } from '@mui/icons-material';
import { Container, Grid } from '@mui/material';

const Navbar = () => {
	return (
		<Container className="navbar">
			<Grid container spacing={3} className="navbar-item">
				<Grid item lg={8} md={8} className="text">
					<h2>
						Good morning, <span>Edho Dwi Tirwanda</span>
					</h2>
				</Grid>

				<Grid item lg={4} md={4} className="search">
					<div className="input">
						<div className="input-icon">
							<Search />
						</div>
						<input type="text" placeholder="Search for projects" />
					</div>
				</Grid>
			</Grid>
		</Container>
	);
};

export default Navbar;
