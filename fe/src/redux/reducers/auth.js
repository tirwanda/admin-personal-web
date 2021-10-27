import {
	AUTH_REQ,
	AUTH_SUCCESS,
	AUTH_FAILURE,
	AUTH_LOGOUT,
	GET_USER_DATA,
	UPDATE_USER_DATA,
} from '../types';

const initialState = {
	user: {},
	error: '',
	loading: false,
	isAuthenticated: false,
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

		case GET_USER_DATA:
			const user = action.payload;
			return {
				...state,
				user: user,
			};

		case UPDATE_USER_DATA:
			const userUpdate = action.payload;
			return {
				...state,
				user: userUpdate,
			};

		default:
			return state;
	}
};

export default auth;
