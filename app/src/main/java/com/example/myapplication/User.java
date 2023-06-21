package com.example.myapplication;

import java.util.ArrayList;

public class User {
    public String username;
    public String password;
    public String status;

    public User(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        users.add( new User("anon", "1234", "FREE"));
        users.add( new User("hero", "1234", "VIP"));
        users.add( new User("adonis", "1234", "STANDARD"));
        users.add( new User("ibara", "1234", "SUPERVIP"));

        return users;
    }
}
