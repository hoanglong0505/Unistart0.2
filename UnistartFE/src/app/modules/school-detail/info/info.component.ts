import { Component, OnInit, Input } from '@angular/core';
import { School } from '../../../model/school';
import { SchoolDetailComponent } from '../school-detail.component';
import { ReportableComponent } from '../../../model/reportable';
import { Rate } from '../../../model/rate';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent extends ReportableComponent implements OnInit {

  @Input() school: School;
  @Input() schoolDetail: SchoolDetailComponent;

  constructor() {
    super();
  }

  ngOnInit() {
  }

}
