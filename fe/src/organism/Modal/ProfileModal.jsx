import React from 'react';
import './profileModal.scss';
import { Link } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';

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
				>
					<motion.div className="modal" variants={modal}>
						<p>Edit Profile</p>
						<Link to="/dashboard">
							<div className="modal-button">
								<button className="modal-button-close">
									Close
								</button>
								<button className="modal-button-save">
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
