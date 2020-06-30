package com.FIEK.raportoup.aktivitetet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.FIEK.raportoup.R;
import com.FIEK.raportoup.databaza.Databaza;
import com.FIEK.raportoup.databaza.Perdoruesi;
import com.FIEK.raportoup.utilities.Hash;

public class Login extends AppCompatActivity {
    @Override
    public void onBackPressed() {
    }

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin1);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(Login.this, SignUp.class);
                startActivity(intentRegister);
            }
        });
    }

    public void checkUsername(String username, String password) {
        SQLiteDatabase objDb = new Databaza(Login.this).getReadableDatabase();

        Cursor c = objDb.query(Databaza.PerdoruesitTable,
                new String[]{Perdoruesi.ID, Perdoruesi.Username, Perdoruesi.Password, Perdoruesi.Admin},
                Perdoruesi.Username + "=?", new String[]{username}, "", "", "");

        if (c.getCount() == 1) {
            c.moveToFirst();
            String dbUsername = c.getString(1);
            String dbPassword = c.getString(2);
            Integer checkAdmin = c.getInt(3);

            if (username.equals(dbUsername) &&
                    Hash.md5(password).equals(dbPassword) && (checkAdmin == 1)) {

                Toast.makeText(Login.this, getString(R.string.loguar_sukses),
                        Toast.LENGTH_LONG).show();

                Intent admin = new Intent(Login.this, Admin.class);
                admin.putExtra("username", etUsername.getText().toString());
                startActivity(admin);
            } else {
                if (username.equals(dbUsername) &&
                        Hash.md5(password).equals(dbPassword) && (checkAdmin == 0)) {
                    Toast.makeText(Login.this, getString(R.string.loguar_sukses),
                            Toast.LENGTH_LONG).show();

                    Intent faqjaPare = new Intent(Login.this, FaqjaPare.class);
                    faqjaPare.putExtra("username", etUsername.getText().toString());
                    startActivity(faqjaPare);
                } else {
                    etPassword.setError(getString(R.string.kredencialet_gabim));
                    etPassword.requestFocus();
                }
            }
        } else {
            etPassword.setError(getString(R.string.kredencialet_gabim));
            etPassword.requestFocus();
        }
    }
}



