import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import MainContent from './pages/MainContent/MainContent';

function App() {
	return (
		<div>
			<Router>
				<Switch>
					<Route exact path="/" component={MainContent} />
					<Route path="/login" component={LoginPage} />
					<Route path="/dashboard" component={Dashboard} />
				</Switch>
			</Router>
		</div>
	);
}

export default App;
