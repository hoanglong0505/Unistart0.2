import { Component, OnInit } from '@angular/core';
import { SubjectService } from '../../services/subject.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  subjects = [];
  select: any;
  grades = [];
  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
    this.reload();
  }
  reload() {
    this.subjectService.loadSubject()
    .subscribe(c => { console.log(c);
            this.subjects = c;
       }
  );
  this.subjectService.loadGrade()
    .subscribe(c => { console.log(c);
            this.grades = c;
       }
  );
}
setSelect(sj) {
this.select = sj;
console.log(this.select);
}
}
