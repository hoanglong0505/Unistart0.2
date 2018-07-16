import {Injectable} from '@angular/core';

@Injectable()
export class Constants {
  public HTTP = 'http://';
  public SERVER_IP = 'localhost';
  public SERVER_PORT = ':8084';
  public SERVER_PATH = '/Unistart/webresources/';
  public CLIENT_PORT = ':4200';

  public LOCATION = this.HTTP + this.SERVER_IP + this.SERVER_PORT + this.SERVER_PATH + 'model.location';
  public GET_LOCATION = this.LOCATION + 'getLocations';
}
