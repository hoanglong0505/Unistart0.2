import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {AuthService} from 'angular2-social-login';
import {LoginService} from '../../service/login/login.service';
import {UniversityService} from "../../service/university/university.service";
import { StompService } from 'ng2-stomp-service';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import {Router} from "@angular/router";
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {
  public user;
  sub: any;
  private url;
  public count = 0;
  private serverUrl = 'http://localhost:8080/unistart/socket';
  private title = 'WebSockets chat';
  private stompClient;
  constructor(private router: Router, private uniService: UniversityService,
              private loginService: LoginService,
              private auth: AuthService,private cdRef:ChangeDetectorRef) {
    router.events.subscribe((data:any) => { this.url = data.url; });
  }

  ngOnInit() {
    this.initializeWebSocketConnection();
    this.getUser();
    this.getCount();
    if(!this.user){
      this.user = JSON.parse(localStorage.getItem('currentUser'));
    }
    console.log(this.url);
    if(this.url == '/'){
      this.router.navigate(['home'])
    }
    if(this.user){
      this.uniService.getQuestionByUser(this.user.id).subscribe(res=>{
        if(res){
          for(let i = 0; i<res.length; i++){
            this.count = this.count + res[i].count;
          }
        }
      });
    }
  }
  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    console.log(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      if(that.user){
        that.stompClient.subscribe("/notify/" + that.user.id, (message) => {
          if(message.body) {
            var answer = JSON.parse(message.body);
            var str = '/question-detail/' + answer.parentId;
            console.log(str);
            if(that.url != str){
              that.count = that.count + 1;
              that.cdRef.detectChanges();
            }
          }
        });
      }
    });
  }

  public getUser(): void {
    this.loginService.space.subscribe(value => {
      this.user = value;
      this.cdRef.detectChanges();
      this.initializeWebSocketConnection();
    });
  }

  public getCount(): void {
    this.uniService.title.subscribe(value=>{
      this.count = value;
      this.cdRef.markForCheck();
    })
  }
  public logout(value) {
    if(value){
      this.auth.logout().subscribe(
        (data) => {
          this.user = null;
          this.cdRef.detectChanges();
          this.loginService.setLogin(false);
          this.loginService.broadcastTextChange(this.user);
          window.location.replace('/home');
          localStorage.removeItem('currentUser');
        }
      );
    }else{
      this.user = null;
      this.cdRef.detectChanges();
      this.loginService.setLogin(false);
      this.loginService.broadcastTextChange(this.user);
      window.location.replace('/home');
      localStorage.removeItem('currentUser');
    }
  }
  public clickLink(){
    document.getElementById('linkFake').click();
  }
  public clickLinkFavorite(){
    document.getElementById('linkFakeFavorite').click();
  }
  clickLinkQuestion(){
    document.getElementById('linkFakeQuestion').click();
  }
}
