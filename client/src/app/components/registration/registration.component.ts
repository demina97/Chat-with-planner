import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  error: string = null;
  user: User = new User();
  afterClick: boolean = false;
  diffPasswords: boolean = false;

  constructor(private loginService: LoginService) { }

  ngOnInit() {
  }

  toRegister() {
    this.error = null;
    if (this.user.password !== this.user.confirmedPassword) {
      this.diffPasswords = true;
      return false;
    } else {
      this.diffPasswords = false;
    }

    this.loginService.registration(this.user).subscribe(
      value => {},
      error => {
        this.error = error.error.message;
      }
    );
  }

}
