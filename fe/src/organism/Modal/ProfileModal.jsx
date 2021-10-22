import React from 'react';
import './profileModal.scss';
import { Link } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import { Grid } from '@mui/material';

import Profile from '../../assets/images/profile.jpg';

const backDrop = {
	visible: { opacity: 1 },
	hidden: { opacity: 0 },
};

const modal = {
	hidden: {
		y: '-100vh',
		opacity: 0,
	},
	visible: {
		y: '200px',
		opacity: 1,
		transition: {
			delay: 0.5,
		},
	},
};

const ProfileModal = ({ showModal, setShowModal }) => {
	return (
		<AnimatePresence exitBeforeEnter>
			{showModal && (
				<motion.div
					className="backDrop"
					variants={backDrop}
					animate="visible"
					initial="hidden"
					exit="hidden"
				>
					<motion.div className="modal" variants={modal}>
						<h1 className="modal-title">Edit Profile</h1>
						<Grid container spacing={3}>
							<Grid item lg={6} className="modal-avatar">
								<img src={Profile} alt="Avatar" id="photo" />
								<input type="file" id="file" />
								<label htmlFor="file" id="button-upload">
									Choose Photo
								</label>
							</Grid>
							<Grid item lg={6}></Grid>
						</Grid>
						<Link to="/dashboard">
							<div className="modal-button">
								<button
									onClick={() => setShowModal(false)}
									className="modal-button-close"
								>
									Close
								</button>
								<button
									onClick={() => setShowModal(false)}
									className="modal-button-save"
								>
									Save
								</button>
							</div>
						</Link>
					</motion.div>
				</motion.div>
			)}
		</AnimatePresence>
	);
};

export default ProfileModal;
