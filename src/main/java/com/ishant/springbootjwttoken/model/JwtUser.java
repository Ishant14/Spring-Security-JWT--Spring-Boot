package com.ishant.springbootjwttoken.model;

public class JwtUser {
    private String userName;
    private String  password;
    private String role;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }


    public String getRole() {
        return role;
    }
}
