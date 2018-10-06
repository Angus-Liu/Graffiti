import React, {PureComponent} from 'react';
import {connect} from 'react-redux';
import {Link} from 'react-router-dom';
import {CSSTransition} from 'react-transition-group';
import {
  HeaderWrapper,
  Logo,
  Nav,
  NavItem,
  SearchWrapper,
  NavSearch,
  Addition,
  Button,
  SearchInfo,
  SearchInfoTitle,
  SearchInfoSwitch,
  SearchInfoList,
  SearchInfoItem
} from './style';
import {actionCreators} from './store'
import {actionCreators as loginActionCreators} from '../../pages/login/store'

class Header extends PureComponent {

  getListArea = () => {
    const {focused, mouseIn, list, page, totalPage, handleMouseEnter, handleMouseLeave, handleChangePage} = this.props;
    const newList = list.toJS();
    const pageList = [];
    if (newList.length) {
      for (let i = page * 10; i < (page + 1) * 10 && i < newList.length; i++) {
        pageList.push(<SearchInfoItem key={newList[i]}>{newList[i]}</SearchInfoItem>);
      }
    }

    if (focused || mouseIn) {
      return (
        <SearchInfo onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
          <SearchInfoTitle>
            热门搜索
            <SearchInfoSwitch onClick={() => handleChangePage(page, totalPage, this.spinIcon)}>
              <i ref={(icon) => {
                this.spinIcon = icon
              }} className="iconfont spin">&#xe851;</i>换一批
            </SearchInfoSwitch>
          </SearchInfoTitle>
          <SearchInfoList>
            {pageList}
          </SearchInfoList>
        </SearchInfo>
      )
    }
  };

  render() {
    const {list, focused, handleInputFocused, handleInputBlur, login, logout} = this.props;
    return (
      <HeaderWrapper>
        <Link to='/'>
          <Logo/>
        </Link>
        <Nav>
          <NavItem className='left active'>首页</NavItem>
          <NavItem className='left'>下载 App</NavItem>
          {
            login ? <NavItem className='right' onClick={logout}>退出</NavItem> :
              <Link to='/login'><NavItem className='right'>登录</NavItem></Link>
          }
          <NavItem className='right'>
            <i className="iconfont">&#xe636;</i>
          </NavItem>
          <SearchWrapper>
            <CSSTransition in={focused} timeout={200} classNames="slide">
              <NavSearch
                className={focused ? 'focused' : ''}
                onFocus={() => {
                  handleInputFocused(list)
                }}
                onBlur={handleInputBlur}
              />
            </CSSTransition>
            <i className={focused ? 'focused iconfont zoom' : 'iconfont zoom'}>&#xe614;</i>
            {this.getListArea()}
          </SearchWrapper>
        </Nav>
        <Addition>
          <Link to='/write'>
            <Button className='writing'>
              <i className="iconfont">&#xe615;</i>写文章
            </Button>
          </Link>
          <Button className='reg'>注册</Button>
        </Addition>
      </HeaderWrapper>
    )
  }
}

// 将 store 的 state 与组件的 props 建立关系
const initMapStateToProps = (state) => ({
  focused: state.headerReducer.get('focused'),
  mouseIn: state.headerReducer.get('mouseIn'),
  list: state.headerReducer.get('list'),
  totalPage: state.headerReducer.get('totalPage'),
  page: state.headerReducer.get('page'),
  login: state.loginReducer.get('login')
});
// 将 store 的 dispatch 与组件的 props 建立关系
const initMapDispatchToProps = (dispatch) => ({
  handleInputFocused(list) {
    if (list.size === 0) {
      dispatch(actionCreators.getList());
    }
    dispatch(actionCreators.searchFocus());
  },
  handleInputBlur() {
    dispatch(actionCreators.searchBlur());
  },
  handleMouseEnter() {
    dispatch(actionCreators.mouseEnter());
  },
  handleMouseLeave() {
    dispatch(actionCreators.mouseLeave());
  },
  handleChangePage(page, totalPage, spin) {
    let originAngle = spin.style.transform.replace(/[^0-9]/ig, '');
    if (originAngle) {
      originAngle = parseInt(originAngle, 10);
    } else {
      originAngle = 0;
    }
    spin.style.transform = 'rotate(' + (originAngle + 360) + 'deg)';
    dispatch(actionCreators.changePage(((page + 1) % totalPage)));
  },
  logout() {
    dispatch(loginActionCreators.logout());
  }
});

// connect 使组件和 store 建立连接
export default connect(initMapStateToProps, initMapDispatchToProps)(Header);
