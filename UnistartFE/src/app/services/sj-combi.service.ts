import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { SubjectCombination } from '../model/subjectCombination';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SjCombiService {

  constructor(private http: HttpClient) { }

  getSjCombinations(): Observable<SubjectCombination[]> {
    const url = 'http://localhost:8084/Unistart/webresources/model.subjectcombination';
    return this.http.get<SubjectCombination[]>(url).pipe(
      catchError(this.handleError<SubjectCombination[]>('getSubjectCombinations', []))
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
