import { Injectable } from '@angular/core';
import { Observable, of } from '../../../node_modules/rxjs';
import { Constants } from '../constanst';
import { catchError } from 'rxjs/operators';
import { HttpRequest, HttpResponse } from '../server/http';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';
import { Class } from '../model/class';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json; charset=UTF-8'
  })
};
@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(private http: HttpClient, private constant: Constants) { }
  createWeek(week) {
    week = [{
      'dateTitle': 'Thứ Hai', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }
      ]
    },
    {
      'dateTitle': 'Thứ Ba', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }]
    },
    {
      'dateTitle': 'Thứ Tư', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }]
    },
    {
      'dateTitle': 'Thứ Năm', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }]
    },
    {
      'dateTitle': 'Thứ Sáu', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }]
    },
    {
      'dateTitle': 'Thứ Bảy', 'listLession': [{ 'no': 1, 'lessionName': '', 'note': '' },
      { 'no': 2, 'lessionName': '', 'note': '' },
      { 'no': 3, 'lessionName': '', 'note': '' },
      { 'no': 4, 'lessionName': '', 'note': '' },
      { 'no': 5, 'lessionName': '', 'note': '' },
      { 'no': 6, 'lessionName': '', 'note': '' },
      { 'no': 7, 'lessionName': '', 'note': '' },
      { 'no': 8, 'lessionName': '', 'note': '' },
      { 'no': 9, 'lessionName': '', 'note': '' },
      { 'no': 10, 'lessionName': '', 'note': '' }]
    },
    ];
    return week;
  }
  loadDay(days) {
    const list = [];
    days.forEach(element => {
      list.push({ 'date': element.itemName, 'dateNo': element.id, 'startTime': '', 'endTime': '' });
    });
    return list;
  }
  loadSelect(days) {
    const list = [];
    days.forEach(element => {
      list.push({ 'id': element.dateNo, 'itemName': element.date});
    });
    return list;
  }
  createClass(data): Observable<HttpResponse> {
    const url = this.constant.CLASS;
    return this.http.post<HttpResponse>(url, data, httpOptions).pipe(
      catchError(this.handleError<HttpResponse>('sendRate', null))
    );
  }

  UpdateClass(data): Observable<HttpResponse> {
    const url = this.constant.CLASS + '/' + data.classId;
    return this.http.post<HttpResponse>(url, data, httpOptions).pipe(
      catchError(this.handleError<HttpResponse>('sendRate', null))
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
  loadListClass(): Observable<Class[]>  {
     return this.http.get<Class[]>(this.constant.CLASS).pipe(
      catchError(this.handleError<Class[]>('getClasses', null))
    );
  }
  loadClass(id): Observable<Class>  {
    return this.http.get<Class>(this.constant.CLASS + '/' + id).pipe(
     catchError(this.handleError<Class>('getClass', null))
   );
 }
loadChart(list) {
  const listChart = [];
list.forEach(c => {
  c.sessionList.forEach(element => {
    const s = + element.startTime.substring(3, 5);
    const top = (element.startTime.substring(0, 2) * 60 + s - 300) ;
    const h = +element.endTime.substring(3, 5);
    const height =  (element.endTime.substring(0, 2) * 60 + h - 300 - top) ;

    const  chart = {'color' : c.color, 'classId': c.classId , 'className': c.className,
    'left': (element.dateNo ) * 12.5 + 0.25, 'time': '(' + element.startTime + '-' + element.endTime + ')',
  'top': top / 1080 * 100, 'height': height / 1080 * 100};

listChart.push(chart);
  });

});

return listChart;
}
}
