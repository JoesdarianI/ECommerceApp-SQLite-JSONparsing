package com.example.projectmcs.Model;

public class Users {

    Integer UserID;
    String UserEmail;
    String UserUsername;
    String userPhone;
    String UserPassword;

    public Users(Integer userID, String userEmail, String userUsername, String userPhone, String userPassword) {
        this.UserID = userID;
        this.UserEmail = userEmail;
        this.UserUsername = userUsername;
        this.userPhone = userPhone;
        this.UserPassword = userPassword;
    }

    public Users(String userUsername){
        this.UserUsername = userUsername;
    }


    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserUsername() {
        return UserUsername;
    }

    public void setUserUsername(String userUsername) {
        UserUsername = userUsername;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

}


