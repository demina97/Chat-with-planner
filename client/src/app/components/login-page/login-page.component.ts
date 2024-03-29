import {Component, OnInit} from '@angular/core';
import {LoginData} from "../../models/LoginData";
import {LoginService} from "../../services/login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  error: boolean = false;
  user: LoginData = new LoginData();
  afterClick: boolean = false;

  constructor(private service: LoginService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.error = false;
    this.afterClick = true;
    this.service.login(this.user).subscribe(value => {
        if (value) {
          this.router.navigateByUrl("/chat");
        } else {
          alert("ERROR");
        }
        this.afterClick = false;
      }, () => {
        this.afterClick = false;
        this.error = true;
      }
    )
  }

}
