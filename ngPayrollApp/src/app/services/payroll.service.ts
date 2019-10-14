import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from "rxjs/operators"
import { Payroll } from '../models/payroll';

@Injectable({
  providedIn: 'root'
})
export class PayrollService {
  private baseUrl = "http://localhost:8090/";
  private url = this.baseUrl + "api/payroll";
  httpOptions = {
    headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
    })
  };
  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Payroll[]>(this.url + "?sorted=true").pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("Error");
      })
    );
  }
  create(payroll: Payroll) {
    return this.http.post<Payroll>(this.url, payroll, this.httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error creating");
        })
      );
  }

  update(payroll: Payroll) {
    return this.http.put<Payroll>(this.url + '/' + payroll.id, payroll, this.httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error updating");
        })
      );
  }

  destroy(id: number) {
    return this.http.delete<boolean>(this.url + '/' + id)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("Error deleting");
        })
      );
  }
}
