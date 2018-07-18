import { Component, OnInit } from '@angular/core';
import { School } from '../../model/school';
import { SchoolService } from '../../services/school.service';
import { WaitingBoxComponent } from '../waiting-box/waiting-box.component';

@Component({
  selector: 'app-schools',
  templateUrl: './schools.component.html',
  styleUrls: ['./schools.component.css']
})
export class SchoolsComponent implements OnInit {

  schools: School[] = [];
  //page area
  pages: number = 1;
  maxItemsPerPage: number = 48;
  firstItem: number = this.pages * this.maxItemsPerPage - this.maxItemsPerPage;;
  lastItem: number = this.pages * this.maxItemsPerPage;
  lastPages: number = 1;
  //--------

  constructor(private schoolService: SchoolService) { }

  ngOnInit() {
    sessionStorage.setItem('reload','false');
    WaitingBoxComponent.start();
    this.getSchools();
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
