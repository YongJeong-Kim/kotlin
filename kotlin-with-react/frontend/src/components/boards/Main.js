import React, { Component } from 'react'
import { Route, Switch } from 'react-router-dom'

import Board from './Board'
import Vacation from './Vacation'
import VacationView from './VacationView'
import VacationPost from './VacationPost'

export default class Main extends Component {
  render() {
    const { match } = this.props;
    return (
      <Switch>
        <Route exact path={`${match.url}/board1`} component={Board} />
        <Route exact path={`${match.url}/board2`} component={Vacation} />
        <Route exact path={`${match.url}/board2/1`} component={VacationView} />
        <Route exact path={`${match.url}/board2/post`} component={VacationPost} />
      </Switch>
    );
  }
}