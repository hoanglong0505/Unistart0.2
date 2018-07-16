import { Component, OnInit, Input } from '@angular/core';
import { School } from '../../model/school';
import { SchoolDetailComponent } from '../school-detail.component';
import { ReportComponent } from './report/report.component';
import { Rate } from '../../model/rate';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  @Input() school: School;
  @Input() schoolDetail: SchoolDetailComponent;
  reportComp: ReportComponent;

  constructor() { }

  ngOnInit() {
  }

  turnOnReport(rate: Rate) {
    this.reportComp.rpRate = rate;
  }

}
