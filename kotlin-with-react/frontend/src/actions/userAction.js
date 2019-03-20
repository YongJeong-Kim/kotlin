import axios from 'axios'

import { connect } from 'react-redux'
import Main from '../components/Main'

export const setLogInUser = user => {
  return dispatch => dispatch({ type: 'LOGIN_USER', payload: user });
};

/*
export default connect(
  null,
  setLogInUser
)(Main)*/
