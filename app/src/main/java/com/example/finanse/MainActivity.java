package com.example.finanse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button addButton,historyButton,chartButton;
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
        addButton=findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this,addActivity.class);
            startActivity(intent);
        });
        historyButton=findViewById(R.id.historyButton);
        historyButton.setOnClickListener(v -> {
            Intent intent =new Intent(MainActivity.this,historyActivity.class);
            startActivity(intent);
        });
        chartButton = findViewById(R.id.chartButton);
        chartButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,chartActivity.class);
            startActivity(intent);
        });
    }
}