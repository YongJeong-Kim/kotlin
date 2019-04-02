import React, { Component } from 'react'
import PropTypes from 'prop-types';
import { MuiThemeProvider, createMuiTheme, withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableFooter from '@material-ui/core/TableFooter';
import TablePagination from '@material-ui/core/TablePagination';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import KeyboardArrowLeft from '@material-ui/icons/KeyboardArrowLeft';
import KeyboardArrowRight from '@material-ui/icons/KeyboardArrowRight';
import LastPageIcon from '@material-ui/icons/LastPage';
import TableHead from '@material-ui/core/TableHead';
import Button from '@material-ui/core/Button'
import blue from '@material-ui/core/colors/blue';
import axios from 'axios'
import { observer, inject } from 'mobx-react'

const actionsStyles = theme => ({
  root: {
    flexShrink: 0,
    color: theme.palette.text.secondary,
    marginLeft: theme.spacing.unit * 2.5,
  },
});

class TablePaginationActions extends Component {
  handleFirstPageButtonClick = event => {
    this.props.onChangePage(event, 0);
  };

  handleBackButtonClick = event => {
    this.props.onChangePage(event, this.props.page - 1);
  };

  handleNextButtonClick = event => {
    this.props.onChangePage(event, this.props.page + 1);
    console.log(this.props.page)
  };

  handleLastPageButtonClick = event => {
    this.props.onChangePage(
      event,
      Math.max(0, Math.ceil(this.props.count / this.props.rowsPerPage) - 1),
    );
  };

  render() {
    const { classes, count, page, rowsPerPage, theme } = this.props;

    return (
      <div className={classes.root}>
        <IconButton
          onClick={this.handleFirstPageButtonClick}
          disabled={page === 0}
          aria-label="First Page"
        >
          {theme.direction === 'rtl' ? <LastPageIcon /> : <FirstPageIcon />}
        </IconButton>
        <IconButton
          onClick={this.handleBackButtonClick}
          disabled={page === 0}
          aria-label="Previous Page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowRight /> : <KeyboardArrowLeft />}
        </IconButton>
        <IconButton
          onClick={this.handleNextButtonClick}
          disabled={page >= Math.ceil(count / rowsPerPage) - 1}
          aria-label="Next Page"
        >
          {theme.direction === 'rtl' ? <KeyboardArrowLeft /> : <KeyboardArrowRight />}
        </IconButton>
        <IconButton
          onClick={this.handleLastPageButtonClick}
          disabled={page >= Math.ceil(count / rowsPerPage) - 1}
          aria-label="Last Page"
        >
          {theme.direction === 'rtl' ? <FirstPageIcon /> : <LastPageIcon />}
        </IconButton>
      </div>
    );
  }
}

TablePaginationActions.propTypes = {
  classes: PropTypes.object.isRequired,
  count: PropTypes.number.isRequired,
  onChangePage: PropTypes.func.isRequired,
  page: PropTypes.number.isRequired,
  rowsPerPage: PropTypes.number.isRequired,
  theme: PropTypes.object.isRequired,
};

const TablePaginationActionsWrapped = withStyles(actionsStyles, { withTheme: true })(
  TablePaginationActions,
);

const styles = theme => ({
  root: {
    width: '100%',
    marginTop: theme.spacing.unit * 3,
  },
  table: {
    minWidth: 500,
  },
  tableWrapper: {
    overflowX: 'auto',
  },
});

const CustomTableCell = withStyles(theme => ({
  head: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  body: {
    fontSize: 14,
  },
}))(TableCell);

const theme = createMuiTheme({
  palette: {
    primary: blue,
  },
  typography: { useNextVariants: true },
});

@inject('loginStore')
@observer
class Vacation extends Component {
  state = {
    data: {},
    rowsPerPageOptions: [5, 10, 25],
  };
  componentWillMount = () => {
    console.log(this.props.loginStore.accessToken);
    this.findVacation({
      size: this.state.rowsPerPageOptions[0],
    });
  };

  findVacation = params => {
    axios.get("/api/board/vacation", {
      headers: {
        Authorization: this.props.loginStore.accessToken
      },
      params,
    }).then((response) => {
      this.setState({ data: response.data })
    }).catch(e => {
      console.log('error find board')
    })
  };
  handleChangePage = (event, page) => {
    this.findVacation({
      page,
      size: this.state.data.size,
    });
  };
  handleChangeRowsPerPage = event => {
    this.findVacation({
      number: 0,
      size: event.target.value,
    });
  };
  handleRowClick = (event, row) => {
    // this.props.history.push(`/board/휴가/:${id}`)
    this.props.history.push({
      pathname: `/게시판/휴가/${row.id}`,
      // search: '?query=abc'
      state: { id: row.id },
    });
    console.log(`/게시판/휴가/${row.id}`)
  };
  handlePost = () => {
    this.props.history.push('/게시판/휴가/글쓰기')
  };

  render() {
    const { classes, match } = this.props;
    const { data, rowsPerPageOptions } = this.state;
    const emptyRows = data.size - Math.min(data.size, data.totalElements - data.number * data.size);
    console.log(match)

    return (
      <>
        <MuiThemeProvider theme={theme}>
          <Button variant='outlined' color='primary' onClick={this.handlePost}>{'글쓰기'}</Button>
        </MuiThemeProvider>
        <Paper className={classes.root}>
          <div className={classes.tableWrapper}>
            <Table className={classes.table}>
              <TableHead>
                <TableRow>
                  <CustomTableCell>Title</CustomTableCell>
                  <CustomTableCell align="right">Author</CustomTableCell>
                  <CustomTableCell align="right">Date</CustomTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {Object.entries(data).length !== 0 && data.content.map(row => (
                  <TableRow key={row.id} hover onClick={event => this.handleRowClick(event, row)}>
                    <TableCell component="th" scope="row">
                      {row.subject}
                    </TableCell>
                    <TableCell align="right">{row.username}</TableCell>
                    <TableCell align="right">{row.updateDate}</TableCell>
                  </TableRow>
                ))}
                {emptyRows > 0 && (
                  <TableRow style={{ height: 48 * emptyRows }}>
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
              <TableFooter>
                <TableRow>
                  <TablePagination
                    rowsPerPageOptions={rowsPerPageOptions}
                    colSpan={3}
                    count={data.totalElements === undefined ? 0: data.totalElements}
                    rowsPerPage={data.size === undefined ? 0: data.size}
                    page={data.number === undefined ? 0: data.number}
                    SelectProps={{
                      native: false,
                    }}
                    onChangePage={this.handleChangePage}
                    onChangeRowsPerPage={this.handleChangeRowsPerPage}
                    ActionsComponent={TablePaginationActionsWrapped}
                  />
                </TableRow>
              </TableFooter>
            </Table>
          </div>
        </Paper>
      </>
    );
  }
}

Vacation.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(Vacation);