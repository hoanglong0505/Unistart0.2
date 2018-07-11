import { Component, OnInit, Input } from '@angular/core';
import { SchoolsComponent } from '../schools.component';
import { School } from '../../model/school';

@Component({
  selector: 'app-filter-school',
  templateUrl: './filter-school.component.html',
  styleUrls: ['./filter-school.component.css']
})
export class FilterSchoolComponent implements OnInit {

  @Input() schoolsComponent: SchoolsComponent;

  constructor() { }

  ngOnInit() {
  }

  filterSchool(schoolName: string, sjCode: string, minPoint: number, typeId: number, fieldCode: string, location: number) {
    // var schools: School[] = this.schoolsComponent.schools;
    // this.schoolsComponent.schools = schools.slice(1, 2);
  }

  log(f: FormData){
    console.log(f);
  }

}
