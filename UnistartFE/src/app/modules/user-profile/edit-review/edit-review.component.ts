import { Component, OnInit } from '@angular/core';
import { Rate } from '../../../model/rate';
import { RateService } from '../../../services/rate.service';
import { HttpRequest, Session, HttpResponse } from '../../../server/http';
import { School } from '../../../model/school';
import { WaitingBoxComponent } from '../../waiting-box/waiting-box.component';
import { Users } from '../../../model/users';

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {

  userRate: Rate;
  school: School;
  private userId;
  private req: HttpRequest;

  constructor(
    private rateService: RateService
  ) { }

  ngOnInit() {
    this.req = new HttpRequest();    
    this.req.getSession(true).setItem('redirect','/');
    this.getRate();
  }

  getRate() {
    var session: Session = this.req.getSession(true);
    this.req.content = session.getItem('rateId');
    this.userId = session.getItem('gId');

    if (this.req.content && this.userId)
      this.rateService.getRate(this.req).subscribe(
        res => {
          var rate: Rate = JSON.parse(<string>res.content.value);

          if (rate) {
            this.userRate = rate;
            console.log(this.userRate);
            this.school = rate.school;
          } else {
            window.location.replace("/");
          }

        }
      );
    else {
      alert('Hãy chọn 1 bài đánh giá của bạn');
      location.replace("/");
    }
  }

  editReview() {
    if (WaitingBoxComponent.time == -1)
      if (this.userRate) {
        WaitingBoxComponent.start();
        var token = new HttpRequest().getSession(true).getItem('gToken');

        this.userRate.user = new Users();
        this.userRate.user.idToken = token;

        this.rateService.sendRate(this.userRate).subscribe(
          res => {
            switch (res.status) {
              case 200:
                location.replace('/user-profile/' + this.userId);
                this.req.getSession(true).remove('rateId');
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
