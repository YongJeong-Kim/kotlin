import React, { Component } from 'react'
import Divider from "@material-ui/core/Divider/Divider";
import List from "@material-ui/core/List/List";
import ListItem from "@material-ui/core/ListItem/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon/ListItemIcon";
import InboxIcon from '@material-ui/icons/MoveToInbox';
import ListItemText from "@material-ui/core/ListItemText/ListItemText";
import Collapse from "@material-ui/core/Collapse/Collapse";
import PropTypes from "prop-types";
import {withStyles} from "@material-ui/core";
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import StarBorder from '@material-ui/icons/StarBorder';
import MailIcon from '@material-ui/icons/Mail';
import { Link } from 'react-router-dom'

const styles = theme => ({
  toolbar: theme.mixins.toolbar,
  nested: {
    paddingLeft: theme.spacing.unit * 4,
  },
  linkStyles,
});

const linkStyles = {
  textDecoration: 'none',
  color: 'black',
};

const menu = [
  'board1', '휴가', 'board3'
];

class MainMenu extends Component {
  state = {
    open: false,
  };
  handleClick = () => {
    this.setState(state => ({ open: !state.open }))
  };

  render() {
    const { classes } = this.props;
    return (
      <>
        <div className={classes.toolbar} />
        <Divider />
        <List>
          {['Inbox', 'Starred', 'Send email', 'Drafts'].map((text, index) => (
            <Link to={`/nnn/${text}`} key={text + index} style={linkStyles}>
              <ListItem button key={text}>
                <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
                <ListItemText primary={text} />
              </ListItem>
            </Link>
          ))}
        </List>
        <Divider />
        <List>
          {['All mail', 'Trash', 'Spam'].map((text, index) => (
            <ListItem button key={text}>
              <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
              <ListItemText primary={text} />
            </ListItem>
          ))}
          <ListItem button onClick={this.handleClick}>
            <ListItemIcon><InboxIcon /></ListItemIcon>
            <ListItemText primary={'게시판'} />
            {this.state.open ? <ExpandLess /> : <ExpandMore />}
          </ListItem>
          <Collapse in={this.state.open} timeout="auto" unmountOnExit>
            {menu.map((text, index) => (
              <Link to={`/게시판/${text}`} key={'Board' + index + text} style={linkStyles}>
                <List component="div" disablePadding>
                  <ListItem button className={classes.nested}>
                  {/*  <ListItemIcon>
                      <StarBorder />
                    </ListItemIcon>*/}
                    <ListItemText inset primary={text} />
                  </ListItem>
                </List>
              </Link>
            ))}
          </Collapse>
        </List>
      </>
    );
  }
}

MainMenu.propTypes = {
  classes: PropTypes.object.isRequired,
  // Injected by the documentation to work in an iframe.
  // You won't need it on your project.
  theme: PropTypes.object.isRequired,
};

export default withStyles(styles, { withTheme: true })(MainMenu);