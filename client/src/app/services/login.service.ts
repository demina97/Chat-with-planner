import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {LoginData} from "../models/LoginData";
import {map} from "rxjs/operators";
import {ChatService} from "./chat.service";
import {TokenService} from "./token.service";
import {User} from "../models/User";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLogin: boolean = false;

  constructor(private http: HttpClient, private chatService: ChatService, private token: TokenService, private router: Router) {
  }

  login(user: LoginData): Observable<boolean> {
    let url = environment.server_url + '/login';
    return this.http.post<any>(url, {
      username: user.userName,
      password: user.password
    }).pipe(map(data => {
      if (data) {
        this.token.setToken(data.token);
        this.isLogin = true;
        this.chatService.initializeWebSocketConnection();
        return true;
      } else {
        alert("Authentication failed.");
        this.isLogin = false;
        return false;
      }
    }));
  }

  validateLogin(): Observable<boolean> {
    return this.http.get<boolean>(environment.server_url + '/validate', {}).pipe(map(value => {
      this.isLogin = value;
      return this.isLogin;
    }, error => {
      return (this.isLogin = false);
    }));
  }

  registration(user: User): Observable<boolean> {
    return this.http.post<any>(environment.server_url + '/registration', user)
      .pipe(map(data => {
        if (data) {
          alert("Registration SUCCESS.");
          this.router.navigateByUrl("/login");
          return true;
        } else {
          alert("Registration failed.");
          return false;
        }
      }));
  }

  logout() {
    this.isLogin = false;
    this.token.signOut();
    this.chatService.disconnect();
    this.router.navigateByUrl("/login");
  }
}
