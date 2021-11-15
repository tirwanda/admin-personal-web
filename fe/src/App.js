import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import MainContent from './pages/MainContent/MainContent';
import MainHome from './template/MainHome/MainHome';

import GlobalStyle from './globalStyle';
import { ThemeProvider } from 'styled-components';
import { lightTheme } from './atoms/Themes/Themes';
import MainAbout from './template/MainAbout/MainAbout';

function App() {
	return (
		<div>
			<GlobalStyle />
			<Router>
				<Switch>
					<ThemeProvider theme={lightTheme}>
						<Route exact path="/" component={MainHome} />
						<Route exact path="/about" component={MainAbout} />
					</ThemeProvider>
					<Route exact path="/login" component={LoginPage} />
					<Route path="/dashboard" component={Dashboard} />
				</Switch>
			</Router>
		</div>
	);
}

export default App;
