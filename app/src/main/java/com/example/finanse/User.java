package com.example.finanse;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class User implements UserInterface, Serializable{
    private int id;
    private String password;
    private  String email;

    public User(int id,String password,String email)
    {
        this.id=id;
        this.password=password;
        this.email=email;
    }
    public User(String email,String password)
    {
        this.password=password;
        this.email=email;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
