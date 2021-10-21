import React, { useState } from 'react';
import './about.scss';

import Profile from '../../assets/images/profile.jpg';
import ProfileModal from '../../organism/Modal/ProfileModal';
import Badge from '../../atoms/Badge/Badge';

import { connect } from 'react-redux';

const About = ({ user }) => {
	const [showModal, setShowModal] = useState(false);

	return (
		<div className="home-about">
			<ProfileModal showModal={showModal} setShowModal={setShowModal} />
			<div className="home-about-detail">
				<div className="home-about-detail-info">
					<div className="avatar">
						<img src={Profile} alt="Profile" />
					</div>
					<div className="detail-text">
						<h3>{user.name}</h3>
						<h5>{user.title}</h5>
					</div>
					{/* <Badge content="Update" /> */}
					<button
						onClick={() => setShowModal(true)}
						className="edit-button"
					>
						Update
					</button>
				</div>
				<div className="home-about-detail-desc">
					<p>{user.about}</p>
				</div>
			</div>
		</div>
	);
};

const mapStateToProps = ({ auth }) => {
	return {
		user: auth.user,
	};
};

export default connect(mapStateToProps)(About);
