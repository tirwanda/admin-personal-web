import { AUTH_REQ, AUTH_SUCCESS, AUTH_FAILURE, AUTH_LOGOUT } from '../types';

const initialState = {
	user: {},
	error: '',
	loading: false,
	isAuthenticated: false,
	// token: null
};

// Reducer
const auth = (state = initialState, action) => {
	console.log('Reducer Auth');
	switch (action.type) {
		case AUTH_REQ:
			return {
				...state,
				error: '',
				loading: true,
			};

		case AUTH_SUCCESS:
			const data = action.payload;
			return {
				...state,
				error: '',
				loading: false,
				isAuthenticated: true,
				user: data,
			};

		case AUTH_FAILURE:
			const error = action.payload;
			return {
				...state,
				loading: false,
				error: error,
			};

		case AUTH_LOGOUT:
			return {
				...state,
				isAuthenticated: false,
			};

		default:
			return state;
	}
};

export default auth;
