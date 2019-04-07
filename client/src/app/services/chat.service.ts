import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../models/Message";
import {TokenService} from "./token.service";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private isChatInitialize: boolean = false;
  private stompClient;
  public messages: Message[] = [];

  constructor(private token: TokenService) {
    if (!this.isChatInitialize) {
      this.initializeWebSocketConnection();
    }
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(environment.socet_url + '?token=' + this.token.getToken());
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function () {
      that.isChatInitialize = true;
      that.stompClient.subscribe("/chat", (message) => {
        if (message.body) {
          that.messages.push(JSON.parse(message.body));
          console.log(message.body);
        }
      });
    });
  }

  public sendMessage(message: string) {
    this.stompClient.send("/send/message", {}, message);
  }

}
