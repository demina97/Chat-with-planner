package kz.aupet.vt152.diplom.service;

import kz.aupet.vt152.diplom.Models.Login.JwtUser;
import kz.aupet.vt152.diplom.Models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new JwtUser("user", new BCryptPasswordEncoder().encode("111"));
  }
  
  public boolean registry(User user) {
    
    return true;
  }
  
  public boolean checkPhoneNumber(String phone) {
    return false;
  }
}
