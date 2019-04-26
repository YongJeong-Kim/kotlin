import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

import { Switch, Route } from 'react-router-dom'

const styles = theme => ({
  root: {
    ...theme.mixins.gutters(),
    paddingTop: theme.spacing.unit * 2,
    paddingBottom: theme.spacing.unit * 2,
  },
});
class VacationView extends Component {
  render() {
    const { classes, match } = this.props;
    console.log(match)
    console.log(match.params)
    const postId = match.params
    return (
      <>
        <Paper elevation={1}>
          <Typography variant="h5" component="h3">
            qweqwe
          </Typography>
          <Typography component="p">
            Paper can be used to build surface or other elements for your application.
          </Typography>
        </Paper>
      </>
    );
  }
}

VacationView.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(VacationView);