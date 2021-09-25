import React, { useState } from 'react';
import { Button, Container } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import styled from 'styled-components';
import { fetchUserData } from '../../api/authenticationService';
import { useHistory } from 'react-router';

const MainWrapper = styled.div`
	padding-top: 40px;
`;

function Dashboard(props) {
	let history = useHistory();
	const dispatch = useDispatch();
	const [loading, setLoading] = useState(false);
	const [data, setData] = useState({});

	React.useEffect(() => {
		fetchUserData()
			.then((response) => {
				setData(response.data);
				console.log(response.data);
			})
			.catch((e) => {
				localStorage.clear();
				history.push('/');
			});
	}, []);

	const logOut = () => {
		localStorage.clear();
		history.push('/');
	};

	return (
		<Container>
			<MainWrapper>
				<h4>Hello {data[0] && `${data[0].name}`}</h4>
				{data[0] &&
					data[0].roles &&
					data[0].roles.filter((value) => value.name === 'ROLE_USER')
						.length > 0 && <Button type="variant">Add User</Button>}
				<br></br>
				<Button style={{ marginTop: '5px' }} onClick={() => logOut()}>
					Logout
				</Button>
			</MainWrapper>
		</Container>
	);
}

export default Dashboard;
