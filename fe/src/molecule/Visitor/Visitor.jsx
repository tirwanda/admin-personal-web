import React from 'react';
import './visitor.scss';

import { BarChart } from '@mui/icons-material';

const Earnings = () => {
	return (
		<div className="visitor">
			<div className="visitor-content">
				<div className="chart">
					<BarChart />
				</div>
				<h3 className="visitor-text">Visitor</h3>
				<h2 className="visitor-qty">1234</h2>
				<h5 className="visitor-increase">+ 10% since last month</h5>
			</div>
		</div>
	);
};

export default Earnings;
