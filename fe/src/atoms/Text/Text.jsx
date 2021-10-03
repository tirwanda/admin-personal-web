import styled from 'styled-components';

export const HeaderText = styled.h2`
	font-size: 1.875rem;
	font-weight: 600;
	line-height: 1.24;
	z-index: 2;
	margin: 0;
	color: #fff;
`;

export const SmallText = styled.h5`
	color: #fff;
	font-weight: 500;
	font-weight: 0.6875rem;
	z-index: 2;
	margin: 0;
	margin-top: 0.375rem;
`;

export const TextSpan = styled.span`
	width: ${(props) => (props.clicked ? '100%' : '0')};
	overflow: hidden;
	margin-left: ${(props) => (props.clicked ? '1.5rem' : '0')};
	transition: all 0.3s ease;
`;

export const Name = styled.div`
	padding: 0 1.5rem;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	h4 {
		display: inline-block;
		font-weight: 500;
		margin: 0;
		font-size: 0.9rem;
	}

	a {
		font-size: 0.8rem;
		text-decoration: none;
		color: var(--grey);

		&:hover {
			text-decoration: underline;
		}
	}
`;
