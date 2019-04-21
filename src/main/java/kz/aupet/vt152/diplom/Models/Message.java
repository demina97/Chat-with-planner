package kz.aupet.vt152.diplom.Models;

import java.util.Date;

public class Message {
  private long id;
  private Date timeOfSending;
  private String sender;
  private String recipient;
  private String messageText;
  private int status;
  
  public Message() {}
  
  public Message(Date timeOfSending, String sender, String recipient, String messageText) {
    this.timeOfSending = timeOfSending;
    this.sender = sender;
    this.recipient = recipient;
    this.messageText = messageText;
  }
  
  public String getSender() {
    return sender;
  }
  
  public void setSender(String sender) {
    this.sender = sender;
  }
  
  public String getRecipient() {
    return recipient;
  }
  
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }
  
  public Date getTimeOfSending() {
    return timeOfSending;
  }
  
  public void setTimeOfSending(Date timeOfSending) {
    this.timeOfSending = timeOfSending;
  }
  
  public String getMessageText() {
    return messageText;
  }
  
  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }
  
  public int getStatus() {
    return status;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
}
