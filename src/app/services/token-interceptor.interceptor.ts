import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

// The interceptor intercepts all HTTP requests and adds the authorization token to the Authorization header:
@Injectable()
export class TokenInterceptorInterceptor implements HttpInterceptor {

  constructor(private login: LoginService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let authReq = request;
    const token = this.login.getToken();
    console.log("inside auth interceptor");
    console.log("token ="  + token)
    
    if (token) {
      authReq = authReq.clone({
          headers: authReq.headers.set('Authorization', 'Bearer ' + token)
      });
  }
  console.log("authrequest header:", authReq.headers.get('Authorization'));
    return next.handle(authReq);
  }
}
