import { Component, OnInit, Input } from '@angular/core';
import { SchoolsComponent } from '../schools.component';
import { School } from '../../../model/school';
import { SchoolService } from '../../../services/school.service';
import { FieldService } from '../../../services/field.service';
import { LocationService } from '../../../services/location.service';
import { SjCombiService } from '../../../services/sj-combi.service';
import { TypeService } from '../../../services/type.service';
import { NgForm } from '@angular/forms'
import { Field } from '../../../model/field';
import { Location } from '../../../model/location';
import { SubjectCombination } from '../../../model/subjectCombination';
import { Type } from '../../../model/type';

@Component({
  selector: 'app-filter-school',
  templateUrl: './filter-school.component.html',
  styleUrls: ['./filter-school.component.css']
})
export class FilterSchoolComponent implements OnInit {

  @Input() schoolsComponent: SchoolsComponent;
  fields: Field[];
  locations: Location[];
  sjCombinations: SubjectCombination[];
  types: Type[];

  constructor(
    private schoolService: SchoolService,
    private fieldService: FieldService,
    private locationService: LocationService,
    private sjCombiService: SjCombiService,
    private typeService: TypeService
  ) { }

  ngOnInit() {
    this.fieldService.getFields().subscribe(
      fields => this.fields = fields
    );
    this.locationService.getLocations().subscribe(
      locations => this.locations = locations
    );
    this.sjCombiService.getSjCombinations().subscribe(
      sjCombinations => this.sjCombinations = sjCombinations
    );
    this.typeService.getTypes().subscribe(
      types => this.types = types
    );
  }

  filterSchool(form: NgForm) {
    var f = form.value;
    this.schoolService.
      filterSchool(f.schoolName, f.sjCode, f.minPoint, f.typeId, f.fieldCode, f.location).
      subscribe(schools => {
        this.schoolsComponent.schools = schools;
        this.schoolsComponent.lastPages = Math.ceil(schools.length / this.schoolsComponent.maxItemsPerPage);
        this.schoolsComponent.pages = 1;
        console.log(this.schoolsComponent.lastPages);
      });
  }

}
