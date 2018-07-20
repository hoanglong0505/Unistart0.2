import { Component, OnInit } from '@angular/core';
import { Rate } from '../../../model/rate';
import { RateService } from '../../../services/rate.service';
import { HttpRequest, Session, HttpResponse } from '../../../server/http';

@Component({
  selector: 'app-edit-review',
  templateUrl: './edit-review.component.html',
  styleUrls: ['./edit-review.component.css']
})
export class EditReviewComponent implements OnInit {

  editRate: Rate;

  constructor(
    private rateService: RateService
  ) { }

  ngOnInit() {

  }

  getRate() {
    var req: HttpRequest = new HttpRequest();
    var session: Session = req.getSession(true);
    req.content = session.get('rateId');
    session.remove('rateId');

    this.rateService.getRate(req).subscribe();
  }

}
