import React, { useEffect, useState } from 'react';
import './profileModal.scss';

import { fetchUserData } from '../../api/authenticationService';
import Profile from '../../assets/images/profile.jpg';
import { updateProfile } from '../../api/updateProfileService';

import { motion, AnimatePresence } from 'framer-motion';
import { connect } from 'react-redux';

import { Grid } from '@mui/material';
import { Publish } from '@mui/icons-material';
import { TextField } from '@material-ui/core';
import { updateUserData } from '../../redux/authActions';

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

const ProfileModal = ({ showModal, setShowModal, user, updateUserData }) => {
	const [value, setValue] = useState(user);
	const handleInputChange = (event) => {
		setValue({
			...value,
			[event.target.name]: event.target.value,
		});
	};

	const reload = () => window.location.reload();

	const handleFormUpdate = (event) => {
		event.preventDefault();

		updateProfile(value)
			.then((res) => {
				updateUserData(res.data);
				setShowModal(false);
				reload();
				console.log('Success Update Data: ', user);
			})
			.catch((err) => {
				console.log('Error Message: ', err);
				setShowModal(false);
			});
	};

	useEffect(() => {
		if (user) {
			setValue(user);
		}
	}, [user]);

	console.log('value', value);
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
											defaultValue={user.name}
											onChange={handleInputChange}
										/>
									</Grid>
									<Grid item xs={12} sm={6}>
										<TextField
											fullWidth
											name="title"
											label="Title"
											defaultValue={user.title}
											onChange={handleInputChange}
										/>
									</Grid>
									<Grid item xs={12}>
										<TextField
											multiline
											rows={5}
											fullWidth
											name="about"
											label="Description"
											defaultValue={user.about}
											onChange={handleInputChange}
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
								onClick={handleFormUpdate}
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

const mapStateToProps = ({ auth }) => {
	return {
		user: auth.user,
	};
};

const mapDispatchToProps = (dispatch) => {
	return {
		updateUserData: (data) => dispatch(updateUserData(data)),
	};
};
export default connect(mapStateToProps, mapDispatchToProps)(ProfileModal);
