import React, {PureComponent} from 'react';
import {connect} from 'react-redux';
import {ListItem, ListInfo, LoadMore} from '../style';
import {actionCreators} from '../store';
import {Link} from 'react-router-dom';

class List extends PureComponent {
  render() {

    const {articleList, getMoreList} = this.props;

    return (
      <div>
        {
          articleList.map((item) => (
            <Link to={'/detail/' + item.get('id')} key={item.get('id')}>
              <ListItem>
                <img className='list-pic' alt='' src={item.get('imgUrl')}/>
                <ListInfo>
                  <h3 className='title'>{item.get('title')}</h3>
                  <p className='desc'>{item.get('desc')}</p>
                </ListInfo>
              </ListItem>
            </Link>
          ))
        }
        <LoadMore onClick={getMoreList}>阅读更多</LoadMore>
      </div>
    )
  }
}

const initMapStateToProps = (state) => ({
  articleList: state.homeReducer.get('articleList')
});

const initMapDispatchToProps = (dispatch) => ({
  getMoreList() {
    dispatch(actionCreators.getMoreList())
  }
});

export default connect(initMapStateToProps, initMapDispatchToProps)(List);

