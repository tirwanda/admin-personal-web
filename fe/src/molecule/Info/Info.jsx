import React from 'react';
import './info.scss';

import Badge from '../../atoms/Badge/Badge';

const Info = () => {
	return (
		<div className="info">
			<div className="info-content">
				<div className="row">
					<div className="number">98</div>
					<div className="subtitle">
						<h3>Rank</h3>
						<h5>In top 20%</h5>
					</div>
				</div>
			</div>
			<div className="info-content">
				<div className="row">
					<div className="number">98</div>
					<div className="subtitle">
						<h3>Project</h3>
						<h5>3 in this month</h5>
					</div>
				</div>

				<div className="row badge">
					<Badge content="mobile app" glow />
					<Badge content="branding" glow />
				</div>
			</div>
		</div>
	);
};

export default Info;
