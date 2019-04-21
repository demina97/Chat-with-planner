import {Component, ElementRef, ViewChild} from '@angular/core';
import {ChatService} from "../../services/chat.service";
import {Message} from "../../models/Message";
import {LoginService} from "../../services/login.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  @ViewChild("chat") chatDiv: ElementRef;
  public input: string = "";
  private loading: boolean = false;
  newMsg: boolean = false;

  constructor(private chatService: ChatService, private loginService: LoginService) {
    chatService.newMsg.subscribe(value => {
      this.newMsg = true;
      window.requestAnimationFrame(() =>
        this.chatDiv.nativeElement.scrollTop = this.chatDiv.nativeElement.scrollHeight
      );
    });
  }

  sendMessage() {
    this.chatService.sendMessage(
      new Message(new Date(),
        this.loginService.userInfo.phone,
        this.chatService.opened,
        this.input)
    );
    this.input = "";
  }

  messages(): Message[] {
    return this.chatService.getMessages();
  }

  checkCurrentUser(username: string): boolean {
    return username == this.loginService.userInfo.phone;
  }

  openChatWith(phone: string) {
    this.chatService.opened = phone;
    this.loading = true;
    this.chatService.loadMessagesFor(phone).subscribe(
      () => {
        this.loading = false;
        window.requestAnimationFrame(() =>
          this.chatDiv.nativeElement.scrollTop = this.chatDiv.nativeElement.scrollHeight
        );
        let u = this.chatService.chats.find(value => value.phone === this.chatService.opened);
        if (u) {
          u.hasNewMessage = false;
        }
      }, () => {
        this.loading = false;
      }
    );
  }
}
