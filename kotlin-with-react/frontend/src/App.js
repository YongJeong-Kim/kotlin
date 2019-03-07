import React, { Component, Suspense } from 'react';
import logo from './logo.svg';
import './App.css';

const Example = React.lazy(() => import('./components/Example'));
const Login = React.lazy(() => import('./components/Login'));
const Index = React.lazy(() => import('./components/index'));

class App extends Component {
  render() {
    return (
      <Suspense fallback={<div>Loading...</div>}>
        <Index />
      </Suspense>
    );
     {/* <div className="App">
        <Suspense fallback={<div>Loading...</div>}>
          <Login />
        </Suspense>
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
        <Suspense fallback={<div>Loading...</div>}>
          <Example />
        </Suspense>
      </div>*/}


  }
}

export default App;
