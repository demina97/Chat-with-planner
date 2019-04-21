package kz.aupet.vt152.diplom.Models.Login;

import kz.aupet.vt152.diplom.Models.User;

public class ValidateResponce {
  private boolean status;
  private User user;
  
  public ValidateResponce(boolean status, User user) {
    this.status = status;
    this.user = user;
  }
  
  public boolean isStatus() {
    return status;
  }
  
  public User getUser() {
    return user;
  }
}
