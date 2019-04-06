import { Component} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../../models/Message";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  private serverUrl = 'http://localhost:8080/socket';
  private title = 'WebSockets chat';
  private stompClient;
  public messages: Message[] = [];
  public input: string = "";

  constructor(){
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/chat", (message) => {
        if(message.body) {
          that.messages.push(JSON.parse(message.body));
          console.log(message.body);
        }
      });
    });
  }

  sendMessage(){
    this.stompClient.send("/app/send/message" , {}, this.input);
    this.input = "";
  }

  checkCurrentUser(username: string): boolean{
    return username == "Vatson";
  }

}
