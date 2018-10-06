import {combineReducers} from 'redux'
import {headerReducer} from '../common/header/store'
import {homeReducer} from '../pages/home/store'
import {detailReducer} from '../pages/detail/store'
import {loginReducer} from '../pages/login/store'

const reducer = combineReducers({
  headerReducer,
  homeReducer,
  detailReducer,
  loginReducer
});

export default reducer;
