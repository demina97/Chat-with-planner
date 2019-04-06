package kz.aupet.vt152.diplom.Models;

public class Message {
    private String timeOfSending;
    private String sender;
    private String recipient;
    private String messageText;

    public Message(String timeOfSending, String sender, String recipient, String messageText){
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

    public String getTimeOfSending() {
        return timeOfSending;
    }

    public void setTimeOfSending(String timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
