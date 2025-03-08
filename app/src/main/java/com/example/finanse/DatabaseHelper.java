package com.example.finanse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements ProductLogic {
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_AMOUNT = "amount";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_PRICE + " REAL," +
                COLUMN_AMOUNT + "INTEGER)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void addProduct(Product p) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,p.getName());
        values.put(COLUMN_CATEGORY,p.getCategory());
        values.put(COLUMN_AMOUNT,p.getAmount());
        values.put(COLUMN_PRICE,p.getPrice());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    @Override
    public List<Product> getAllProducts() {
        int id,amount;
        String name,category;
        float price;
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        if(cursor.moveToFirst())
        {
            do{
                id=cursor.getInt(0);
                name=cursor.getString(1);
                category=cursor.getString(2);
                price=cursor.getFloat(3);
                amount=cursor.getInt(4);
                products.add(new Product(id,name,category,price,amount));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }

    @Override
    public void deleteProduct(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
