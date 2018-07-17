import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { AverageRate } from '../model/averageRate';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constanst';

@Injectable({
  providedIn: 'root'
})
export class AverageRateService {

  constructor(private http: HttpClient, private constant: Constants) { }

  // getFields(): Observable<AverageRate[]> {
  //   return this.http.get<AverageRate[]>(url).pipe(
  //     catchError(this.handleError<AverageRate[]>('getAverageRates', []))
  //   );
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
