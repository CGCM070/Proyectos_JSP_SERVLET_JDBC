package com.iesvdm.proyectos.model;

import java.util.Objects;

public class User {
    private int userID;
    private String username;
    private String password;


    public User() {}

    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getUserID() {
        return userID;
    }

    public User setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", userID=" + userID +
                ", username='" + username + '\'' +
                '}';
    }
}
