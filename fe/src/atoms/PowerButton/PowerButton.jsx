import styled from 'styled-components';
import powerLogo from '../../assets/svg/power-off-solid.svg';
import { PowerBtn } from '../AllSvgs/AllSvgs';

const Power = styled.button`
	position: relative;
	top: 0.5rem;
	left: 50%;
	transform: translate(-50, 0);
	background-color: #fcf6f4;
	padding: 0.3rem;
	border-radius: 50%;
	border: 1px solid #000;
	width: 2.5rem;
	height: 2.5rem;
`;

const PowerButton = () => {
	return (
		<Power>
			<PowerBtn width={30} height={30} fill="currentColor" />
			{/* <img src={powerLogo} alt="Power" /> */}
		</Power>
	);
};

export default PowerButton;
