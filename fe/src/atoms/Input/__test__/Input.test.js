import React from 'react';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import { Input } from '../Input';

test('Should Render <Input /> Correct', () => {
	const { container, getByPlaceholderText } = render(
		<Input placeholder="Username" />
	);
	expect(container.querySelector('input')).toBeInTheDocument();
	expect(getByPlaceholderText('Username')).toBeInTheDocument();
});
