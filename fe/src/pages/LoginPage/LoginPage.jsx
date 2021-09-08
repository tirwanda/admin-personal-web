import React from 'react';
import styled from 'styled-components';

import AccountBox from '../../template/AccountBox';

const AppContainer = styled.div`
	width: 100%;
	height: 100vh;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
`;

function LoginPage() {
	return (
		<AppContainer>
			<AccountBox />
		</AppContainer>
	);
}

export default LoginPage;
