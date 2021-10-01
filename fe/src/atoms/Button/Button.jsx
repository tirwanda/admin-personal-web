import styled from 'styled-components';

export const MenuButton = styled.button`
	display: flex;
	justify-content: center;
	align-items: center;

	background-color: var(--black);
	border: none;
	width: 2.5rem;
	height: 2.5rem;
	border-radius: 50%;
	margin: 0.5rem 0.5rem;
	cursor: pointer;

	position: relative;

	&::before,
	&::after {
		content: '';
		background-color: var(--white);
		height: 2px;
		width: 1rem;
		position: absolute;
		transition: all 0.3s ease-in-out;
	}

	&::before {
		top: ${(props) => (props.clicked ? '1.5' : '1rem')};
		transform: ${(props) =>
			props.clicked ? 'rotate(135deg)' : 'rotate(0)'};
	}

	&::after {
		top: ${(props) => (props.clicked ? '1.2' : '1.5rem')};
		transform: ${(props) =>
			props.clicked ? 'rotate(-135deg)' : 'rotate(0)'};
	}
`;

export const SubmitButton = styled.button`
	width: 100%;
	padding: 11px 40%;
	color: #fff;
	font-size: 15px;
	font-weight: 600;
	border: none;
	border-radius: 100px;
	cursor: pointer;
	transition: all 240ms ease-in-out;
	background: rgb(241, 196, 15);
	background: linear-gradient(
		58deg,
		rgba(241, 196, 15, 1),
		rgba(243, 172, 18, 1)
	);

	&::hover {
		filter: brightness(1.03);
	}
`;
