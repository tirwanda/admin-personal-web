import React from 'react';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import { MenuButton, SubmitButton } from '../Button';

test('Should render <MenuButton /> correct', () => {
	const { container, getByText } = render(<MenuButton>Click</MenuButton>);
	expect(container.querySelector('button')).toBeInTheDocument();
	expect(getByText('Click')).toBeInTheDocument();
});

test('Should render <SubmitButton /> correct', () => {
	const { container, getByText } = render(<SubmitButton>Click</SubmitButton>);
	expect(container.querySelector('button')).toBeInTheDocument();
	expect(getByText('Click')).toBeInTheDocument();
});
