import axios from 'axios';
import * as constants from './constants';
import {fromJS} from 'immutable';

const changeHomeData = (result) => ({
  type: constants.CHANGE_HOME_DATA,
  topicList: result.topicList,
  articleList: result.articleList,
  recommendList: result.recommendList
});

const addHomeList = (list) => ({
  type: constants.ADD_ARTICLE_LIST,
  list: fromJS(list)
});

export const getHomeInfo = () => {
  return (dispatch) => {
    axios.get('/api/home.json')
      .then((res) => {
        dispatch(changeHomeData(res.data.data));
      });
  }
};

export const getMoreList = () => {
  return (dispatch) => {
    axios.get('/api/homeList.json')
      .then((res) => {
        dispatch(addHomeList(res.data.data));
      });
  }
};
