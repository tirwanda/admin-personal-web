import {
	BrowserRouter as Router,
	Route,
	Switch,
	Redirect,
} from 'react-router-dom';
import { useReducer, createContext } from 'react';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';

// Context
export const AuthContext = createContext();

// Initial State
const initialState = {
	isAuthenticated: false,
	user: null,
	token: null,
};

// Reducer
const reducer = (state, action) => {
	switch (action.type) {
		case 'LOGIN':
			localStorage.setItem('user', JSON.stringify(action.payload.user));
			localStorage.setItem('token', JSON.stringify(action.payload.token));
			return {
				...state,
				isAuthenticated: true,
				user: action.payload.user,
				token: action.payload.token,
			};

		case 'LOGOUT':
			localStorage.clear();
			return {
				...state,
				isAuthenticated: false,
				user: null,
			};

		default:
			return state;
	}
};

function App() {
	const [state, dispatch] = useReducer(reducer, initialState);

	return (
		<div>
			<Router>
				<Switch>
					<AuthContext.Provider
						value={{
							state,
							dispatch,
						}}
					>
						{!state.isAuthenticated ? (
							<Redirect to={{ pathname: '/' }} />
						) : (
							<Redirect to={{ pathname: '/dashboard' }} />
						)}

						<Route exact path="/" component={LoginPage} />
						<Route path="/dashboard" component={Dashboard} />
					</AuthContext.Provider>
				</Switch>
			</Router>
		</div>
	);
}

export default App;
