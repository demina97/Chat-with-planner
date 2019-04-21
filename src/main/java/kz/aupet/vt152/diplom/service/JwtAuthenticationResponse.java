package kz.aupet.vt152.diplom.service;

import kz.aupet.vt152.diplom.Models.User;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
  private final String token;
  private final User user;
  
  public JwtAuthenticationResponse(String token, User user) {
    this.token = token;
    this.user = user;
  }
  
  public String getToken() {
    return this.token;
  }
  
  public User getUser() {
    return user;
  }
}
