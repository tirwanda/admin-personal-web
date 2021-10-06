import styled from 'styled-components';

export const Input = styled.input`
	height: 2.625rem;
	width: 100%;
	outline: none;
	border: 1px solid rgba(200, 200, 200, 0.3);
	padding: 0px 10px;
	border-bottom: 1.4px solid transparent;
	transition: all 200ms ease-in-out;
	font-size: 0.75rem;

	&::placeholder {
		color: rgba(200, 200, 200, 1);
	}

	&::not(:last-of-type) {
		border-bottom: 1.5px solid rgba(200, 200, 200, 0.4);
	}

	&:focus {
		outline: none;
		border-bottom: 2px solid rgb(241, 196, 15);
	}
`;
