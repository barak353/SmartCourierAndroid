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
    public static User currentUser;
    public final static String ip = "b8145c9a.ngrok.io";//13.57.89.248";//"10.0.2.2";

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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Courier [username=" + username + ", password=" + password + /*", firstName=" + firstName + ", LastName=" + lastName
                + ", address=" + address + ", */ " token=" + token + "]";
    }

}

