import { Component, OnInit, Input } from '@angular/core';
import { SchoolsComponent } from '../schools.component';
import { School } from '../../../model/school';
import { SchoolService } from '../../../services/school.service';
import { FieldService } from '../../../services/field.service';
import { LocationService } from '../../../services/location.service';
import { SjCombiService } from '../../../services/sj-combi.service';
import { TypeService } from '../../../services/type.service';
import { NgForm, FormGroup, FormBuilder, Validators } from '@angular/forms'
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

  // Selected Item in multiple-selected
  selectedItems: any = {
    "selectedLocationItems": [],
    "selectedFieldItems": [],
    "selectedSjCombiItems": [],
    "selectedTypeItems": [],
  }

  // Setting for multiple selected
  dropdownSearchSettings = {};
  dropdownSettings = {};
  schoolName: string;
  minPoint: any = 0;
  //object contain the value selected
  filterForm: FormGroup;
  //Data for loading in  multiple selected
  dropDownList: any = {
    "fieldList": [],
    "locationList": [],
    "sjCombiList": [],
    "typeList": [],
  };
  constructor(
    private schoolService: SchoolService,
    private fieldService: FieldService,
    private locationService: LocationService,
    private sjCombiService: SjCombiService,
    private typeService: TypeService,
    private fb: FormBuilder,
  ) { this.createForm() }

  ngOnInit() {
     // Field Service
     this.fieldService.getFields().subscribe(
      fields => this.dropDownList.fieldList = fields.map(e => ({
        id: e.fieldId,
        itemName: e.fieldName,
        itemCode: e.fieldCode,
      }))
    );
    // Location service
    this.locationService.getLocations().subscribe(
      locations => this.dropDownList.locationList = locations.map(e => ({
        id: e.locationId,
        itemName: e.locationName
      }))
    );
    // sjCombi Service
    this.sjCombiService.getSjCombinations().subscribe(
      sjCombinations => this.dropDownList.sjCombiList = sjCombinations.map(e => ({
        id: e.sjCombiName,
        itemName: e.sjCombiCode
      }))
    );
    // Type Service
    this.typeService.getTypes().subscribe(
      types => this.dropDownList.typeList = types.map(e => ({
        id: e.typeId,
        itemName: e.typeName
      }))
    );

    //do setting drop down list
    this.dropdownSettings = {
      singleSelection: false,
      text: "Chọn",
      selectAllText: "Tất Cả",
      unSelectAllText: "Bỏ Chọn Tất Cả",
      classes: "myclass custom-location",
      badgeShowLimit: 4
    }
    this.dropdownSearchSettings = {
      singleSelection: false,
      text: "Chọn",
      searchPlaceholderText: "Tìm Kiếm",
      enableCheckAll: false,
      enableSearchFilter: true,
      classes: "myclass custom-location",
      badgeShowLimit: 4,
      limitSelection: 4,
      disable: false
    }
  }
  // get selected value from the filterFrom
  createForm() {
    this.filterForm = this.fb.group({
      school: '',
      location: [],
      field: [],
      sjcombi: [],
      type: [],
      minPoint: ''
    })
  }
  // method support multiple-selected
  onItemSelect(item: any) {
    console.log(item);
    console.log(this.selectedItems);
  }
  OnItemDeSelect(item: any) {
    console.log(item);
    console.log(this.selectedItems);
  }
  onSelectAll(items: any) {
    console.log(items);
  }
  onDeSelectAll(items: any) {
    console.log(items);
  }

  // filterSchool(form: NgForm) {
  //   var f = form.value;
  //   this.schoolService.
  //     filterSchool(f.schoolName, f.sjCode, f.minPoint, f.typeId, f.fieldCode, f.location).
  //     subscribe(schools => {
  //       this.schoolsComponent.schools = schools;
  //       this.schoolsComponent.lastPages = Math.ceil(schools.length / this.schoolsComponent.maxItemsPerPage);
  //       this.schoolsComponent.pages = 1;
  //       console.log(this.schoolsComponent.lastPages);
  //     });
  // }
 //do submit form get filter-school with value get from filterForm
 submitForm() {
  let schoolFilter = {
    "schoolName": this.filterForm.value.school,
    "location": this.filterForm.value.location.map(e => (e.id)),
    "field": this.filterForm.value.field.map(e => (e.itemCode)),
    "typeSchool": this.filterForm.value.type.map(e => (e.id)),
    "sjCombi": this.filterForm.value.sjcombi.map(e => (e.itemName)),
    "minPoint": this.filterForm.value.minPoint,
  }
  console.log(schoolFilter);
  // call schoolService to do filter
  this.schoolService.filterSchoolPost(schoolFilter).subscribe(schools => {
    this.schoolsComponent.schools = schools;
    this.schoolsComponent.lastPages = Math.ceil(schools.length / this.schoolsComponent.maxItemsPerPage);
    this.schoolsComponent.pages = 1;
  }
  );
}
// reset all value input
resetValue() {
  console.log(this.dropDownList);
  this.minPoint = 0;
  this.schoolName = "";
  this.selectedItems = {
    "selectedLocationItems": [],
    "selectedFieldItems": [],
    "selectedSjCombiItems": [],
    "selectedTypeItems": [],
  }
}

// check input Number change
onKeydown(event) {
  var regex = /^[0-3][.]*$/;
  var number = this.minPoint + event.key
  if (event.key == "Backspace") {
    //event press backspace
  } else {
    if (parseFloat(number) > 30 || this.minPoint.length > 6) {
      event.preventDefault();
      this.minPoint = "0";
    }
    else if (event.key === '+') {
      event.preventDefault();
      if (parseFloat(this.minPoint) >= 30) {
        this.minPoint = "30";
      } else {
        this.minPoint = parseFloat(this.minPoint) + 1 + "";
      }
    }
    else if (event.key === '-') {
      event.preventDefault();
      if (this.minPoint !== "0") {
        this.minPoint = parseFloat(this.minPoint) - 1 + "";
      } else {
        this.minPoint = "0";
      }
    }
    else if (this.minPoint == "0" || this.minPoint == undefined) {
      event.preventDefault();
      this.minPoint = event.key;
    }
    else if (event.key === '.' || event.key === ',') {
      event.preventDefault();
      var str = this.minPoint + event.key
      this.minPoint = str;
    }
    else {
      event.preventDefault();
      if (event.key.match(regex)) {
        if (this.minPoint == "0" || this.minPoint == undefined) {
          this.minPoint = event.key;
        }
        else {
          this.minPoint += event.key
        }
      }
    }
  }
}
}
