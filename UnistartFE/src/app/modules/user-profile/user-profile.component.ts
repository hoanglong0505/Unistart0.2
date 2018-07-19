import { Component, OnInit } from '@angular/core';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { Users } from '../../model/users';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Rate } from '../../model/rate';
import { RateService } from '../../services/rate.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: Users;
  authorUser: boolean;
  private interval: any;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private rateService: RateService
  ) { }

  ngOnInit() {
    sessionStorage.setItem('reload','true');
    WaitingBoxComponent.start();
    this.getUser();
  }

  checkUser() {
    // console.log(this.user);
    var comp: UserProfileComponent = this;
    this.interval = setInterval(function () {
      if (sessionStorage.getItem('gId')) {
        if (sessionStorage.getItem('gId') == comp.user.userId)
          comp.authorUser = true;
        else comp.authorUser = false;
        clearInterval(comp.interval);
      }
    }, 500);
  }

  getUser() {
    const id = this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(
      user => {
        this.user = user;
        WaitingBoxComponent.stop();
        this.checkUser();
      }
    );
  }

  deleteRate(r: Rate) {
    var confirm: boolean = window.confirm('Bạn chắc chắn muốn xóa đánh giá này?');
    if (confirm) {
      WaitingBoxComponent.start();
      var rate: Rate = new Rate();
      rate.rateId = r.rateId;
      rate.user = new Users();
      rate.user.idToken = sessionStorage.getItem('gToken');

      this.rateService.deleteRate(rate).subscribe(
        res => {
          switch (res.status) {
            case 200:
              location.reload();
              break;
            default:
              window.alert(res.content);
          }
          WaitingBoxComponent.stop();
        }
      );
    }
  }

}
