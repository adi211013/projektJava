package com.example.finanse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loggedActivity extends AppCompatActivity {
    Button addButton,historyButton,logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logged);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User u = (User) getIntent().getSerializableExtra("User");
        addButton=findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent intent =new Intent(loggedActivity.this,addActivity.class);
            intent.putExtra("User",u);
            startActivity(intent);
        });
        historyButton=findViewById(R.id.historyButton);
        historyButton.setOnClickListener(v -> {
            Intent intent =new Intent(loggedActivity.this,historyActivity.class);
            intent.putExtra("User",u);
            startActivity(intent);
        });
        logoutButton=findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v ->{
            finish();
        });

    }
}
