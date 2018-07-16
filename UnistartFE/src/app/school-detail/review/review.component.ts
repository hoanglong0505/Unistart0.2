import { Component, OnInit, Input } from '@angular/core';
import { School } from '../../model/school';
import { SchoolDetailComponent } from '../school-detail.component';
import { RateCriteria } from '../../model/rateCriteria';
import { Rate } from '../../model/rate';
import { RCriteriaService } from '../../services/r-criteria.service';
import { RateService } from '../../services/rate.service';
import { RateDetail } from '../../model/rateDetail';
import { RateDetailPK } from '../../model/rateDetailPK';
import { Users } from '../../model/users';
import { WaitingBoxComponent } from '../../waiting-box/waiting-box.component';
import { CookieManager } from '../../server/cookie-manager';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  @Input() school: School;
  @Input() schoolDetail: SchoolDetailComponent;
  criterias: RateCriteria[];
  rateDetails: RateDetail[];
  userRate: Rate;

  constructor(
    private rCService: RCriteriaService,
    private rateService: RateService,
  ) { }

  ngOnInit() {
    WaitingBoxComponent.start();

    this.rCService.getRateCriterias().subscribe(
      criterias => {
        this.criterias = criterias;
        this.rateDetails = new Array();
        this.criterias.forEach(
          cr => {
            var rd = new RateDetail();
            rd.rateDetailPK = new RateDetailPK();
            rd.rateDetailPK.criteriaId = cr.criteriaId;
            rd.value = 10;
            rd.rateCriteria = cr;
            this.rateDetails.push(rd);
          }
        )
        this.userRate = new Rate();
        this.userRate.rateDetails = this.rateDetails;
        this.userRate.school = this.school;
        this.userRate.anonymous = false;
        this.userRate.encourage = true;
        WaitingBoxComponent.stop();

      }
    );

  }

  sendReview() {
    // console.log(this.userRate);
    if (WaitingBoxComponent.time == -1)
      if (this.userRate) {
        WaitingBoxComponent.start();
        var token = (window as any).token;

        this.userRate.user = new Users();
        this.userRate.user.idToken = token;

        this.rateService.addRate(this.userRate).subscribe(
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
