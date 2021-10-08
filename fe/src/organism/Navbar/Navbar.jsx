import React from 'react';
import './navbar.scss';
import styled from 'styled-components';

import { Search } from '@mui/icons-material';
import { Container, Grid } from '@mui/material';

const Navbar = () => {
	return (
		<Container className="navbar">
			<Grid container spacing={3} className="navbar-item">
				<Grid item lg={8} md={8} className="navbar-text">
					<h2>
						Good morning, <span>Edho Dwi Tirwanda</span>
					</h2>
				</Grid>

				<Grid item lg={4} md={4}>
					<InputContainer>
						<Icon>
							<Search />
						</Icon>
						<Input type="text" placeholder="Search for projects" />
					</InputContainer>
				</Grid>
			</Grid>
		</Container>
	);
};

const InputContainer = styled.div`
	display: flex;
`;

const Icon = styled.div`
	height: 3rem;
	width: 3rem;
	background-color: #dce4ff;
	display: flex;
	justify-content: center;
	align-items: center;
	border-top-left-radius: 0.5rem;
	border-bottom-left-radius: 0.5rem;
	svg {
		color: #555555;
	}
`;
const Input = styled.input`
	border: none;
	background-color: #dce4ff;
	border-top-right-radius: 0.5rem;
	border-bottom-right-radius: 0.5rem;
	color: #464646;

	&:focus {
		border: none;
		outline: none;
	}
`;

export default Navbar;
