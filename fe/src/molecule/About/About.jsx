import React, { useState } from 'react';
import './about.scss';

import Profile from '../../assets/images/profile.jpg';
import ProfileModal from '../../organism/Modal/ProfileModal';

import { connect } from 'react-redux';
import { AnimatePresence } from 'framer-motion';

const About = ({ user }) => {
	const [showModal, setShowModal] = useState(false);

	return (
		<div className="home-about">
			<ProfileModal showModal={showModal} setShowModal={setShowModal} />
			<AnimatePresence
				exitBeforeEnter
				onExitComplete={() => setShowModal(false)}
			>
				<div className="home-about-detail">
					<div className="home-about-detail-info">
						<div className="avatar">
							<img src={Profile} alt="Profile" />
						</div>
						<div className="detail-text">
							<h3>{user.name}</h3>
							<h5>{user.title}</h5>
						</div>
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
			</AnimatePresence>
		</div>
	);
};

const mapStateToProps = ({ auth }) => {
	return {
		user: auth.user,
	};
};

export default connect(mapStateToProps)(About);
