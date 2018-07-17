import { Injectable } from '@angular/core';
import { Constants } from '../constanst';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { catchError } from '../../../node_modules/rxjs/operators';
import { Question } from '../model/question';

@Injectable({
  providedIn: 'root'
})
export class MbtiService {

  constructor(private http: HttpClient, private constant: Constants) { }
  getQuestion(): Observable<Question[]> {
    const url = this.constant.GET_QUESTIONS;
    return this.http.get<Question[]>(url).pipe(

    );
  }
  getAnserByQuestion(id): Observable<any[]> {
    const url = this.constant.GET_ANSWERS_BY_QUESTION + id;
    return this.http.get<any[]>(url).pipe(
    );
  }
  handleError<T>(arg0: any, arg1: any): any {
    throw new Error('Method not implemented.');
  }
}
