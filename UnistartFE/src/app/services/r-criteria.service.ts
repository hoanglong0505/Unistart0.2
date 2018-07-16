import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { RateCriteria } from '../model/rateCriteria';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constanst';

@Injectable({
  providedIn: 'root'
})
export class RCriteriaService {

  constructor(private http: HttpClient, private constant: Constants) { }

  getRateCriterias(): Observable<RateCriteria[]> {
    const url = this.constant.GET_RCRITERIAS;
    return this.http.get<RateCriteria[]>(url).pipe(
      catchError(this.handleError<RateCriteria[]>('getRateCriterias', []))
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
