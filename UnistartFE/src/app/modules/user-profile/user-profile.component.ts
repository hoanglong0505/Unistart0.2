import { Component, OnInit } from '@angular/core';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { Users } from '../../model/users';
import { UserService } from '../../services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Rate } from '../../model/rate';
import { RateService } from '../../services/rate.service';
import { HttpRequest, Session } from '../../server/http';
import { ReportableComponent } from '../../model/reportable';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent extends ReportableComponent implements OnInit {

  user: Users;
  authorUser: boolean;
  private request: HttpRequest;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private rateService: RateService
  ) {
    super();
  }

  ngOnInit() {
    this.request = new HttpRequest();
    var session = this.request.getSession(true);
    session.set('reload', true);
    WaitingBoxComponent.start();
    this.getUser();
  }

  checkUser() {
    // console.log(this.user);
    var comp: UserProfileComponent = this;
    var session = this.request.getSession(true);
    if (session.get('gId')) {
      if (session.get('gId') == comp.user.userId)
        comp.authorUser = true;
      else comp.authorUser = false;
    }
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
      var session = this.request.getSession(true);
      WaitingBoxComponent.start();
      var rate: Rate = new Rate();
      rate.rateId = r.rateId;
      rate.user = new Users();
      rate.user.idToken = session.get('gToken');

      console.log(rate.user.idToken);

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
