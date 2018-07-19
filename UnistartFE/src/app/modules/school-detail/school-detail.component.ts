import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { SchoolService } from '../../services/school.service'
import { School } from '../../model/school';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { AverageRateService } from '../../services/average-rate.service';
import { HttpRequest, Session } from '../../server/http';

@Component({
  selector: 'app-school-detail',
  templateUrl: './school-detail.component.html',
  styleUrls: ['./school-detail.component.css']
})
export class SchoolDetailComponent implements OnInit {
  imageSources = ['a'];
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
    new HttpRequest().getSession(true).set('reload',false);
    WaitingBoxComponent.start();
    this.getSchool();
  }

  getSchool(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.schoolService.getSchool(id)
      .subscribe(school => {
        this.school = school;
        WaitingBoxComponent.stop();
        this.getImage(school.schoolCode);
      });
  }
  getImage(code) {
    this.schoolService.getImage(code)
      .subscribe(images => {
        this.imageSources.splice(0, 1);
        images.forEach(element => {
         this.imageSources.push('assets/School/img/' + code + '/' + element.link);
        });
        WaitingBoxComponent.stop();
        console.log(this.imageSources);
      });
  }

}
