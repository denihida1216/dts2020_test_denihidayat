package com.denihidayat.teslsp.sqllite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Constants for Database name, table name, and column names
    public static final String DB_NAME = "myDB";

    //database version
    private static final int DB_VERSION = 1;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //creating the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql = Users.CREATE_TABLE;
        db.execSQL(sql);
        sql = Barang.CREATE_TABLE;
        db.execSQL(sql);
    }

    //upgrading the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        sql = Users.DROP_TABLE;
        db.execSQL(sql);
        sql = Barang.DROP_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor checkLoginUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String where = "WHERE "+Users.COLUMN_USERNAME+"='"+ username+"' ";
        String sql = "SELECT * FROM " + Users.TABLE_NAME +" "+
                where +
                " ORDER BY " + Users.COLUMN_USERNAME +
                " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String where = "WHERE "+Users.COLUMN_USERNAME+"='"+ username+"' AND "+Users.COLUMN_PASSWORD+"='"+password+"' ";
        String sql = "SELECT * FROM " + Users.TABLE_NAME +" "+
                where +
                " ORDER BY " + Users.COLUMN_USERNAME +
                " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void insertUsers(UsersDataitem items) {
        SQLiteDatabase db = this.getWritableDatabase();
        Users.INSERT(db, items);
        db.close();
    }

    public Cursor checkBarangNama(String nama) {
        SQLiteDatabase db = this.getReadableDatabase();
        String where = "WHERE "+Barang.COLUMN_NAMA+"='"+ nama+"' ";
        String sql = "SELECT * FROM " + Barang.TABLE_NAME +" "+
                where +
                " ORDER BY " + Barang.COLUMN_NAMA +
                " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Cursor getBarangs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String where = "";
        String sql = "SELECT * FROM " + Barang.TABLE_NAME +" "+
                where +
                " ORDER BY " + Barang.COLUMN_NAMA +
                " ASC;";
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void insertBarang(BarangDataitem items) {
        SQLiteDatabase db = this.getWritableDatabase();
        Barang.INSERT(db, items);
        db.close();
    }
}