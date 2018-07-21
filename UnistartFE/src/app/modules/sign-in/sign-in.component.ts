import { Component, OnInit } from '@angular/core';
import { HttpRequest, Session } from '../../server/http';
@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  constructor() { }

  ngOnInit() {

  }

  ele(id): HTMLElement {
    return document.getElementById(id);
  }

  display: boolean = false;
  showDropDown() {
    if (!this.display) {
      this.ele('drop-down').style.display = 'block';
      this.display = true;
    } else {
      this.ele('drop-down').style.display = 'none';
      this.display = false;
    }
  }

  goToProfile() {
    var gId: string = new HttpRequest().getSession(true).getItem('gId');
    window.location.replace('/personal/user-profile/' + gId);
  }

}
