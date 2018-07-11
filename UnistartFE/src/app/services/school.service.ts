import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { School } from '../model/school';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SchoolService {

  constructor(private http: HttpClient) { }

  getSchools(): Observable<School[]> {
    const url='http://localhost:8084/Unistart/webresources/model.school';
    return this.http.get<School[]>(url).pipe(
      catchError(this.handleError<School[]>('getSchools',[]))
    );
  }

  filterSchool(schoolName: string, sjCode: string, minPoint: number,
    typeId: number, fieldCode: string, location: number): Observable<School[]> {
      const url=`http://localhost:8084/Unistart/webresources/model.school/filter-school?`+
      `schoolName=${schoolName}&sjCode=${sjCode}&minPoint=${minPoint}`+
      `&typeId=${typeId}&fieldCode=${fieldCode}&location=${location}`;

      return this.http.get<School[]>(url).pipe(
        catchError(this.handleError<School[]>('filterSchool',[]))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
   
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
   
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
