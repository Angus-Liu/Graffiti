import React, {PureComponent} from 'react';
import {connect} from 'react-redux'
import {Redirect} from 'react-router-dom'
import {LoginWrapper, LoginBox, Input, Button} from "./style";
import {actionCreators} from './store'

class Login extends PureComponent {
  render() {

    const {loginState} = this.props;

    if (loginState) {
       return <Redirect to='/'/>
    }

    return (
      <LoginWrapper>
        <LoginBox>
          <Input placeholder='账号' innerRef={(input) => {this.account = input}}/>
          <Input placeholder='密码' type='password' innerRef={(input) => {this.password = input}}/>
          <Button onClick={() => this.props.login(this.account.value, this.password.value)}>登录</Button>
        </LoginBox>
      </LoginWrapper>
    )
  }
}

const initMapStateToProps = (state) => ({
  loginState: state.loginReducer.get('login')
});

const initMapDispatchToProps = (dispatch) => ({
  login(account, password) {
    dispatch(actionCreators.login(account, password));
  }
});

export default connect(initMapStateToProps, initMapDispatchToProps)(Login);
