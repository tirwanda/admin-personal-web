import React from 'react';
import { render } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';

import { BackDrop } from '../BackDrop';

test('Should render componenr <BackDrop/> Correct', () => {
	const { container } = render(<BackDrop />);
	expect(container.querySelector('div')).toBeInTheDocument();
});
