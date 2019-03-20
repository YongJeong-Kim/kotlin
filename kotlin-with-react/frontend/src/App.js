import React, { Component, Suspense } from 'react';
import axios from 'axios';

const Login = React.lazy(() => import('./components/Login'));
const Main = React.lazy(() => import('./components/Main'));

export default class App extends Component {
  state = {
    token: '',
  };
  componentWillMount = () => {
    this.setState({ token: window.localStorage.accessToken });
    console.log(window.localStorage.accessToken);
    axios.get('/api/checkExpiredToken', {
      headers: {
        Authorization: window.localStorage.accessToken,
      },
    }).then((response) => {
      console.log('then')
    }).catch(e => {
      if (e.response.status === 401) this.setState({ token: '' })
    })
  };

  render() {
    return (
      <Suspense fallback={<div>Loading...</div>}>
        { this.state.token === '' && <Login/> }
        { this.state.token !== '' && <Main /> }
      </Suspense>
    );
  }
}
