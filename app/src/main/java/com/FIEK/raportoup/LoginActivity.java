package com.FIEK.raportoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

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
                Intent intentRegister = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    public void checkUsername(String username, String password) {
        SQLiteDatabase objDb = new Databaza(LoginActivity.this).getReadableDatabase();

        Cursor c = objDb.query(Databaza.PerdoruesitTable,
                new String[]{Perdoruesi.ID, Perdoruesi.Username, Perdoruesi.Password},
                Perdoruesi.Username + "=?", new String[]{username}, "", "", "");

        if (c.getCount() == 1) {
            c.moveToFirst();
            String dbUsername = c.getString(1);
            String dbPassword = c.getString(2);

            if (username.equals(dbUsername) &&
                    password.equals(dbPassword)) {
                Toast.makeText(LoginActivity.this, getString(R.string.loguar_sukses),
                        Toast.LENGTH_LONG).show();

                Intent faqjaPare = new Intent(LoginActivity.this, FaqjaPare.class);
                faqjaPare.putExtra("username", etUsername.getText().toString());
                startActivity(faqjaPare);

            } else {
                etPassword.setError(getString(R.string.kredencialet_gabim));
                etPassword.requestFocus();
            }
        } else {
            etPassword.setError(getString(R.string.kredencialet_gabim));
            etPassword.requestFocus();
        }
    }
}
