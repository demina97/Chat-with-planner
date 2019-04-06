package kz.aupet.vt152.diplom.models.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUser implements UserDetails {
  private final String username;
  private final String password;
  
  public JwtUser(
    String username,
    String password
  ) {
    this.username = username;
    this.password = password;
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
  
  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }
}
