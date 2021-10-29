import axios from 'axios';

const getToken = () => {
	return localStorage.getItem('USER_KEY');
};

const config = {
	headers: {
		'Content-Type': 'application/json',
		Authorization: 'Bearer ' + getToken(),
	},
};

export const updateProfile = (data) => {
	// return axios.put(`http://localhost:8080/api/user/update`, data, config);
	return axios({
		method: 'PUT',
		url: `${
			process.env.hostUrl || 'http://localhost:8080'
		}/api/user/update`,
		data,
		config,
	});
};

export const uploadImage = (data, userId) => {
	return axios({
		method: 'POST',
		url: `${
			process.env.hostUrl || 'http://localhost:8080'
		}/api/upload/image/${userId}`,
		data,
		headers: {
			'Content-Type': 'multipart/form-data',
			Authorization: 'Bearer ' + getToken(),
		},
	});
};
