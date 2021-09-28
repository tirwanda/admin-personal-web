import React, { useState } from 'react';
import { fetchUserData } from '../../api/authenticationService';
import { useHistory } from 'react-router';

import Sidebar from '../../organism/Sidebar/Sidebar';

function Dashboard(props) {
	let history = useHistory();
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
		<div className="dashboard">
			<Sidebar />
		</div>
	);
}

export default Dashboard;
