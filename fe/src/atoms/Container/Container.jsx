import styled from 'styled-components';

export const BoxContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

export const AccountBoxContainer = styled.div`
	width: 280px;
	min-height: 550px;
	display: flex;
	flex-direction: column;
	border-radius: 19px;
	background-color: #fff;
	box-shadow: 0 0 2px rgba(15, 15, 15, 0.28);
	position: relative;
	overflow: hidden;
`;

export const TopContainer = styled.div`
	width: 100%;
	height: 250px;
	display: flex;
	flex-direction: column;
	justify-content: flex-end;
	padding: 0 1.8em;
	padding-bottom: 5em;
`;

export const HeaderContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
`;

export const AppContainer = styled.div`
	width: 100%;
	height: 100vh;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
`;

export const InnerContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	padding: 0px 0.8em;
`;

export const FormContainer = styled.div`
	width: 100%;
	display: flex;
	flex-direction: column;
	box-shadow: 0px 0px 2.5px rgba(15, 15, 15, 0.19);
`;

export const NavContainer = styled.div`
	position: fixed;

	.active {
		border-right: 4px solid var(--white);

		img {
			filter: invert(100%) sepia(0%) saturate(0%) hue-rotate(93deg)
				brightness(103%) contrast(103%);
		}
	}
`;

export const SidebarContainer = styled.div`
	background-color: var(--black);
	width: 3.5rem;
	height: 80vh;
	margin-top: 1rem;
	border-radius: 0 30px 30px 0;
	padding: 1rem 0;

	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: space-between;

	position: relative;
`;

export const LogoContainer = styled.div`
	width: 2.5rem;

	img {
		width: 100%;
		height: auto;
	}
`;
