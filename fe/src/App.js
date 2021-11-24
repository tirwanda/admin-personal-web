import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import LoginPage from './pages/LoginPage/LoginPage';
import Dashboard from './pages/Dashboard/Dashboard';
import MainHome from './pages/MainHome/MainHome';

import GlobalStyle from './globalStyle';
import { ThemeProvider } from 'styled-components';
import { lightTheme } from './atoms/Themes/Themes';
import MainAbout from './pages/MainAbout/MainAbout';
import MainBlog from './pages/MainBlog/MainBlog';
import MainSkills from './pages/MainSkills/MainSkills';

function App() {
	return (
		<div>
			<GlobalStyle />
			<Router>
				<ThemeProvider theme={lightTheme}>
					<Switch>
						<Route exact path="/" component={MainHome} />
						<Route exact path="/about" component={MainAbout} />
						<Route exact path="/blog" component={MainBlog} />
						<Route exact path="/skills" component={MainSkills} />
						<Route exact path="/login" component={LoginPage} />
						<Route path="/dashboard" component={Dashboard} />
					</Switch>
				</ThemeProvider>
			</Router>
		</div>
	);
}

export default App;
