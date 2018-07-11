import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Type } from '../model/type';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TypeService {

  constructor(private http: HttpClient) { }

  getTypes(): Observable<Type[]> {
    const url = 'http://localhost:8084/Unistart/webresources/model.type';
    return this.http.get<Type[]>(url).pipe(
      catchError(this.handleError<Type[]>('getTypes', []))
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
