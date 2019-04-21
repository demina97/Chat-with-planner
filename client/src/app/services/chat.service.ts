import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Message} from "../models/Message";
import {TokenService} from "./token.service";
import {environment} from "../../environments/environment";
import {Observable, Subject} from "rxjs";
import {User} from "../models/User";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private isChatInitialize: boolean = false;
  private stompClient;
  public chats: User[] = [];
  opened: string = null;
  public messages: any = [];

  public newMsg: Subject<Message> = new Subject();

  constructor(private http: HttpClient, private token: TokenService) {
  }

  initializeWebSocketConnection(user: string) {
    this.loadChatList().subscribe();
    if (!this.isChatInitialize) {
      let ws = new SockJS(environment.socet_url + '?token=' + this.token.getToken());
      this.stompClient = Stomp.over(ws);
      let that = this;
      this.stompClient.connect({}, function () {
        that.start();
        that.stompClient.subscribe("/chat-" + user, (message) => {
          if (message.body) {
            let msg = JSON.parse(message.body);
            if (msg.recipient === that.opened || msg.sender === that.opened) {
              that.messages.push(msg);
              that.newMsg.next(msg);
            } else {
              let u = that.chats.find(value => value.phone === msg.sender);
              if (u) {
                u.hasNewMessage = true;
              }
            }
          }
        });
      });
      that.isChatInitialize = true;
    }
  }

  public sendMessage(message: Message) {
    this.stompClient.send("/send/message", {}, JSON.stringify(message));
  }

  public disconnect() {
    this.stompClient.disconnect();
    this.isChatInitialize = false;
    this.messages = [];
  }

  start() {
    this.stompClient.send("/start", {}, this.token.getToken());
  }

  public loadChatList(): Observable<User[]> {
    return this.http.get<any>(environment.server_url + "/api_chat/chats", {}).pipe(map(
      value => {
        this.chats = value;
        return value;
      }
    ));
  }

  loadMessagesFor(phone: string): Observable<Message[]> {
    return this.http.get<any>(environment.server_url + "/api_chat/messages?phone=" + encodeURIComponent(phone))
      .pipe(map(
        value => {
          this.messages = value;
          return value;
        }
      ));
  }

  getMessages() {
    return this.messages;
  }
}
