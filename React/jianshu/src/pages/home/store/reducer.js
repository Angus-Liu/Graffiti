import {fromJS} from 'immutable'
import * as constants from './constants'

// 初始 state，使用 immutable 将其变为一个不可修改的对象
const defaultState = fromJS({
  topicList: [],
  articleList: [],
  recommendList: []
});

// reducer 导出的是一个纯函数
export default (state = defaultState, action) => {
  switch (action.type) {
    case constants.CHANGE_HOME_DATA:
      return state.merge({
        topicList: fromJS(action.topicList),
        articleList: fromJS(action.articleList),
        recommendList: fromJS(action.recommendList)
      });
    case constants.ADD_ARTICLE_LIST:
      return state.set('articleList', state.get('articleList').concat(action.list));
    default:
      return state;
  }
}
