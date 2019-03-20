import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withStyles, MuiThemeProvider, createMuiTheme } from '@material-ui/core/styles'
import TextField from '@material-ui/core/TextField'
import blue from '@material-ui/core/colors/blue';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import Chip from '@material-ui/core/Chip';

import axios from 'axios';
import Editor from './Editor'

const styles = theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
  },
  dense: {
    marginTop: 16,
  },
  col: {
    display: 'flex',
  },
  root: theme.mixins.gutters({
    paddingTop: 16,
    paddingBottom: 16,
    marginTop: theme.spacing.unit * 3,
    maxWidth: '900px',
    margin: '0 auto',
  }),
  row: {
    display: 'flex',
    justifyContent: 'center',
  },
  button: {
    margin: theme.spacing.unit,
    // flex: 1,
  },
  input: {
    display: 'none',
  },
  chip: {
    margin: theme.spacing.unit,
  },
});
const theme = createMuiTheme({
  palette: {
    primary: blue,
  },
  typography: { useNextVariants: true },
});

class VacationPost extends Component {
  state = {
    contact: {
      customerEmail: null,
      subject: null,
      text: null,
      file: null,
    },
    subject: '',
    content: '',
  };
  filenameUpdate = (file) => {
    this.setState({
      contact: {
        ...this.state.contact,
        file,
      }
    })
  };
  handleFileUpload = (e) => {
    const reader = new FileReader();
    const file = e.target.files[0];
    if (file !== undefined) {
      reader.onloadend = () => {
        this.filenameUpdate(file);
      };
      reader.readAsDataURL(file);
    } else {
      this.filenameUpdate(null);
    }
  };
  handleFileDelete = () => {
    this.fileInput.value = '';
    this.filenameUpdate(null);
  };
  handleClick = () => {
    alert(this.state.subject)
    alert(this.state.content)
  };
  onChangeSubject = (e) => {
    this.setState({ subject: e.target.value, })
  };
  onChangeContent = (newContent) => {
    console.log(newContent)
    this.setState({ content: newContent })
  };
  render() {
    const { classes } = this.props;
    const { contact } = this.state;

    return (
      <div className={classes.col} >
        <Paper className={classes.root} elevation={4} style={{width: '100%'}}>
          <MuiThemeProvider theme={theme} >
            <div className={classes.col}>
              <TextField
                id="outlined-subject-input"
                label="제목"
                className={classes.textField}
                name="subject"
                margin="normal"
                variant="outlined"
                fullWidth
                onChange={this.onChangeSubject}
              />
            </div>
            <input
              className={classes.input}
              id="text-button-file"
              multiple
              type="file"
              onChange={this.handleFileUpload}
              ref = {ref => this.fileInput = ref}
            />
            <label htmlFor="text-button-file">
              <Button color='primary' variant='outlined' component="span" className={classes.button}>
                첨부파일
              </Button>
            </label>
            {contact.file && contact.file.name != null &&
              <Chip
                label={contact.file && contact.file.name}
                clickable
                className={classes.chip}
                color="primary"
                variant="outlined"
                onDelete={this.handleFileDelete}
              />
            }
            <div className={classes.col} style={{margin: '8px'}}>
              <Editor onChangeContent={this.onChangeContent}/>
            </div>
            <div className={classes.col} style={{float: 'right'}}>
              <Button variant="contained" color="primary" className={classes.button} onClick={this.handleClick}>
                {'글쓰기'}
              </Button>
            </div>
          </MuiThemeProvider>
        </Paper>
      </div>
    )
  }
}
VacationPost.propTypes = {
  classes: PropTypes.object.isRequired,
};
export default withStyles(styles)(VacationPost)