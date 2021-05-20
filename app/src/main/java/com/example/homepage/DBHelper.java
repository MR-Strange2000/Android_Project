package com.example.homepage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "user";

    public DBHelper(Context context) {
        super(context, DBname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username varchar(30) primary key ,email varchar(30) , password varchar(30) , cnfpwd varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists users");
    }

    public boolean insertData(String username ,String email , String pwd , String cnfpwdd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cm = new ContentValues();

        cm.put("username" , username);
        cm.put("email" , email);
        cm.put("password" , pwd);
        cm.put("cnfpwd" , cnfpwdd);
        long res = db.insert("users" , null  , cm);
        if(res == -1){
            return  false;
        }else{
            return  true;
        }
    }

    public boolean getUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cn = db.rawQuery("SELECT * FROM users where username=?" , new String[] {username});

        if(cn.getCount() > 0){
            return  true;
        }else{
            return  false;
        }
    }

    public boolean checkUsernamePasword(String username , String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cn = db.rawQuery("SELECT * FROM users where username=? and password=?" , new String[] {username , password});

        if(cn.getCount() > 0){
            return true;
        }
        else{
            return  false;
        }
    }
}
