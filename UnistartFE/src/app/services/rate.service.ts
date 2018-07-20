import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { HttpRequest, HttpResponse } from '../server/http';
import { Rate } from '../model/rate';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constanst';
import { School } from '../model/school';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RateService {

  constructor(private http: HttpClient, private constant: Constants) { }

  getRate(req: HttpRequest): Observable<HttpResponse> {
    //todo
    return null;
  }

  addRate(rate: Rate): Observable<HttpResponse> {
    const url = this.constant.SEND_REVIEW;

    var school = new School();
    school.schoolId = rate.school.schoolId;
    rate.school = school;
    console.log(rate);

    return this.http.post<HttpResponse>(url, rate, httpOptions).pipe(
      catchError(this.handleError<HttpResponse>('addRate', null))
    );
  }

  deleteRate(rate: Rate): Observable<HttpResponse> {
    const url = this.constant.DELETE_REVIEW;

    return this.http.post<HttpResponse>(url, rate, httpOptions).pipe(
      catchError(this.handleError<HttpResponse>('deleteRate', null))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
