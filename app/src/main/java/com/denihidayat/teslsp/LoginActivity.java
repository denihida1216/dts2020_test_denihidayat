package com.denihidayat.teslsp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denihidayat.teslsp.sqllite.DatabaseHelper;
import com.denihidayat.teslsp.sqllite.UsersDataitem;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private SessionManager sessionManager;
    private EditText username;
    private EditText password;
    private Button btnlogin;
    private Button btnregister;
    private Button btncancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnlogin = findViewById(R.id.btnlogin);
        btnregister = findViewById(R.id.btnregister);
        btncancel = findViewById(R.id.btncancel);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_input("login");
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_input("register");
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void check_input(String menu){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (user.trim().equals("")) {
            username.setError("*Username required!");
            username.requestFocus();
        } else {
            username.setError(null);
        }
        if (pass.trim().equals("")) {
            password.setError("*Password required!");
            if (user.trim().equals("")) {
                username.requestFocus();
            } else {
                password.requestFocus();
            }
        } else {
            password.setError(null);
        }
        if (!user.trim().equals("") && !pass.trim().equals("")) {
            if (menu.equals("login")){
                login();
            }else if (menu.equals("register")){
                check_user();
            };
        }
    }

    private void check_user(){
        Cursor cursor;
        cursor = db.checkLoginUsername(username.getText().toString());
        cursor.moveToFirst();
        int check = cursor.getCount();
        if (check == 1) {
            Toast.makeText(LoginActivity.this,"User sudah ada!!!", Toast.LENGTH_SHORT).show();
            return;
        }else{
            register();
        }
    }

    private void login(){
        Cursor cursor;
        cursor = db.checkLogin(
                ""+username.getText().toString(),
                ""+password.getText().toString()
        );
        cursor.moveToFirst();
        int check = cursor.getCount();
//        System.out.println("check=>"+check);
        if (check == 1) {
            sessionManager.createSessionLogin(username.getText().toString());
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(LoginActivity.this,"Username / Password salah!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void register(){
        UsersDataitem data = new UsersDataitem();
        data.setUsername(username.getText().toString());
        data.setPassword(password.getText().toString());
        db.insertUsers(data);
        sessionManager.createSessionLogin(username.getText().toString());
        Toast.makeText(LoginActivity.this,"User berhasil dibuat", Toast.LENGTH_SHORT).show();
    }
}