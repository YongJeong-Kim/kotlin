import React, { Component } from 'react'
import { BrowserRouter, Switch, Route } from 'react-router-dom'

import Board from './boards/Board'
import Vacation from './boards/Vacation'

const Inbox = () => (
  <div>
    inbox content
  </div>
);
export const Starred = () => (
  <div>
    starred content
  </div>
);
export default class MainContent extends Component {
  render() {
    const { match } = this.props
    console.log(match)
    return (
      <Switch>
        <Route path={'/nnn/Inbox'} component={Board} />
        <Route path={'/nnn/Starred'} component={Starred} />
        <Route exact path={'/'} component={Starred} />
      </Switch>
    );
  }
}