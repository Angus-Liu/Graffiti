import React, {PureComponent} from 'react';
import {connect} from 'react-redux'
import {actionCreators} from './store'
import List from './components/List';
import Recommend from './components/Recommend';
import Topic from './components/Topic';
import Writer from './components/Writer';
import {
  HomeWrapper,
  HomeLeft,
  HomeRight,
  BackTop
} from './style';

class Home extends PureComponent {

  handleScrollTop() {
    window.scrollTo(0, 0);
  }

  render() {
    return (
      <HomeWrapper>
        <HomeLeft>
          <img className='banner-img' alt="12 "
               src="https://upload.jianshu.io/admin_banners/web_images/4490/b7edd948215a7a8db1fc8e7b3c07171f53803185.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/1250/h/540"/>
          <Topic/>
          <List/>
        </HomeLeft>
        <HomeRight>
          <Recommend/>
          <Writer/>
        </HomeRight>
        <BackTop onClick={this.handleScrollTop}>回到顶部</BackTop>
      </HomeWrapper>
    )
  }

  componentDidMount() {
    this.props.changeHomeData();
  }
}

const initMapDispatchToProps = (dispatch) => ({
  changeHomeData() {
    dispatch(actionCreators.getHomeInfo());
  }
});

export default connect(null, initMapDispatchToProps)(Home);
