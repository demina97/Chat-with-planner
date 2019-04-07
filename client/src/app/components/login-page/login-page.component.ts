import {Component, OnInit} from '@angular/core';
import {LoginData} from "../../models/LoginData";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  user: LoginData = new LoginData();
  afterClick: boolean = false;

  constructor(private service: LoginService) { }

  ngOnInit() {
  }

  login() {
    this.afterClick = true;
    this.service.login(this.user).subscribe(value => {
        if (value) {
          alert("SUCCESS");
        } else {
          alert("ERROR");
        }
        this.afterClick = false;
      }, () => {
        this.afterClick = false;
      }
    )
  }

}
