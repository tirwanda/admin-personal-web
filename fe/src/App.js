import { Router, Route, Switch } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';

const history = createBrowserHistory();

function App() {
	return (
		<div>
			<Router history={history}>
				<Switch>
					<Route exact path="/" component={LoginPage} />
					<Route path="/dashboard" component={Dashboard} />
				</Switch>
			</Router>
		</div>
	);
}

export default App;
