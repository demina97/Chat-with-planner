import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {TokenService} from "./services/token.service";
import {catchError} from "rxjs/operators";


@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    if (this.token.getToken()) {
      req = req.clone({
        setHeaders: {
          'Authorization': this.token.getToken()
        }
      });
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.router.navigate(['login']);
        }
        return throwError(error);
      }));
  }

}
