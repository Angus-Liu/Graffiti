import {createStore , applyMiddleware, compose} from 'redux';
// redux-thunk 是 redux 的中间件，允许 action 接收函数
import thunk from 'redux-thunk'
import reducer from './reducer'

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(reducer, composeEnhancers(
  applyMiddleware(thunk)
));

export default store;
