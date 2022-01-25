import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class InterceptorsService implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

      let u = req.url.replace('/api','http://localhost:8080');
      const proxyReq = req.clone({ url: u });
      return next.handle(proxyReq);
    }
}
