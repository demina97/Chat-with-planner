import {Component} from '@angular/core';
import {ChatService} from "../../services/chat.service";
import {Message} from "../../models/Message";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  public input: string = "";

  constructor(private chatService: ChatService) {

  }

  sendMessage() {
    this.chatService.sendMessage(this.input);
    this.input = "";
  }

  messages(): Message[] {
    return this.chatService.messages;
  }

  checkCurrentUser(username: string): boolean {
    return username == "Менеджер проектов";
  }

}
