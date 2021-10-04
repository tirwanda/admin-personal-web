import React from 'react';

import {
	BoxContainer,
	AccountBoxContainer,
	TopContainer,
	HeaderContainer,
	AppContainer,
	InnerContainer,
	FormContainer,
	NavContainer,
	SidebarContainer,
	LogoContainer,
} from '../Container';

import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

test('Should render <BoxContainer /> correct', () => {
	const { container, getByText } = render(<BoxContainer>test</BoxContainer>);
	expect(getByText('test')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Should render <FormContainer /> correct', () => {
	const { container, getByText } = render(
		<FormContainer>input</FormContainer>
	);
	expect(getByText('input')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <AccountBoxContainer /> correct', () => {
	const { container, getByText } = render(
		<AccountBoxContainer>container</AccountBoxContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <TopContainer /> correct', () => {
	const { container, getByText } = render(
		<TopContainer>container</TopContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <HeaderContainer /> correct', () => {
	const { container, getByText } = render(
		<HeaderContainer>container</HeaderContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <AppContainer /> correct', () => {
	const { container, getByText } = render(
		<AppContainer>container</AppContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <InnerContainer /> correct', () => {
	const { container, getByText } = render(
		<InnerContainer>container</InnerContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <NavContainer /> correct', () => {
	const { container, getByText } = render(
		<NavContainer className="active">container</NavContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
	expect(container.querySelector('.active')).toBeInTheDocument();
});

test('Shoul render <SidebarContainer /> correct', () => {
	const { container, getByText } = render(
		<SidebarContainer>container</SidebarContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});

test('Shoul render <LogoContainer /> correct', () => {
	const { container, getByText } = render(
		<LogoContainer>container</LogoContainer>
	);
	expect(getByText('container')).toBeInTheDocument();
	expect(container.querySelector('div')).toBeInTheDocument();
});
