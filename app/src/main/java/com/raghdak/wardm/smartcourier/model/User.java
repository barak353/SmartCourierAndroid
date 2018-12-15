package com.raghdak.wardm.smartcourier.model;


import java.io.Serializable;

/**
 * Created by wardm on 22/11/2017.
 */
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
    private String token;

    public User(String email, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    public User(String id, String username, String password, String token) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Courier [username=" + username + ", password=" + password + /*", firstName=" + firstName + ", LastName=" + lastName
                + ", address=" + address + ", */ " token=" + token + "]";
    }

}

