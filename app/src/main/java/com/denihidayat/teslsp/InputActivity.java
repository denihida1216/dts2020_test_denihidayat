package com.denihidayat.teslsp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denihidayat.teslsp.sqllite.Barang;
import com.denihidayat.teslsp.sqllite.BarangDataitem;
import com.denihidayat.teslsp.sqllite.DatabaseHelper;
import com.denihidayat.teslsp.sqllite.UsersDataitem;

import java.util.ArrayList;
import java.util.List;

public class InputActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private SessionManager sessionManager;
    private EditText nama;
    private EditText harga;
    private EditText merk;
    private Button btnsave;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private List<BarangDataitem> rowsArrayList;
    private BarangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        rowsArrayList = new ArrayList<>();
        nama = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        merk = findViewById(R.id.merk);
        btnsave = findViewById(R.id.btnsave);
        rv = findViewById(R.id.rv);

        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        adapter = new BarangAdapter(rowsArrayList);
        rv.setAdapter(adapter);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_input();
            }
        });
        getData();
    }

    private void check_input(){
        String namastr = nama.getText().toString();
        String merkstr = merk.getText().toString();
        String hargastr = harga.getText().toString();
        if (namastr.trim().equals("")) {
            nama.setError("*Nama required!");
            nama.requestFocus();
        } else {
            nama.setError(null);
        }

        if (hargastr.trim().equals("")) {
            harga.setError("*Harga required!");
            if (namastr.trim().equals("")) {
                nama.requestFocus();
            } else if (merkstr.trim().equals("")) {
                merk.requestFocus();
            } else {
                harga.requestFocus();
            }
        } else {
            harga.setError(null);
        }

        if (merkstr.trim().equals("")) {
            merk.setError("*Merk required!");
            if (namastr.trim().equals("")) {
                nama.requestFocus();
            } else if (hargastr.trim().equals("")) {
                harga.requestFocus();
            } else {
                merk.requestFocus();
            }
        } else {
            merk.setError(null);
        }

        if (!namastr.trim().equals("") && !hargastr.trim().equals("")&& !merkstr.trim().equals("")) {
            check_barang();
        }
    }

    private void check_barang(){
        Cursor cursor;
        cursor = db.checkBarangNama(nama.getText().toString());
        cursor.moveToFirst();
        int check = cursor.getCount();
        if (check == 1) {
            Toast.makeText(InputActivity.this,"Barang sudah ada!!!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            save();
        }
    }

    private void save(){
        BarangDataitem data = new BarangDataitem();
        data.setNama(nama.getText().toString());
        data.setMerk(merk.getText().toString());
        data.setHarga(Double.parseDouble(harga.getText().toString()));
        db.insertBarang(data);
        Toast.makeText(InputActivity.this,"Barang berhasil ditambahkan", Toast.LENGTH_SHORT).show();
        nama.setText("");
        harga.setText("");
        merk.setText("");
        getData();
    }

    private void getData(){
        Cursor cursor = db.getBarangs();

        if (cursor.moveToFirst()) {
            do {
                BarangDataitem list = new BarangDataitem();
                list.setNama(cursor.getString(cursor.getColumnIndex(Barang.COLUMN_NAMA)));
                list.setHarga(cursor.getDouble(cursor.getColumnIndex(Barang.COLUMN_HARGA)));
                list.setMerk(cursor.getString(cursor.getColumnIndex(Barang.COLUMN_MERK)));
                rowsArrayList.add(list);
            } while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }
}