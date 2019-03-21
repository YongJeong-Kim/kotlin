import React, { Component } from 'react';
import PropTypes from 'prop-types';

// material-ui components
import Button from '@material-ui/core/Button';
import Avatar from '@material-ui/core/Avatar';
import Paper from '@material-ui/core/Paper';
import TextField from '@material-ui/core/TextField';

// material-ui icons
import PersonIcon from '@material-ui/icons/Person';
import LockIcon from '@material-ui/icons/Lock';

//material-ui colors and style
import blue from '@material-ui/core/colors/blue';
import { withStyles, MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles';
import axios from "axios";

const styles = theme => ({
  container: {
    // display: 'flex',
    flexWrap: 'wrap',
  },
  lock: {
    margin: 10,
    color: '#fff',
    backgroundColor: blue[500],
  },
  avatar: {
    margin: 10,
    color: '#fff',
    backgroundColor: blue[500],
  },
  row: {
    display: 'flex',
    justifyContent: 'center',
  },
  button: {
    margin: theme.spacing.unit,
    color: '#fff',
  },
  label: {
    textTransform: 'capitalize',
  },
  input: {
    display: 'none',
  },
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 3,
    maxWidth: '900px',
    margin: '0 auto',
  }),
  col: {
    display: 'flex',
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
});

const theme = createMuiTheme({
  palette: {
    primary: blue,
  },
  typography: { useNextVariants: true },
});

const styleButton = {
  background: blue[500],
};

class Login extends Component {
  state = {
    username: 'user',
    password: 'user',
  };

  handleChangeName = event => {
    this.setState({ username: event.target.value });
  };
  handleChangePassword = event => {
    this.setState({ password: event.target.value });
  };
  handleTest = () => {
    axios.get('http://localhost:3000/api/users', {
      headers: {
        // Authorization: localStorage.getItem('token')
        Authorization: window.localStorage.accessToken
      }
    }).then((response) => {
      console.log(response)
    }).catch(e => {
      console.log(e.response)
    })
  };
  handleLogin = () => {
    const login = {
      username: this.state.username,
      password: this.state.password,
    };
    axios.post('/login', login)
      .then(response => {
        window.localStorage.accessToken = 'Bearer ' + response.data.token
        // localStorage.setItem("token", 'Bearer ' + response.data.token)
        // this.props.history.push('/');
        window.location.href = '/';
        console.log('success')
      }).catch(e => {

      })
  };

  render() {
    const classes = this.props.classes;

    return (
      <div className={classes.col}>
        <Button onClick={this.handleTest}>DF</Button>
        <Paper className={classes.root} elevation={4} >
          <form className={classes.container} >
            <div className={classes.row}>
              <Avatar className={classes.avatar}>
                <PersonIcon />
              </Avatar>
              <MuiThemeProvider theme={theme}>
                <TextField
                  id="standard-name"
                  label="Name"
                  className={classes.textField}
                  value={this.state.username}
                  onChange={this.handleChangeName}
                  margin="normal"
                />
              </MuiThemeProvider>
            </div>
            <div className={classes.row}>
              <Avatar className={classes.lock}>
                <LockIcon />
              </Avatar>
              <MuiThemeProvider theme={theme}>
                <TextField
                  id="standard-name"
                  label="Password"
                  className={classes.textField}
                  value={this.state.password}
                  onChange={this.handleChangePassword}
                  margin="normal"
                />
              </MuiThemeProvider>
            </div>
            <div className={classes.row}>
              <label htmlFor="raised-button-file" >
                <Button onClick={this.handleLogin} component="span" style={styleButton} className={classes.button}>
                  Login
                </Button>
              </label>
            </div>
          </form>
        </Paper>
      </div>
    );
  }
}

Login.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Login);