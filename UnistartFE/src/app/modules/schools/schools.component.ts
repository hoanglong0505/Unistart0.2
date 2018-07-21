import { Component, OnInit } from '@angular/core';
import { School } from '../../model/school';
import { SchoolService } from '../../services/school.service';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';
import { LocationService } from '../../services/location.service';
import { TypeService } from '../../services/type.service';
import { HttpRequest, HttpResponse } from '../../server/http';

@Component({
  selector: 'app-schools',
  templateUrl: './schools.component.html',
  styleUrls: ['./schools.component.css']
})
export class SchoolsComponent implements OnInit {
  //filter location & type school - Quy 20/7
  locationSelected = [];
  typeSchoolSelected = [];

  dropdownLocationSettings = {};
  dropdownTypeSettings = {};

  // Array lists value of locations and typeschools
  dropDownLocationList = [];
  dropDownTypeList = [];

  schools: School[] = [];
  //page area
  pages: number = 1;
  maxItemsPerPage: number = 48;
  firstItem: number = this.pages * this.maxItemsPerPage - this.maxItemsPerPage;;
  lastItem: number = this.pages * this.maxItemsPerPage;
  lastPages: number = 1;
  //--------

  constructor(private schoolService: SchoolService,
    private locationService: LocationService,
    private typeService: TypeService
  ) { }
  ngOnInit() {
    new HttpRequest().getSession(true).setItem('reload', false);
    WaitingBoxComponent.start();
    this.getSchools();

    // Quy 20/7 filter location & typeschool
    this.dropdownLocationSettings = {
      singleSelection: true,
      text: "Thành Phố",
      selectAllText: "Tất Cả",
      unSelectAllText: "Bỏ Chọn Tất Cả",
      classes: "locationclass custom-location",
    }
    this.dropdownTypeSettings = {
      singleSelection: true,
      text: "Hệ Trường",
      selectAllText: "Tất Cả",
      unSelectAllText: "Bỏ Chọn Tất Cả",
      classes: "locationclass custom-location",
    }

    this.locationService.getLocations().subscribe(
      locations => this.dropDownLocationList = locations.map(e => ({
        id: e.locationId,
        itemName: e.locationName
      }))
    );
    this.typeService.getTypes().subscribe(
      types => this.dropDownTypeList = types.map(e => ({
        id: e.typeId,
        itemName: e.typeName
      }))
    );
    //====>>>>
  }


  // Quy 20/7 - method do action in multiple selected
  onItemSelect(item: any) {
    console.log(item);
    console.log(this.locationSelected);
  }
  OnItemDeSelect(item: any) {
    console.log(item);
    console.log(this.locationSelected);
  }
  onSelectAll(items: any) {
    console.log(items);
  }
  onDeSelectAll(items: any) {
    console.log(items);
  }

  getSchools() {
    this.schoolService.getSchools().subscribe(
      schools => {
        this.schools = schools
        this.lastPages = Math.ceil(this.schools.length / this.maxItemsPerPage);
        console.log(this.lastPages);
        WaitingBoxComponent.stop();

      }
    );
  }

  nextPage() {
    if (this.pages < this.lastPages)
      this.pages++;
  }
  previousPage() {
    if (this.pages > 1)
      this.pages--;
  }

}
