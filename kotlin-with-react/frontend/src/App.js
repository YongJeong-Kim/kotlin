import React, { Component, Suspense } from 'react';
import axios from 'axios';
import { observer, inject } from 'mobx-react'
import { withRouter } from 'react-router-dom'

const Login = React.lazy(() => import('./components/Login'));
const Main = React.lazy(() => import('./components/Main'));

@withRouter
@inject('loginStore')
@observer
class App extends Component {
  state = {
    token: '',
    isNeedLogin: true,
  };
  handleIsNeedLogin = (isNeedLogin) => {
    this.setState({ isNeedLogin })
  };
  componentWillMount = () => {
    console.log('app component will mount start');
    this.setState({ token: window.localStorage.accessToken }, () => {
      axios.get('/api/checkExpiredToken', {
        headers: {
          // Authorization: window.localStorage.accessToken,
          Authorization: this.state.token,
        },
      }).then((response) => {
        if (response.status === 200) {
          this.setState({ isNeedLogin: false }, () => {
            this.props.loginStore.accessToken = this.state.token
          })
        }
        console.log('App axios then')
      }).catch(e => {
        if (e.response.status === 401) this.setState({ token: '', isNeedLogin: true })
      })
    });
    console.log('app component will mount end');
  };

  render() {
    console.log('qqqddd')
    return (
      <Suspense fallback={<div>Loading...</div>}>
        { this.state.isNeedLogin && <Login handleIsNeedLogin={this.handleIsNeedLogin} /> }
        { !this.state.isNeedLogin && <Main /> }
      </Suspense>
    );
  }
}
export default App