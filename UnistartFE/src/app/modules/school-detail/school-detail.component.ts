import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { SchoolService } from '../../services/school.service'
import { School } from '../../model/school';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { AverageRateService } from '../../services/average-rate.service';

@Component({
  selector: 'app-school-detail',
  templateUrl: './school-detail.component.html',
  styleUrls: ['./school-detail.component.css']
})
export class SchoolDetailComponent implements OnInit {

  school: School;
  INFO: number = 1;
  REVIEW: number = 2;
  ABOUT: number = 3;
  mode: number = this.INFO;

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private schoolService: SchoolService,
  ) { }

  ngOnInit() {
    sessionStorage.setItem('reload','false');
    WaitingBoxComponent.start();
    this.getSchool();
  }

  getSchool(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.schoolService.getSchool(id)
      .subscribe(school => {
        this.school = school;
        WaitingBoxComponent.stop();

      });
  }

}
