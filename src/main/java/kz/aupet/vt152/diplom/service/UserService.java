package kz.aupet.vt152.diplom.service;

import kz.aupet.vt152.diplom.Models.Login.JwtUser;
import kz.aupet.vt152.diplom.Models.User;
import kz.aupet.vt152.diplom.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {
  
  private final UserDao userDao;
  
  private final PasswordEncoder passwordEncoder;
  
  @Autowired
  public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.getUserByPhone(username);
    if (user == null) {
      return new JwtUser("", "");
    }
    
    return new JwtUser(user.getPhone(), user.getPassword());
  }
  
  public boolean registry(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userDao.saveUser(user);
    return true;
  }
  
  public boolean checkPhoneNumber(String phone) {
    User user = userDao.getUserByPhone(phone);
    return user == null;
  }
}
