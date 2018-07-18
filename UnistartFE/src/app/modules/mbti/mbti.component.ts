import { Component, OnInit } from '@angular/core';
import { MbtiService } from '../../services/mbti.service';
import { Question } from '../../model/question';
import { Router } from '../../../../node_modules/@angular/router';

@Component({
  selector: 'app-mbti',
  templateUrl: './mbti.component.html',
  styleUrls: ['./mbti.component.css']
})
export class MbtiComponent implements OnInit {
  questions: Question[] = [];
  quests: Question[] = [];
  finish = false;

  constructor(private mbtiService: MbtiService, private router: Router) { }

  ngOnInit() {
    sessionStorage.setItem('reload', 'false');
    this.loadQuestion();
  }
  loadQuestion() {
    this.mbtiService.getQuestion().subscribe(
      res => this.questions = res
    );
    this.loadAnswer();
  }
  loadAnswer() {
    this.questions.forEach(q => {
      this.mbtiService.getAnserByQuestion(q.questionId).subscribe(
        res => q.listAnswer = res
      );
    });
    this.quests = this.questions;
    console.log(this.quests);
  }
  load() {
    this.loadQuestion();
    this.quests = this.questions;
  }
  check(question, code, check) {
    if (check) {
    question.select = code;
    } else {
      question.select = '';
    }
    let s = '';
    this.quests.forEach(element => {
      if (element.select !== undefined) {
        s = s + element.select;
      }

    });
    this.finish = (s.length === this.quests.length);


  }

  getcode() {
    let code = '';
    this.quests.forEach(element => {
      if (element.select !== undefined) {
        code = code + element.select;
      }

    });
    console.log(code);
    let E = 0;
    let S = 0;
    let T = 0;
    let J = 0;
    let I = 0;
    let N = 0;
    let F = 0;
    let P = 0;
    for (let i = 0; i < code.length; i++) {
      const char = code.charAt(i);
      switch (char) {
        case ('E'): E++;
          break;
        case ('I'): I++;
          break;
        case ('S'): S++;
          break;
        case ('N'): N++;
          break;
        case ('T'): T++;
          break;
        case ('F'): F++;
          break;
        case ('J'): J++;
          break;
        case ('P'): P++;
          break;
      }
    }
    console.log(E + 'E ' + (E / (E + I) * 100) + '% ' + I, S, N, T, F, J, P);
    let EI = 'E';
    if (I > E) {
      EI = 'I';
    }
    let SN = 'N';
    if (S > N) {
      SN = 'S';
    }
    let TF = 'F';
    if (T > F) {
      TF = 'T';
    }
    let JP = 'P';
    if (J > P) {
      JP = 'J';
    }
    console.log(EI + SN + TF + JP);
    this.router.navigate(['/mbti-detail/' + EI + SN + TF + JP]);
  }
}
