package com.example.finanse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class historyActivity extends AppCompatActivity {
    Button backButton;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User u = (User) getIntent().getSerializableExtra("User");
        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
        tableLayout=findViewById(R.id.table);
        DatabaseHelper db=new DatabaseHelper(this);
        List<Product> productList =db.getProductsForUser(u.getId());
        for(Product p : productList)
        {
            TableRow row=new TableRow(this);
            //nazwa
            TextView nameText=new TextView(this);
            nameText.setText(p.getName());
            nameText.setPadding(20,5,0,0);
            row.addView(nameText);
            //nazwa
            TextView categoryText=new TextView(this);
            categoryText.setText(p.getCategory());
            categoryText.setPadding(20,5,0,0);
            row.addView(categoryText);
            //nazwa
            TextView priceText=new TextView(this);
            priceText.setText(p.getPrice()+" zł");
            priceText.setPadding(20,5,0,0);
            row.addView(priceText);
            //nazwa
            TextView amountText =new TextView(this);
            amountText.setText(p.getAmount()+" szt");
            amountText.setPadding(20,5,0,0);
            row.addView(amountText);
            //usuwanie
            Button deleteButton=new Button(this);
            deleteButton.setText("Usuń produkt");
            deleteButton.setPadding(20,5,0,0);
            deleteButton.setOnClickListener(v -> {
                db.deleteProduct(p.getId());
                Toast.makeText(historyActivity.this,"Produkt usunięty",Toast.LENGTH_SHORT).show();
                tableLayout.removeView(row);
            });
            row.addView(deleteButton);
            tableLayout.addView(row);

        }
    }
}