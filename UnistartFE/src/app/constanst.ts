import { Injectable } from '@angular/core';

@Injectable()
export class Constants {
  private HTTP = 'http://';
  private SERVER_IP = 'localhost';
  private SERVER_PORT = ':8084/';
  private SERVER_PATH = 'Unistart/';
  private RESOURCES_PATH = 'webresources/';
  private CLIENT_PORT = ':4200/';

  private SERVICE_PATH = this.HTTP + this.SERVER_IP + this.SERVER_PORT + this.SERVER_PATH + this.RESOURCES_PATH;
  private SERVER_URL = this.HTTP + this.SERVER_IP + this.SERVER_PORT + this.SERVER_PATH;

  //location
  private LOCATION = this.SERVICE_PATH + 'model.location';
  public GET_LOCATIONS = this.LOCATION;

  //field
  private FIELD = this.SERVICE_PATH + 'model.field';
  public GET_FIELD_3 = this.FIELD + '/field-type/3';

  //rate-criteria
  private RATE_CRITERIA = this.SERVICE_PATH + 'model.ratecriteria';
  public GET_RCRITERIAS = this.RATE_CRITERIA;

  //rate
  private RATE = this.SERVICE_PATH + 'model.rate';
  public SEND_REVIEW = this.SERVER_URL + 'send-review';

  //report
  private REPORT = this.SERVICE_PATH + 'model.report';
  public SEND_REPORT = this.SERVER_URL + 'send-report';

  //school
  private SCHOOL = this.SERVICE_PATH + 'model.school';
  public GET_SCHOOLS = this.SCHOOL;
  public GET_SCHOOL_BY_ID = this.SCHOOL + '/';
  public FILTER_SCHOOL = this.SCHOOL+ '/filter-school?';

  //subject combination
  private SUBJECT_COMBINATION = this.SERVICE_PATH + 'model.subjectcombination';
  public GET_SJ_COMBINATIONS = this.SUBJECT_COMBINATION;

  //type
  private TYPE = this.SERVICE_PATH + 'model.type';
  public GET_TYPES = this.TYPE;

}