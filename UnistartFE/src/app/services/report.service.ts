import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Report } from '../model/report';
import { ReportPK } from '../model/reportPK';
import { Rate } from '../model/rate';
import { catchError } from 'rxjs/operators';
import { HttpResponse } from '../server/http';
import { Constants } from '../constanst';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient, private constant: Constants) { }

  addReport(report: Report): Observable<HttpResponse> {
    const url = this.constant.SEND_REPORT;

    var rateId: number = report.rate.rateId;

    report.rate = null;
    report.rate = new Rate();
    report.reportPK = new ReportPK();
    report.reportPK.rateId = rateId;

    return this.http.post<HttpResponse>(url, report, httpOptions).pipe(
      catchError(this.handleError<HttpResponse>('addReport', null))
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
