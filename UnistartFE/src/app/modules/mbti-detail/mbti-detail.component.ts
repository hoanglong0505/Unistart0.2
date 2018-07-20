import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-mbti-detail',
  templateUrl: './mbti-detail.component.html',
  styleUrls: ['./mbti-detail.component.css']
})
export class MbtiDetailComponent implements OnInit {
code = '';
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
  this.code = this.route.snapshot.paramMap.get('code');
    console.log(this.code);
  }

}
