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
    Button loginButton,signinButton;
    EditText emailEditText,passwordEditText;
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
        loginButton=findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            emailEditText=findViewById(R.id.emailET);
            passwordEditText=findViewById(R.id.passwordET);
            try {
                String email=emailEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Pola nie moga byc puste", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseHelper db = new DatabaseHelper(this);
                User u=db.getUser(email,password);
                if(u!=null){
                    Intent intent =new Intent(MainActivity.this,loggedActivity.class);
                    intent.putExtra("User",u);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Taki uzytkownik nie istnieje", Toast.LENGTH_SHORT).show();
                    return;
               }
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Wystapił błąd: "+e, Toast.LENGTH_SHORT).show();
           }
        });
        signinButton=findViewById(R.id.signinButton);
        signinButton.setOnClickListener(v ->{
            emailEditText=findViewById(R.id.emailET);
            passwordEditText=findViewById(R.id.passwordET);
            try {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Pola nie moga byc puste", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseHelper db=new DatabaseHelper(this);
                if(db.getUser(email)==1){
                    Toast.makeText(MainActivity.this, "Uzytkownik o podanym adresie email istnieje w bazie danych", Toast.LENGTH_SHORT).show();
                    return;
                }
                User u=new User(password,email);
                if (db.addUser(u) != -1) {
                    Toast.makeText(MainActivity.this, "Rejestracja przebiegla pomyslnie", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e) {
                Toast.makeText(MainActivity.this, "Wystapił błąd: "+e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}