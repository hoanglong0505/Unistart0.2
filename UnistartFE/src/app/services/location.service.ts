import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Location  } from '../model/location';
import { catchError } from 'rxjs/operators';
import { Constants } from '../constans';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient, private contant: Constants) { }

  getLocations(): Observable<Location[]> {
    const url= this.contant.LOCATION;
    return this.http.get<Location[]>(url).pipe(
      catchError(this.handleError<Location[]>('getLocations',[]))
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
