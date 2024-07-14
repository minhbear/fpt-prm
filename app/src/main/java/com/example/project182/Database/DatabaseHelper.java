package com.example.project182.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project182.Entity.Account;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AccountDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "Account";
    private static final String COL_ID = "id";
    private static final String COL_PASSWORD = "password";
    private static final String COL_USERNAME = "username";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_PASSWORD + " TEXT," +
                COL_USERNAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PASSWORD, account.getPassword());
        values.put(COL_USERNAME, account.getUsername());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Account getAccountById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(id)});
        Account account = null;
        if (cursor.moveToFirst()) {
            account = new Account(cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
            account.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
        }
        cursor.close();
        db.close();
        return account;
    }

    public Account getAccountByUsername(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_USERNAME + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{email});
        Account account = null;
        if (cursor.moveToFirst()) {
            account = new Account(cursor.getString(cursor.getColumnIndexOrThrow(COL_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_PASSWORD)));
            account.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)));
        }
        cursor.close();
        db.close();
        return account;
    }
}