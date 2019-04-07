import { Component, OnInit } from '@angular/core';
import {User} from "../../models/User";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User = new User();
  afterClick: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  toRegister() {

  }

}
