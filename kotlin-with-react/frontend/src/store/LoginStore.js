import { decorate, observable, action } from 'mobx'
import { observer } from 'mobx-react'

class LoginStore {
  accessToken = 'qqqaaasss';
  setAccessToken = accessToken => {
    this.accessToken = accessToken;
  };
  getAccessToken = () => {
    return this.getAccessToken
  };
}

decorate(LoginStore, {
  accessToken: observable,
  setAccessToken: action,
  getAccessToken: action,
});

export default observer(LoginStore)