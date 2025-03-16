package com.example.finanse;

import java.util.List;

public interface UserLogic {
    long addUser(User u);
    User getUser(String email,String password );
    int getUser(String email);
}
