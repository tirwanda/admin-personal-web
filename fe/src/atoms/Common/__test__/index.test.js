import React from 'react';
import { Input, MutedLink, BoldLink } from '../index';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

test('Should render <Input /> username correct', () => {
	const container = render(<Input placeholder="username" type="text" />);
	const element = container.getByPlaceholderText('username');
	expect(element).toBeInTheDocument();
});

test('Should render <Input /> username and text content must be empty', () => {
	const container = render(<Input placeholder="username" type="text" />);
	const element = container.getByPlaceholderText('username');
	expect(element.textContent).toBe('');
});

test('Should render <Input /> password correct', () => {
	const container = render(<Input placeholder="password" type="text" />);
	const element = container.getByPlaceholderText('password');
	expect(element).toBeInTheDocument();
});

test('Should render <Input /> password and text content must be null', () => {
	const container = render(<Input placeholder="password" type="text" />);
	const element = container.getByPlaceholderText('password');
	expect(element.textContent).toBe('');
});

test('Should render <MutedLink /> correct', () => {
	const container = render(<MutedLink>Forget your password?</MutedLink>);
	const element = container.getByText('Forget your password?');
	expect(element).toBeInTheDocument();
});

test('Should render <BoldLink /> correct', () => {
	const container = render(<BoldLink>SignUp</BoldLink>);
	const element = container.getByText('SignUp');
	expect(element).toBeInTheDocument();
});
