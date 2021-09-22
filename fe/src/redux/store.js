import { createStore } from 'redux';
import rootReducer from './reducers';

// Store
const store = createStore(rootReducer);

export default store;
