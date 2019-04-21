package kz.aupet.vt152.diplom.Controllers;


import kz.aupet.vt152.diplom.Models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebSocketController {
  private final SimpMessagingTemplate template;
  
  private static List<Message> testMessages = new ArrayList<>();
  
  static {
    testMessages.add(new Message("12-04-2019 23:15:14", "Бухгалтер",
      "Менеджер проектов", "Добрый день!"));
    testMessages.add(new Message("12-04-2019 23:18:14", "Менеджер проектов",
      "Бухгалтер", "Добрый."));
    testMessages.add(new Message("12-04-2019 23:19:14", "Менеджер проектов",
      "Бухгалтер", "Сегодня состоится встреча за квартал в 16:00."));
    testMessages.add(new Message("12-04-2019 23:21:14", "Бухгалтер",
      "Менеджер проектов", "Хорошо, спасибо."));
  }
  
  private static int i = 0;
  
  @Autowired
  WebSocketController(SimpMessagingTemplate template) {
    this.template = template;
  }
  
  @MessageMapping("/send/message")
  public void onReceiveMessage(String message) {
    this.template.convertAndSend("/chat",
      testMessages.get(i++));
    
    if (i >= testMessages.size()) {
      i = 0;
    }
  }
  
  @MessageMapping("/info")
  public void info() {
    this.template.convertAndSend("/chat",
      "SUCCESS");
  }
}
