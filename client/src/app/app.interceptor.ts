import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {TokenService} from "./services/token.service";


@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    if (this.token.getToken()) {
      req = req.clone({
        setHeaders: {
          'Authorization': `Bearer ${this.token.getToken()}`
        }
      });

      console.log(req);
    }

    let n = next.handle(req);
    n.subscribe(() => {}, err => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 401) {
          this.router.navigate(['home']);
        }
      }
    });
    return n;
  }

}
