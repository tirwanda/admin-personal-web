import Button from '@restart/ui/esm/Button';
import React, { useState, useContext } from 'react';
import { AuthContext } from '../../App';

function Navbar() {
	const { state, dispatch } = useContext(AuthContext);

	return (
		<div className="navbar">
			<Button
				variant="primary"
				onClick={() => dispatch({ type: 'LOGOUT' })}
			>
				Logout
			</Button>
		</div>
	);
}

export default Navbar;
