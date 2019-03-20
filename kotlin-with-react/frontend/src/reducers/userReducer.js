export default function reducer(state = {
  user: {},
}, action) {
  switch (action.type) {
    case 'LOGIN_USER': {
      console.log(action.type)
      console.log(action.payload)
      return { ...state }
    }
    default:
      return state
  }
}