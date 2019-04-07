import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  context: boolean = false;

  constructor(private loginService: LoginService, private router: Router) { }

  ngOnInit() {
  }

  isLogin(): boolean {
    return this.loginService.isLogin;
  }

  logout() {
    this.loginService.logout()
  }
}
