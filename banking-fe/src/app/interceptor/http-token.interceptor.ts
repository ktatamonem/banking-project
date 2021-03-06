import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "root"
})
export class HttpTokenInterceptor implements HttpInterceptor {
    constructor() {}
  
    intercept(
      request: HttpRequest<any>,
      next: HttpHandler
    ): Observable<HttpEvent<any>> {
      // add authorization header with jwt token if available
      const token = localStorage.getItem("token");
  
      if (token) {
        request = request.clone({
          setHeaders: {
            "Access-Control-Allow-Origin": "*",
            Authorization: "Bearer " + token
          }
        });
      } else {
        request = request.clone({
          setHeaders: {
            "Access-Control-Allow-Origin": "*"
          }
        });
      }
      return next.handle(request);
    }
  }
  