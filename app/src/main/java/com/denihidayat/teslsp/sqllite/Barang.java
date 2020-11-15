package com.denihidayat.teslsp.sqllite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;


public class Barang {

    public static final String TABLE_NAME = "barang";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_HARGA = "harga";
    public static final String COLUMN_MERK = "merk";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COLUMN_NAMA + " TEXT PRIMARY KEY, " +
            COLUMN_HARGA + " DOUBLE, " +
            COLUMN_MERK + " TEXT " +
            ");";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public Barang() {
    }

    public static void INSERT(SQLiteDatabase db, BarangDataitem items) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Barang.COLUMN_NAMA, items.getNama());
        contentValues.put(Barang.COLUMN_HARGA, items.getHarga());
        contentValues.put(Barang.COLUMN_MERK, items.getMerk());
        db.insert(Barang.TABLE_NAME, null, contentValues);
    }
}