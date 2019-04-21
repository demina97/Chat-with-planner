package kz.aupet.vt152.diplom.service;

import kz.aupet.vt152.diplom.Configuration.JwtTokenUtil;
import kz.aupet.vt152.diplom.Models.Login.JwtUser;
import kz.aupet.vt152.diplom.Models.Login.LoginData;
import kz.aupet.vt152.diplom.Models.User;
import kz.aupet.vt152.diplom.Models.UserRegistration;
import kz.aupet.vt152.diplom.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserService implements UserDetailsService {
  
  private final UserDao userDao;
  
  private final PasswordEncoder passwordEncoder;
  
  private final JwtTokenUtil jwtTokenUtil;
  
  @Autowired
  public UserService(UserDao userDao, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenUtil = jwtTokenUtil;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    LoginData loginData = getLogin(username);
    if (loginData == null) {
      return new JwtUser("", "");
    }
    
    return new JwtUser(loginData.getUsername(), loginData.getPassword());
  }
  
  public boolean registry(UserRegistration user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.saveUser(user);
    return true;
  }
  
  public boolean checkPhoneNumber(String phone) {
    User user = userDao.getUserByPhone(phone);
    return user == null;
  }
  
  public User getUserByToken(String token) {
    String username = jwtTokenUtil.getUsernameFromToken(token);
    return getUser(username);
  }
  
  public User getUser(String username) {
    return userDao.getUserByPhone(username);
  }
  
  public LoginData getLogin(String username) {
    return userDao.getUserLoginData(username);
  }
}
