import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateAccountRequest } from '../model/create-account-request.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http : HttpClient) { }


  public createAccount(createAccountrequest : CreateAccountRequest):Observable<any>{
    return this.http.post<any>("/api/account/create",createAccountrequest); 
  }



}
