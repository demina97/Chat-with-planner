package kz.aupet.vt152.diplom.Models;

public class User {
  private String phone;
  private String firstName;
  private String lastName;
  private String position;
  private String password;
  private String confirmedPassword;
  
  public User() {
  }
  
  public User(String phone, String firstName, String lastName, String position,
              String password, String confirmedPassword) {
    this.phone = phone;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.password = password;
    this.confirmedPassword = confirmedPassword;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getPosition() {
    return position;
  }
  
  public void setPosition(String position) {
    this.position = position;
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
