import React from 'react';
import GlobalStyle from '../../globalStyle';
import { ThemeProvider } from 'styled-components';
import { lightTheme, darkTheme } from '../../atoms/Themes/Themes';
import { Route, Switch } from 'react-router';
import MainHome from '../../template/MainHome/MainHome';
import MainAbout from '../../template/MainAbout/MainAbout';

const MainContent = () => {
	return (
		<>
			<GlobalStyle />
			<ThemeProvider theme={lightTheme}>
				<Switch>
					<Route exact path="/" component={MainHome} />
					<Route path="/about" component={MainAbout} />
				</Switch>
			</ThemeProvider>
		</>
	);
};

export default MainContent;
