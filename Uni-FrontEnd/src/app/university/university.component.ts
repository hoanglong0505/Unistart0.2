import { Component, OnInit } from '@angular/core';
import { UniversitytService } from '../service/university.service';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css']
})
export class UniversityComponent implements OnInit {
  university: any;
  count: number;
  constructor(private universityService: UniversitytService) { }

  ngOnInit() {
    this.count = 1;
    this.loadProduct();
  }
  loadProduct() {
    this.universityService.getList().then((res: any) => {
      this.university = res;
      console.log(res);
    }).catch(err => {
      alert(err);
    });
  }
  showmore() {
    this.count++;
  }
}
