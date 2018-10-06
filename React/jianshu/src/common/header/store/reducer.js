import * as constants from './constants'
import {fromJS} from 'immutable'

// 初始 state，使用 immutable 将其变为一个不可修改的对象
const defaultState = fromJS({
  focused: false,
  mouseIn: false,
  list: [],
  page: 0,
  totalPage: 1
});

// reducer 导出的是一个纯函数
export default (state = defaultState, action) => {
  switch (action.type) {
    case constants.SEARCH_FOCUSED:
      return state.set('focused', true);
    case constants.SEARCH_BLUR:
      return state.set('focused', false);
    case constants.MOUSE_ENTER:
      return state.set('mouseIn', true);
    case constants.MOUSE_LEAVE:
      return state.set('mouseIn', false);
    case constants.CHANGE_LIST:
      return state.merge({
        list: action.data,
        totalPage: action.totalPage
      });
    case constants.CHANGE_PAGE:
      return state.set('page', action.page);
    default:
      return state;
  }
}
