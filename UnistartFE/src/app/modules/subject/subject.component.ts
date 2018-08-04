import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';
import { SubjectService } from '../../services/subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {
chapters = [];
  constructor(
     private route: ActivatedRoute,
     private subjectService: SubjectService) { }

  ngOnInit() {
  this.loadListChapter();
  }
loadListChapter() {
  const sub = +this.route.snapshot.paramMap.get('sub');
  const grade = +this.route.snapshot.paramMap.get('grade');
  this.subjectService.loadListChapter(sub, grade)
   .subscribe(c => { this.chapters = c;
     console.log(c);
       }
  );
}
}
