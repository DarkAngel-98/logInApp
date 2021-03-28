package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db" ;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists Users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("Users", null, contentValues);
        if (result == -1)
            return false ;
        else
            return true ;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor cursor = db.rawQuery("Select * From Users where username = ?", new String[]{username}) ;
        if(cursor.getCount() > 0)
            return true ;
        else
            return false ;
    }

    public Boolean checkUsersPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor cursor = db.rawQuery("Select * From Users Where username = ? and password = ?", new String[] {username, password}) ;
        if(cursor.getCount() > 0)
            return true ;
        else
            return false ;
    }
}
