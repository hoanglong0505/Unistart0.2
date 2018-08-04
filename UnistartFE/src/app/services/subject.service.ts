import { Injectable } from '@angular/core';
import { Constants } from '../constanst';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Observable, of } from '../../../node_modules/rxjs';
import { catchError } from '../../../node_modules/rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubjectService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  constructor(private http: HttpClient, private constant: Constants) { }
  loadSubject(): Observable<any[]>  {
    return this.http.get<any[]>(this.constant.SUBJECT).pipe(
     catchError(this.handleError<any[]>('loadSubject', null))
   );
 }

 loadGrade(): Observable<any[]>  {
  return this.http.get<any[]>(this.constant.GRADE).pipe(
   catchError(this.handleError<any[]>('loadGrade', null))
 );
}
loadListChapter(sId, gId): Observable<any[]>  {
  return this.http.get<any[]>(this.constant.GET_LIST_CHAPTER + '/' + sId + '/' + gId).pipe(
   catchError(this.handleError<any[]>('loadListChapter', null))
 );
}
}
