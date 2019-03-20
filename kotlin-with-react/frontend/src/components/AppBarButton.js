import React from 'react'
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';

import AvatarButton from "./appbar/AvatarButton";

const styledButton = {
  color: 'white',
};

export const AppBarButton = () => (
  <>
    <IconButton aria-label="Delete" style={styledButton}>
      <DeleteIcon />
    </IconButton>
    <IconButton aria-label="Delete" style={styledButton}>
      <DeleteIcon />
    </IconButton>
    <IconButton aria-label="Delete" style={styledButton}>
      <DeleteIcon />
    </IconButton>
    <AvatarButton />
  </>
);
