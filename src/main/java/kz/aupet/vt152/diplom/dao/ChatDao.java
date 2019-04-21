package kz.aupet.vt152.diplom.dao;

import kz.aupet.vt152.diplom.Models.Message;
import kz.aupet.vt152.diplom.Models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatDao {
  
  @Select("SELECT * from person where phone != #{phone}")
  List<User> getAllChatFor(@Param("phone") String phone);
  
  @Select("select * from chat_message" +
    " where (" +
    " (sender = #{current}" +
    "   and recipient = #{other})" +
    "  or (sender = #{other} and recipient = #{current})" +
    "  )" +
    " order by timeofsending desc" +
    " limit 20 * (#{offset} + 1)")
  List<Message> getMessages(@Param("current") String currentUser,
                            @Param("other") String other,
                            @Param("offset") int offset);
  
  @Select("insert into chat_message (sender, recipient, messagetext)"
    + " values (#{message.sender}, #{message.recipient}, #{message.messageText}) "
    + " returning id, sender, recipient, messagetext, timeofsending")
  Message saveMessage(@Param("message") Message message);
}
