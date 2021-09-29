import { AUTH_REQ, AUTH_SUCCESS, AUTH_FAILURE, AUTH_LOGOUT } from './types';

export const authenticate = () => {
	return {
		type: AUTH_REQ,
	};
};

export const authSuccess = (content) => {
	localStorage.setItem('USER_KEY', content.access_token);
	localStorage.setItem('USERNAME', content.username);
	return {
		type: AUTH_SUCCESS,
		payload: content,
	};
};

export const authFailure = (error) => {
	return {
		type: AUTH_FAILURE,
		payload: error,
	};
};

export const authLogout = () => {
	localStorage.clear();
	return {
		type: AUTH_LOGOUT,
	};
};
