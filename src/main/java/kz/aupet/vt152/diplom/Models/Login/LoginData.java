package kz.aupet.vt152.diplom.Models.Login;

import java.io.Serializable;

public class LoginData implements Serializable {
  private String username;
  private String password;
  
  public LoginData() {
    super();
  }
  
  public LoginData(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return this.password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
}