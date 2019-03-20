import React, { Component } from 'react'
import { Switch, Route } from 'react-router-dom'

export default class VacationView extends Component {
  render() {
    const { match } = this.props;
    console.log(match)
    return (
      <>
        {match.param}
        sdfsef
      </>
    );
  }
}