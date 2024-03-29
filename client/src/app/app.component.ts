import {Component, OnInit} from '@angular/core';
import {LoginService} from "./services/login.service";
import {ChatService} from "./services/chat.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private loginService: LoginService, private chatService: ChatService) {
  }

  ngOnInit() {
    this.loginService.validateLogin().subscribe(value => {
      this.chatService.initializeWebSocketConnection(this.loginService.userInfo.phone);
    });
  }

}
