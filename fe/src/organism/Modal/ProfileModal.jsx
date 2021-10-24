import React from 'react';
import './profileModal.scss';
import { motion, AnimatePresence } from 'framer-motion';
import { Grid } from '@mui/material';

import { Publish } from '@mui/icons-material';

import Profile from '../../assets/images/profile.jpg';
import { TextField } from '@material-ui/core';

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
		y: '150px',
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
								<label htmlFor="file" id="button-upload">
									<Publish />
								</label>
								<input type="file" id="file" />
							</Grid>
							<Grid item lg={6} className="modal-form">
								<Grid container spacing={2}>
									<Grid item xs={12} sm={6}>
										<TextField
											fullWidth
											name="name"
											label="Name"
											defaultValue="Test"
										/>
									</Grid>
									<Grid item xs={12} sm={6}>
										<TextField
											fullWidth
											name="title"
											label="Title"
										/>
									</Grid>
									<Grid item xs={12}>
										<TextField
											multiline
											rows={5}
											fullWidth
											name="description"
											label="Description"
										/>
									</Grid>
								</Grid>
							</Grid>
						</Grid>
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
					</motion.div>
				</motion.div>
			)}
		</AnimatePresence>
	);
};

export default ProfileModal;
