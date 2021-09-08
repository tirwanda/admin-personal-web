import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';

function App() {
	return (
		<div>
			<Router>
				<Switch>
					<Route exact path="/" component={LoginPage} />
					{/* <Route exact path="/" component={Resume} /> */}
				</Switch>
			</Router>
		</div>
	);
}

export default App;
