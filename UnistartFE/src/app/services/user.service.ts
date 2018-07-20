import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Users } from '../model/users';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constanst';
import { HttpRequest, Session } from '../server/http';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private constant: Constants) { }

  getUser(id: string): Observable<Users> {
    const url = this.constant.GET_USER_INFO;

    var user = new Users();
    user.userId = id;
    var request: HttpRequest = new HttpRequest();
    var session: Session = request.getSession(true);
    user.idToken = session.getItem('gToken');

    return this.http.post<Users>(url, user, httpOptions).pipe(
      catchError(this.handleError<Users>('getUserInfo', null))
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
