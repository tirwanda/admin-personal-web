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
import { AnimatePresence } from 'framer-motion';
import { Grid } from '@mui/material';

import { connect } from 'react-redux';
import { authGetUserData } from '../../redux/authActions';

function Dashboard(props) {
	let history = useHistory();
	const location = useLocation();
	const [data, setData] = useState({});

	React.useEffect(() => {
		fetchUserData()
			.then((response) => {
				setData(response.data);
				// props.getUserData(response.data);
				console.log('data', response.data);
			})
			.catch((e) => {
				localStorage.clear();
				history.push('/');
			});
	}, [history]);

	const logOut = () => {
		localStorage.clear();
		history.push('/');
	};

	props.getUserData(data);

	return (
		<DashboardContainer>
			{props.user && (
				<Grid container>
					<Grid item lg={1}>
						<Sidebar />
					</Grid>

					<Grid item lg={11} sm={11} md={11}>
						<AnimatePresence exitBeforeEnter>
							<Switch location={location} key={location.pathname}>
								<Route
									exact
									path="/dashboard"
									component={Home}
								/>
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
					</Grid>
				</Grid>
			)}
		</DashboardContainer>
	);
}

const mapStateToProps = ({ auth }) => {
	console.log('from dashboard: ', auth);
	return {
		user: auth.user,
	};
};

const mapDispatchToProps = (dispatch) => {
	return {
		getUserData: (data) => dispatch(authGetUserData(data)),
	};
};

export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);
