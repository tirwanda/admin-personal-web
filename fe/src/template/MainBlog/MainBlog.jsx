import React from 'react';
import styled from 'styled-components';

import wallpaper from '../../assets/images/main-content/patrick-tomasso-Oaqk7qqNh_c-unsplash.jpg';
import LogoComponent from '../../atoms/Logo/LogoComponent';
import PowerButton from '../../atoms/PowerButton/PowerButton';
import SocialIcons from '../../atoms/SocialICons/SocialIcons';
import BlogComponent from '../../molecule/BlogComponent/BlogComponent';

import { Blogs } from '../../data/BlogData';

const MainContainerBlog = styled.div`
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

const MainBlog = () => {
	return (
		<MainContainerBlog>
			<Container>
				<LogoComponent />
				<PowerButton />
				<SocialIcons />

				<Center>
					<Grid>
						{Blogs.map((blog) => {
							return <BlogComponent key={blog.id} blog={blog} />;
						})}
					</Grid>
				</Center>
			</Container>
		</MainContainerBlog>
	);
};

export default MainBlog;
