package com.denihidayat.teslsp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.denihidayat.teslsp.sqllite.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private SessionManager sessionManager;
    private Button btninput;
    private Button btnkeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        btninput = findViewById(R.id.btninput);
        btnkeluar = findViewById(R.id.btnkeluar);
        btninput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InputActivity.class));
            }
        });
        btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
            }
        });
    }
}