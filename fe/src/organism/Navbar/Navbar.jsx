import React from 'react';
import styled from 'styled-components';

import { Search } from '@mui/icons-material';

const Navbar = () => {
	return (
		<NavbarContainer>
			<TextH1>Good Morning</TextH1>
			<span>Edho Dwi</span>
			<InputContainer>
				<Icon>
					<Search />
				</Icon>
			</InputContainer>
		</NavbarContainer>
	);
};

const NavbarContainer = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	height: 10%;
	@media screen and (min-width: 320px) and (max-width: 1080px) {
		flex-direction: column;
		margin-bottom: 1rem;
	}
`;

const TextH1 = styled.h1`
	span {
		font-weight: 500;
		color: #484258;
	}
	@media screen and (min-width: 320px) and (max-width: 1080px) {
		margin-top: 1rem;
	}
`;
const InputContainer = styled.div``;
const Icon = styled.div``;
const Input = styled.input``;

export default Navbar;
