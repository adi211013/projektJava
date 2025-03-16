package com.example.finanse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements ProductLogic , UserLogic{
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 7;
    //produkty
    private static final String PRODUCT_TABLE_NAME = "products";
    private static final String PRODUCT_COLUMN_ID = "id";
    private static final String PRODUCT_COLUMN_USERID = "userid";
    private static final String PRODUCT_COLUMN_NAME = "name";
    private static final String PRODUCT_COLUMN_CATEGORY = "category";
    private static final String PRODUCT_COLUMN_PRICE = "price";
    private static final String PRODUCT_COLUMN_AMOUNT = "amount";
    //uzytkownicy
    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_EMAIL = "email";

    private static final String USER_COLUMN_PASSWORD = "password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + PRODUCT_TABLE_NAME + " (" +
                PRODUCT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_COLUMN_USERID + " INTEGER , " +
                PRODUCT_COLUMN_NAME + " TEXT, " +
                PRODUCT_COLUMN_CATEGORY + " TEXT, " +
                PRODUCT_COLUMN_PRICE + " REAL," +
                PRODUCT_COLUMN_AMOUNT + " INTEGER)";
        db.execSQL(createTable);
        String createUserTable = "CREATE TABLE " + USER_TABLE_NAME + " (" +
                USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_EMAIL + " TEXT, " +
                USER_COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    @Override
    public long addProduct(Product p) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_COLUMN_NAME,p.getName());
        values.put(PRODUCT_COLUMN_USERID,p.getUserID());
        values.put(PRODUCT_COLUMN_CATEGORY,p.getCategory());
        values.put(PRODUCT_COLUMN_AMOUNT,p.getAmount());
        values.put(PRODUCT_COLUMN_PRICE,p.getPrice());
        long result=db.insert(PRODUCT_TABLE_NAME,null,values);
        db.close();
        return result;
    }
    public List<Product> getProductsForUser(int userId) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = PRODUCT_COLUMN_USERID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.query(PRODUCT_TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(PRODUCT_COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT_COLUMN_NAME));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT_COLUMN_CATEGORY));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(PRODUCT_COLUMN_PRICE));
                int amount = cursor.getInt(cursor.getColumnIndexOrThrow(PRODUCT_COLUMN_AMOUNT));
                products.add(new Product(id, userId, name, category, price, amount));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }
    @Override
    public void deleteProduct(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(PRODUCT_TABLE_NAME,PRODUCT_COLUMN_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public long addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_EMAIL, u.getEmail());
        values.put(USER_COLUMN_PASSWORD, u.getPassword());
        long result = db.insert(USER_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public User getUser(String email,String password ) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USER_COLUMN_EMAIL + "=? AND " + USER_COLUMN_PASSWORD + "=?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query(USER_TABLE_NAME, null, selection, selectionArgs, null, null, null);
        User user = null;
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(USER_COLUMN_ID));
            String dbEmail = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_EMAIL));
            String dbPassword = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_PASSWORD));
            user = new User(id, dbPassword, dbEmail);
        }
        cursor.close();
        db.close();
        return user;
    }
    public int getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = USER_COLUMN_EMAIL + "=?";
        String[] selectionArgs = { email };
        Cursor cursor = db.query(USER_TABLE_NAME, null, selection, selectionArgs, null, null, null);
        int result = (cursor.moveToFirst()) ? 1 : -1;
        cursor.close();
        db.close();
        return result;
    }
}
