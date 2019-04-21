package kz.aupet.vt152.diplom.Controllers;

import kz.aupet.vt152.diplom.Models.Message;
import kz.aupet.vt152.diplom.Models.User;
import kz.aupet.vt152.diplom.service.ChatService;
import kz.aupet.vt152.diplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api_chat")
public class ChatController {
  
  private final ChatService chatService;
  
  private final UserService userService;
  
  @Autowired
  public ChatController(ChatService chatService, UserService userService) {
    this.chatService = chatService;
    this.userService = userService;
  }
  
  @RequestMapping(value = "/chats", method = RequestMethod.GET)
  public List<User> chats(@RequestHeader("Authorization") String token) {
    return chatService.getAllChats(userService.getUserByToken(token).getPhone());
  }
  
  @RequestMapping(value = "/messages", method = RequestMethod.GET)
  public List<Message> messages(@RequestParam("phone") String phone,
                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                @RequestHeader("Authorization") String token) {
    return chatService.getMessages(userService.getUserByToken(token).getPhone(), phone, offset);
  }
}
