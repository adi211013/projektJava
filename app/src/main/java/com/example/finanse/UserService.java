package com.example.finanse;

import android.content.Context;

public class UserService {
    private DatabaseHelper db;

    public UserService(Context context)
    {
        db=new DatabaseHelper(context);
    }
    public long registerUser(String email, String password) throws UserAlreadyExistsException {
        if(db.getUser(email)==1)
            throw new UserAlreadyExistsException("Uzytkownik o podanym adresie email juz istnieje");
        User u = new User(email,password);
        return db.addUser(u);
    }
    public User loginUser(String email, String password)throws UserNotFoundException
    {
        User u=db.getUser(email,password);
        if (u == null)
            throw new UserNotFoundException("Uzytkownik o podanym emailu i hasle nie istnieje");
        return u;
    }
}
