import React from 'react';
import { MutedLink, BoldLink } from '../index';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

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
