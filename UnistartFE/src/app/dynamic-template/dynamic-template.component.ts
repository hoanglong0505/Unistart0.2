import { Component, OnInit, Input } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Component({
  selector: 'app-dynamic-template',
  templateUrl: './dynamic-template.component.html',
  styleUrls: ['./dynamic-template.component.css']
})
export class DynamicTemplateComponent implements OnInit {

  @Input() url: string;
  private container: Element;

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.container = document.getElementById("dynamic-template");
    this.change(this.url);
  }

  change(url: string) {
    this.http.get(url, { responseType: 'text' })
      .pipe(catchError(this.handleError('changeContent')))
      .subscribe(
      res => this.container.innerHTML = res);
  }

  private handleError(operation = 'operation', result?: string) {
    return (error: HttpErrorResponse): Observable<string> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      result = error.message;
      // Let the app keep running by returning an empty result.
      return of(result);
    };
  }

}
