package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {
    public SqliteHelper(Context context) {
        super(context, "users", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (id integer primary key, username text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insert (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long id = db.insert("users", null, contentValues);
        return true;
    }

    public ArrayList<UserModel> getAll() {
        ArrayList<UserModel> allData = new ArrayList<UserModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from users", null );

        if (cursor.moveToFirst()) {
            do {
                UserModel userModels = new UserModel();
                userModels.setId(Integer.parseInt(cursor.getString(0)));
                userModels.setUsername(cursor.getString(1));
                userModels.setPassword(cursor.getString(2));
                allData.add(userModels);
            } while (cursor.moveToNext());
        }
        return allData;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from users", null );
        int numRows = cursor.getCount();
        return numRows;
    }
}
