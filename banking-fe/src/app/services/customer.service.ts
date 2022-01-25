import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http : HttpClient) { }


  public retrieveCustomerList():Observable<any> {
    return this.http.get("/api/customer/list");
  }

  public retrieveCustomerData(id:number):Observable<any> {
    return this.http.get("/api/customer/"+id);
  }
}
