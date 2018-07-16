import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Field } from '../model/field';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constanst';

@Injectable({
  providedIn: 'root'
})
export class FieldService {

  constructor(private http: HttpClient, private constant:Constants) { }

  getFields(): Observable<Field[]> {
    const url = this.constant.GET_FIELD_3;
    return this.http.get<Field[]>(url).pipe(
      catchError(this.handleError<Field[]>('getFields', []))
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
