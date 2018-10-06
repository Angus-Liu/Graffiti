import React, {PureComponent} from 'react';
import {connect} from 'react-redux';
import {TopicWrapper, TopicItem} from '../style'

class Topic extends PureComponent {

  render() {
    const {topicList} = this.props;
    return (
      <TopicWrapper>
        {
          topicList.map((item) => (
            <TopicItem key={item.get('imgUrl')}>
              <img className='topic-pic' alt={item.get('title')} src={item.get('imgUrl')}/>
              {item.get('title')}
            </TopicItem>
          ))
        }
      </TopicWrapper>
    )
  }
}

const initMapStateToProps = (state) => ({
  topicList: state.homeReducer.get('topicList')
});

export default connect(initMapStateToProps, null)(Topic);
