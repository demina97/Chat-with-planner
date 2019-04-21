package kz.aupet.vt152.diplom.Controllers;


import kz.aupet.vt152.diplom.Configuration.JwtTokenUtil;
import kz.aupet.vt152.diplom.Models.Message;
import kz.aupet.vt152.diplom.service.ChatService;
import kz.aupet.vt152.diplom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
  private final SimpMessagingTemplate template;
  
  private final UserService userService;
  
  private final ChatService chatService;
  
  private final JwtTokenUtil jwtTokenUtil;
  
  private static int i = 0;
  
  @Autowired
  WebSocketController(SimpMessagingTemplate template, UserService userService, ChatService chatService, JwtTokenUtil jwtTokenUtil) {
    this.template = template;
    this.userService = userService;
    this.chatService = chatService;
    this.jwtTokenUtil = jwtTokenUtil;
  }
  
  @MessageMapping("/send/message")
  public void send_message(Message message, SimpMessageHeaderAccessor headerAccessor) {
    Message msg = chatService.saveMessage(message);
    this.template.convertAndSend("/chat-" +
        msg.getRecipient(),
      msg);
    
    this.template.convertAndSend("/chat-" +
        msg.getSender(),
      msg);
  }
  
  @MessageMapping("/info")
  public void info() {
    this.template.convertAndSend("/chat",
      "SUCCESS");
  }
}
