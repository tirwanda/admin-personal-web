import React, { useState, useEffect } from 'react';
import styled from 'styled-components';

import wallpaper from '../../assets/images/main-content/patrick-tomasso-Oaqk7qqNh_c-unsplash.jpg';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import BlogComponent from '../../molecule/BlogComponent/BlogComponent';

import { Blogs } from '../../data/BlogData';
import AnchorComponent from '../../atoms/Anchor/AnchorComponent';
import BigTitle from '../../atoms/BigTitle/BigTitle';
import { motion } from 'framer-motion';

const MainContainerBlog = styled(motion.div)`
	background-image: url(${wallpaper});
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
`;
const Container = styled.div`
	background-color: ${(props) => `rgba(${props.theme.bodyRgba},0.8)`};
	width: 100%;
	height: auto;
	position: relative;
	padding-bottom: 5rem;
`;

const Center = styled.div`
	display: flex;
	justify-content: center;
	align-items: center;
	padding-top: 10rem;
`;

const Grid = styled.div`
	display: grid;
	grid-template-columns: repeat(2, minmax(calc(10rem + 15vw), 1fr));
	grid-gap: calc(1rem + 2vw);
`;

const container = {
	hidden: { opacity: 0 },
	show: {
		opacity: 1,

		transitions: {
			staggerChildren: 0.5,
			duration: 0.5,
		},
	},
};

const MainBlog = () => {
	const [numbers, setNumbers] = useState(0);

	useEffect(() => {
		let num = (window.innerHeight - 70) / 25;
		setNumbers(parseInt(num));
	}, []);
	return (
		<MainContainerBlog
			variants={container}
			initial="hidden"
			animate="show"
			exit={{
				opacity: 0,
				transition: { duration: 0.5 },
			}}
		>
			<Container>
				<LogoComponent />
				<PowerButton />
				<SocialIcons />
				<AnchorComponent number={numbers} />
				<Center>
					<Grid>
						{Blogs.map((blog) => {
							return <BlogComponent key={blog.id} blog={blog} />;
						})}
					</Grid>
				</Center>
				<BigTitle text="BLOG" top="5rem" left="5rem" />
			</Container>
		</MainContainerBlog>
	);
};

export default MainBlog;
