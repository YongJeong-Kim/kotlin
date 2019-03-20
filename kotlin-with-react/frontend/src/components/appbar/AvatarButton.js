import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Avatar from '@material-ui/core/Avatar';
import deepPurple from '@material-ui/core/colors/deepPurple';

import ClickAwayListener from '@material-ui/core/ClickAwayListener';
import Grow from '@material-ui/core/Grow';
import Paper from '@material-ui/core/Paper';
import Popper from '@material-ui/core/Popper';
import MenuItem from '@material-ui/core/MenuItem';
import MenuList from '@material-ui/core/MenuList';
import axios from 'axios'
import ProfileModal from './ProfileModal'

const styles = {
  avatar: {
    margin: 10,
  },
  bigAvatar: {
    margin: 10,
    width: 60,
    height: 60,
  },
  purpleAvatar: {
    // margin: 10,
    color: '#fff',
    backgroundColor: deepPurple[500],
  },
};

class AvatarButton extends Component {
  state = {
    open: false,
    avatar: '',
    profileOpen: false,
  };
  componentWillMount = () => {
    axios.get('/api/user/5c85f9fa710c0a2144ba0e0b', {
      headers: {
        Authorization: window.localStorage.accessToken,
      }
    }).then((response) => {
        console.log(response.data)
        this.setState({ avatar: response.data.avatar })
      }).catch(e => {
        window.location.href = '/'
      })
  };

  handleToggle = () => {
    this.setState(state => ({ open: !state.open }));
    console.log('handle')
  };
  handleClose = event => {
    if (this.anchorEl.contains(event.target)) {
      return;
    }

    this.setState({ open: false });
    console.log('close')
  };
  handleLogout = () => {
    window.localStorage.accessToken = '';
    this.setState({ open: false });
    window.location.href = '/';
  };
  handleRequestProfile = () => {
    this.setState({ profileOpen: true, open: !this.state.open, });
  };
  handleRequestProfileClose = (profileOpen) => {
    this.setState({ profileOpen, });
  };

  render() {
    const { classes } = this.props
    const { open, profileOpen } = this.state;

    return (
      <>
        <IconButton
          buttonRef={node => {
            this.anchorEl = node;
          }}
          aria-owns={open ? 'menu-list-grow' : undefined}
          aria-haspopup="true"
          onClick={this.handleToggle}
        >
          <Avatar src={this.state.avatar} className={classes.purpleAvatar}>YJ</Avatar>
        </IconButton>
        <Popper open={open} anchorEl={this.anchorEl} transition disablePortal>
          {({ TransitionProps, placement }) => (
            <Grow
              {...TransitionProps}
              id="menu-list-grow"
              style={{ transformOrigin: placement === 'bottom' ? 'center top' : 'center bottom' }}
            >
              <Paper>
                <ClickAwayListener onClickAway={this.handleClose}>
                  <MenuList>
                    <MenuItem onClick={this.handleRequestProfile}>Profile</MenuItem>
                    <MenuItem onClick={this.handleClose}>My account</MenuItem>
                    <MenuItem onClick={this.handleLogout}>Logout</MenuItem>
                  </MenuList>
                </ClickAwayListener>
              </Paper>
            </Grow>
          )}
        </Popper>
{/*        <ProfileModal handleRequestProfileClose={this.handleRequestProfileClose}
                      profileOpen={profileOpen} />*/}
      </>
    );
  }
}
AvatarButton.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(AvatarButton);