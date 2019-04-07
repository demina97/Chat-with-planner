package kz.aupet.vt152.diplom.Models;

public class User {
    private String userName;
    private String firstName;
    private String lastName;
    private String position;
    private String password;

    public User(String userName, String firstName, String lastName, String position, String password){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
