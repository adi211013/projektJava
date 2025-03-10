package com.example.finanse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class addActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText nameEdittext,priceEdittext,amountEdittext;
    Spinner categorySpinner;
    Button addProductButton,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        backButton=(Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        db=new DatabaseHelper(this);
        nameEdittext=(EditText)findViewById(R.id.nameEditext);
        priceEdittext=(EditText)findViewById(R.id.priceEditext);
        amountEdittext=(EditText)findViewById(R.id.amountEditext);
        categorySpinner=(Spinner) findViewById(R.id.categoriesSpinner);
        addProductButton=findViewById(R.id.addProductButton);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEdittext.getText().toString();
                try {
                    if (name.length() == 0) {
                        Toast.makeText(addActivity.this, "Nazwa produktu nie może być pusta", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double price = Double.parseDouble(priceEdittext.getText().toString());
                    if (price < 0) {
                        Toast.makeText(addActivity.this, "Cena nie może być ujemna", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int amount = Integer.parseInt(amountEdittext.getText().toString());
                    if (amount < 0) {
                        Toast.makeText(addActivity.this, "Ilość produktów nie może być ujemna", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String category = categorySpinner.getSelectedItem().toString();
                    Product p = new Product(name, category, price, amount);
                    if (db.addProduct(p) != -1) {
                        Toast.makeText(addActivity.this, "Produkt dodany", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                        Toast.makeText(addActivity.this, "Wystąpił błą∂, spróbuj jeszcze raz.", Toast.LENGTH_SHORT).show();
                }catch (Exception e)
                {
                    Toast.makeText(addActivity.this, "Wystapił błąd: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}