package kz.aupet.vt152.diplom.dao;

import kz.aupet.vt152.diplom.Models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
  
  @Select("select * from person")
  List<User> getUserList();
  
  @Select("select * from person where phone=#{phone}")
  User getUserByPhone(@Param("phone") String phone);
  
  @Insert("insert into person (phone, firstName, lastName, position, password)" +
    " values (#{user.phone}, #{user.firstName}, #{user.lastName}, #{user.position}, #{user.password})")
  void saveUser(@Param("user") User user);
}
