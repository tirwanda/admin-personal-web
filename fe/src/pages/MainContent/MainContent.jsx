import React from 'react';
import GlobalStyle from '../../globalStyle';
import { ThemeProvider } from 'styled-components';
import { lightTheme } from '../../atoms/Themes/Themes';

const MainContent = () => {
	return (
		<GlobalStyle>
			<ThemeProvider theme={lightTheme}>Main Content</ThemeProvider>
		</GlobalStyle>
	);
};

export default MainContent;
