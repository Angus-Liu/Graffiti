import React, {PureComponent} from 'react';
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'

class Write extends PureComponent {
  render() {

    const {loginState} = this.props;

    if (loginState) {
       return (
         <div>写文章页面</div>
       )
    } else {
      return <Redirect to='/login'/>
    }
  }
}

const initMapStateToProps = (state) => ({
  loginState: state.loginReducer.get('login')
});

export default connect(initMapStateToProps, null)(Write);
