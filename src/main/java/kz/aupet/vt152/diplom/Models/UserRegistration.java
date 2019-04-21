package kz.aupet.vt152.diplom.Models;

import java.util.Date;

public class UserRegistration extends User {
  private String password;
  private String confirmedPassword;
  
  public UserRegistration() {
  
  }
  
  public UserRegistration(String phone, String firstName, String lastName, String position, String password, String confirmedPassword) {
    super(phone, firstName, lastName, position);
    this.password = password;
    this.confirmedPassword = confirmedPassword;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getConfirmedPassword() {
    return confirmedPassword;
  }
  
  public void setConfirmedPassword(String confirmedPassword) {
    this.confirmedPassword = confirmedPassword;
  }
}

