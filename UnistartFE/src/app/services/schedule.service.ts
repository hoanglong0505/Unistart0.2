import { Injectable } from '@angular/core';
import { Observable, of } from '../../../node_modules/rxjs';
import { Constants } from '../constanst';
import { catchError } from 'rxjs/operators';
import { HttpRequest, HttpResponse } from '../server/http';
import { HttpClient, HttpHeaders } from '../../../node_modules/@angular/common/http';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
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
      list.push({ 'date': element.itemName, 'id': element.id, 'starTime': '', 'endTime': '' });
    });
    return list;
  }
  createClass(data): Observable<HttpResponse> {
    const url = this.constant.GET_IMAGE + '/test';
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
  loadListClass(list) {
    list = [
      {
        'sessionList': [
          {
            'date': 'Thứ 2',
            'id': 1,
            'starTime': '15:00',
            'endTime': '18:00'
          },
          {
            'date': 'Thứ 4',
            'id': 3,
            'starTime': '15:00',
            'endTime': '18:00'
          }
        ],
        'className': 'Toán',
        'color': '#2196F3'
      },
      {
        'sessionList': [
          {
            'date': 'Thứ 3',
            'id': 2,
            'starTime': '15:00',
            'endTime': '18:00'
          },
          {
            'date': 'Thứ 5',
            'id': 4,
            'starTime': '15:30',
            'endTime': '18:45'
          }
        ],
        'className': 'Văn',
        'color': '#F44336'
      },
      {
        'sessionList': [
          {
            'date': 'Th? 7',
            'id': 6,
            'starTime': '08:00',
            'endTime': '10:00'
          },
          {
            'date': 'Chủ Nhật',
            'id': 7,
            'starTime': '07:30',
            'endTime': '09:30'
          }
        ],
        'className': 'Anh',
        'color': '#FFEB3B'
      }
    ];
    return list;
  }
loadChart(list) {
  const listChart = [];
list.forEach(c => {
  c.sessionList.forEach(element => {
    const s = + element.starTime.substring(3, 5);
    const top = (element.starTime.substring(0, 2) * 60 + s - 300) ;
    const h = +element.endTime.substring(3, 5);
    const height =  (element.endTime.substring(0, 2) * 60 + h - 300 - top) ;

    const  chart = {'color' : c.color, 'className': c.className,
    'left': (element.id ) * 12.5 + 0.25, 'time': '(' + element.starTime + '-' + element.endTime + ')',
  'top': top / 1080 * 100, 'height': height / 1080 * 100};

listChart.push(chart);
  });

});

return listChart;
}
}
