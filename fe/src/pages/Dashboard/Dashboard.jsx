import React, { useState } from 'react';
import { fetchUserData } from '../../api/authenticationService';
import { Route, Switch, useHistory, useLocation } from 'react-router';

import { DashboardContainer } from '../../atoms/Container/Container';

import Sidebar from '../../organism/Sidebar/Sidebar';
import Home from '../../template/Home/Home';
import Projects from '../../template/Projects/Projects';
import Education from '../../template/Education/Education';
import Achievments from '../../template/Achievments/Achievments';
import Skills from '../../template/Skills/Skills';
import styled from 'styled-components';
import { AnimatePresence } from 'framer-motion';
import Navbar from '../../organism/Navbar/Navbar';

const Section = styled.div`
	width: 100vw;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;

	h1 {
		font-size: calc(2rem + 2vw);
		background: linear-gradient(to right, #803bec 30%, #1b1b1b 100%);
		-webkit-background-clip: text;
		-webkit-text-fill-color: transparent;
	}
`;

function Dashboard(props) {
	let history = useHistory();
	const location = useLocation();
	const [data, setData] = useState({});

	// React.useEffect(() => {
	// 	fetchUserData()
	// 		.then((response) => {
	// 			setData(response.data);
	// 			console.log('data', response.data);
	// 		})
	// 		.catch((e) => {
	// 			localStorage.clear();
	// 			history.push('/');
	// 		});
	// }, [history]);

	const logOut = () => {
		localStorage.clear();
		history.push('/');
	};

	return (
		<DashboardContainer>
			<Navbar />
			<Sidebar />
			<Section>
				<AnimatePresence exitBeforeEnter>
					<Switch location={location} key={location.pathname}>
						<Route exact path="/dashboard" component={Home} />
						<Route
							exact
							path="/dashboard/skills"
							component={Skills}
						/>
						<Route
							exact
							path="/dashboard/achievments"
							component={Achievments}
						/>
						<Route
							exact
							path="/dashboard/education"
							component={Education}
						/>
						<Route
							exact
							path="/dashboard/projects"
							component={Projects}
						/>
					</Switch>
				</AnimatePresence>
			</Section>
		</DashboardContainer>
	);
}

export default Dashboard;
