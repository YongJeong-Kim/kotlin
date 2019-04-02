import React, { Component } from 'react'
import { Route, Switch } from 'react-router-dom'

import Board from './Board'
import Vacation from './Vacation'
import VacationView from './VacationView'
import VacationPost from './VacationPost'

const Main = ({ match }) => (
  <Switch>
    <Route exact path={`${match.url}/board1`} component={Board} />
    <Route exact path={`${match.url}/휴가`} component={Vacation} />
    <Route exact path={`${match.url}/휴가/1`} component={VacationView} />
    <Route exact path={`${match.url}/휴가/글쓰기`} component={VacationPost} />
  </Switch>
);
export default Main