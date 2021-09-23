import axios from 'axios';

const getToken = () => {
	return localStorage.getItem('USER_KEY');
};

const config = {
	headers: {
		'Content-Type': 'application/x-www-form-urlencoded',
	},
};

export const userLogin = (data) => {
	return axios.post(`http://localhost:8080/api/login`, data, config);
};

export const fetchUserData = (authRequest) => {
	return axios({
		method: 'GET',
		url: `${process.env.hostUrl || 'http://localhost:8080'}/api/users`,
		headers: {
			Authorization: 'Bearer ' + getToken(),
		},
	});
};
