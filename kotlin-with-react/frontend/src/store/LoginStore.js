import { observable, action } from 'mobx'

export default class LoginStore {
  @observable accessToken = 'default';
  @action setAccessToken = accessToken => {
    this.accessToken = accessToken;
  };
  @action getAccessToken = () => {
    return this.accessToken
  };
}