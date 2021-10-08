import React from 'react';
import './home.scss';
import { Grid, Container } from '@mui/material';
import styled from 'styled-components';
import MotionHoc from '../../animation/MotionHoc';

import Navbar from '../../organism/Navbar/Navbar';

const HomeComponent = () => {
	return (
		<Container className="dashboard top-60">
			<Grid item lg={12}>
				<Navbar />
			</Grid>
			<SubContainer>
				<SectionOne>
					<ColumnOne1>
						{/* <Earnings />
						<Info /> */}
					</ColumnOne1>
					<ColumnTwo1>
						<TitleText>Your Projects</TitleText>
						{/* <Projects /> */}
					</ColumnTwo1>
				</SectionOne>
				<SectionTwo>
					<ColumnOne2>
						<InvoiceContainer>
							<TitleText>Recent Invoices</TitleText>
							{/* <Invoices /> */}
						</InvoiceContainer>
						{/* <JoinSlack /> */}
					</ColumnOne2>
					<ColumnTwo2>
						<TitleText>Recommended Project</TitleText>
						{/* <ProjectRecommendation /> */}
					</ColumnTwo2>
				</SectionTwo>
			</SubContainer>
		</Container>
	);
};

const SubContainer = styled.div`
	margin: 0.5rem 0;
	height: 80%;
	width: 100%;
	display: flex;
	flex-direction: column;
	gap: 4rem;
`;
const SectionOne = styled.div`
	display: flex;
	justify-content: space-between;
	height: 40%;
	gap: 2rem;
	width: 100%;
`;
const ColumnOne1 = styled.div`
	display: flex;
	gap: 3rem;
`;

const ColumnTwo1 = styled.div`
	display: flex;
	flex-direction: column;
	height: 115%;
	width: 100%;
`;

const TitleText = styled.h3`
	height: 20%;
	/* padding-top */
`;

const SectionTwo = styled.div`
	display: flex;
	gap: 2rem;
	height: 26vh;
`;
const ColumnOne2 = styled.div``;
const InvoiceContainer = styled.div`
	height: 60%;
`;

const ColumnTwo2 = styled.div``;
const Home = MotionHoc(HomeComponent);

export default Home;
