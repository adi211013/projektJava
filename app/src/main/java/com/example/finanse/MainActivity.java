package com.example.finanse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button loginButton, signinButton;
    EditText emailEditText, passwordEditText;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailEditText = findViewById(R.id.emailET);
        passwordEditText = findViewById(R.id.passwordET);
        userService = new UserService(this);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> loginUser());
        signinButton = findViewById(R.id.signinButton);
        signinButton.setOnClickListener(v -> registerUser());
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(validatePass(password)) return;
        if(validateEmail(email)) return;
        try {
            User u = userService.loginUser(email, password);
            Intent intent = new Intent(MainActivity.this, loggedActivity.class);
            intent.putExtra("User", u);
            startActivity(intent);
        } catch (UserNotFoundException e) {
            Toast.makeText(MainActivity.this, "Taki uzytkownik nie istnieje", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Wystapił błąd: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(validatePass(password)) return;
        if(validateEmail(email)) return;
        try {
            userService.registerUser(email, password);
            Toast.makeText(MainActivity.this, "Rejestracja przebiegla pomyslnie", Toast.LENGTH_SHORT).show();
        } catch (UserAlreadyExistsException e) {
            Toast.makeText(MainActivity.this, "Uzytkownik o podanym adresie email istnieje w bazie danych", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Wystapił błąd: " + e, Toast.LENGTH_SHORT).show();
        }
    }
    boolean validatePass(String password)
    {
        if (password.isEmpty())
        {
            Toast.makeText(MainActivity.this, "Haslo nie moze byc puste", Toast.LENGTH_SHORT).show();
            return true;
        }
        else return false;
    }
    boolean validateEmail(String email)
    {
        boolean val= email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        boolean empty=email.isEmpty();
        if(val && !empty)
            return false;
        else {
            Toast.makeText(MainActivity.this, "Niepoprawny adres email", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}