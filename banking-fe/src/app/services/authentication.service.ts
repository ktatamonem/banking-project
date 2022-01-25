import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  


  constructor(private http:HttpClient) {}


login(username:string , password:string):Observable<any> {
  return this.http.post<any>('/api/login',{"username":username , "password":password}).pipe(
    catchError(
      (error: any, caught: Observable<HttpEvent<any>>) => {
        return throwError(error);
      }
    )
  );
}

isAuthenticated(): boolean {
  return !!localStorage.getItem("token");
}


  

}
