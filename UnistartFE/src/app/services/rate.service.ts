import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Rate } from '../model/rate';
import { catchError } from 'rxjs/operators';
import { HttpResponse } from '../server/httpResponse';
import { CookieManager } from '../server/cookie-manager';
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

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
