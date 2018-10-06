import React, {PureComponent} from 'react';
import {connect} from 'react-redux'
import {actionCreators} from './store'
import {DetailWrapper, Header, Content} from "./style";

class Detail extends PureComponent {

  render() {
    const {title, content} = this.props;
    return (
      <DetailWrapper>
        <Header>{title}</Header>
        {/*dangerouslySetInnerHTML 直接渲染带 html 标签的文本，不会进行转义，但不可滥用，以防出现 CSRF */}
        <Content dangerouslySetInnerHTML={{__html: content}}/>
      </DetailWrapper>
    )
  }

  componentDidMount() {
    this.props.getDetail(this.props.match.params.id);
  }
}

const initMapStateToProps = (state) => ({
  title: state.detailReducer.get('title'),
  content: state.detailReducer.get('content')
});

const initMapDispatchToProps = (dispatch) => ({
  getDetail(id) {
    dispatch(actionCreators.getDetail(id))
  }
});

export default connect(initMapStateToProps, initMapDispatchToProps)(Detail);
