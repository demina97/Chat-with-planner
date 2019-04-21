package kz.aupet.vt152.diplom.service;


import kz.aupet.vt152.diplom.Models.Message;
import kz.aupet.vt152.diplom.Models.User;
import kz.aupet.vt152.diplom.dao.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ChatService {
  private final ChatDao chatDao;
  
  @Autowired
  public ChatService(ChatDao chatDao) {this.chatDao = chatDao;}
  
  
  public List<User> getAllChats(String username) {
    return chatDao.getAllChatFor(username);
  }
  
  public List<Message> getMessages(String currentUser, String other, int offset) {
    List<Message> messages = chatDao.getMessages(currentUser, other, offset);
    Collections.reverse(messages);
    return messages;
  }
  
  public Message saveMessage(Message message) {
    return chatDao.saveMessage(message);
  }
}
