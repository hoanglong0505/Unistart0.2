import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../shared/user.model';
import { Report } from '../shared/report.model';
declare const gapi: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public auth2: any;
  user: User;
  report: Report[];
  r: Report;
  private name: string;
  private email: string;
  private avatar: string;
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
   this.load();
    console.log(this.user);
  }
load() {
 this.name = 'aa';
 this.user = new User;
 this.user.name = 'aa';
 this.user.userId = 1;
 this.r = new Report;
 this.r.user = new User;
 this.r.user.userId = this.user.userId;
 this.user.report = this.r;
   // this.r.user = this.user;

  //  this.report.pop( this.r );
  //   this.report.push( this.r );
  //   this.user.reportList = this.report;
}
  public googleInit() {
    gapi.load('auth2', () => {
      this.auth2 = gapi.auth2.init({
        client_id: '1041280345800-ievuree53gfvp83q0ap7uiipc8o6vbgk.apps.googleusercontent.com',
        cookie_policy: 'single_host_origin',
        scope: 'profile email'
      });
      this.attachSignin(document.getElementById('googleBtn'));
    });
  }
  public attachSignin(element) {
    this.auth2.attachClickHandler(element, {},
      (googleUser) => {
        const profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        this.user = new User;
        this.user.name = profile.getName();
        this.user.email = profile.getEmail();
        console.log('Email: ' + this.email);
        this.user.avatar = profile.getImageUrl();
      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });
  }

// tslint:disable-next-line:use-life-cycle-interface
ngAfterViewInit() {
      this.googleInit();
}
test() {
  console.log('Name: ' + this.user.name);
}
}
