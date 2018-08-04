import { Injectable } from '@angular/core';
import { Observable, of } from '../../../node_modules/rxjs';
import { Select } from '../model/select';
import { HttpClient } from '../../../node_modules/@angular/common/http';
import { Constants } from '../constanst';
import { catchError } from '../../../node_modules/rxjs/operators';
import { Class } from '../model/class';

@Injectable({
  providedIn: 'root'
})
export class TinhDiemService {
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  constructor(private http: HttpClient, private constant: Constants) { }
  loadSelects(): Observable<any[]>  {
    console.log(this.constant.SUBJECT_SELECTS);
    return this.http.get<any[]>(this.constant.SUBJECT_SELECTS).pipe(
     catchError(this.handleError<any[]>('loadSelects', null))
   );
 }

 taoDiem(list, hs, mon) {
  const result = [];
  list.forEach(element => {
    result.push(element);
  });
 const diem = {'markValue': null, 'subjectId': mon, 'markType': hs, 'no': list.length + 1};
 result.push(diem);
  return result;
 }
 huyDiem(select, list) {
  const n = [] ;
  list.forEach(element => {
   if (element.no !== select.no) {
    n.push(element);
   }
 });

   return n;
  }
 taoMon(select, list) {
   const result = [];
   list.forEach(element => {
     result.push(element);
   });
  const mon = {'subjectId': select.id, 'subjectName': select.itemName, 'listMart': [], 'average': null};
  result.push(mon);
   return result;
  }
  huyMon(select, list) {
    const n = [] ;
    list.forEach(element => {
     if (element.subjectId !== select.id) {
      n.push(element);
     }
   });
     return n;
    }
}
