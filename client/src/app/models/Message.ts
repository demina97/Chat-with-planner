export class Message {
  id: number;
  timeOfSending: Date;
  sender: string = "";
  recipient: string = "";
  messageText: string = "";
  status: number;


  constructor(timeOfSending: Date, sender: string, recipient: string, messageText: string) {
    this.timeOfSending = timeOfSending;
    this.sender = sender;
    this.recipient = recipient;
    this.messageText = messageText;
  }
}
