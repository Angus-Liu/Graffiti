import React, {Component} from 'react';
import {Provider} from 'react-redux'
import {BrowserRouter, Route} from 'react-router-dom';
import store from './store';
import Header from './common/header/index';
import Home from './pages/home';
import Detail from './pages/detail'
import Login from './pages/login'
import Write from './pages/write'

class App extends Component {
  render() {
    return (
      // 让 provider 里的所有组件都可以使用 store
      <Provider store={store}>
          <BrowserRouter>
            <div>
              <Header/>
              <Route path='/' exact component={Home}/>
              <Route path='/login' exact component={Login}/>
              <Route path='/detail/:id' exact component={Detail}/>
              <Route path='/write' exact component={Write}/>
            </div>
          </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
