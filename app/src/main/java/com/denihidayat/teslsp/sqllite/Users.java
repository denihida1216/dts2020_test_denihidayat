package com.denihidayat.teslsp.sqllite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Users {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
            COLUMN_PASSWORD + " TEXT " +
            ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public Users() {
    }

    public static void INSERT(SQLiteDatabase db, UsersDataitem items) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Users.COLUMN_USERNAME, items.getUsername());
        contentValues.put(Users.COLUMN_PASSWORD, items.getPassword());
        db.insert(Users.TABLE_NAME, null, contentValues);
    }
}