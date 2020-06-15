package com.FIEK.raportoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    EditText etID, etEmailAddress, etUsername, etPassword;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etID = findViewById(R.id.etID);
        etEmailAddress = findViewById(R.id.etMail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(Perdoruesi.ID, etID.getText().toString());
                cv.put(Perdoruesi.Email, etEmailAddress.getText().toString());
                cv.put(Perdoruesi.Username, etUsername.getText().toString());
                cv.put(Perdoruesi.Password, etPassword.getText().toString());

                SQLiteDatabase objDb = new Databaza(SignUpActivity.this).getWritableDatabase();

                try {
                    long retValue = objDb.insert(Databaza.PerdoruesitTable, null, cv);
                    if (retValue > 0) {
                        Toast.makeText(SignUpActivity.this, "Me id " + retValue + " u regjistruat me sukses!",
                                Toast.LENGTH_LONG).show();
                    }

                } catch (Exception ex) {
                    Log.e("except", ex.getMessage());
                } finally {
                    objDb.close();
                }
            }
        });


        btnLogin = findViewById(R.id.logInBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LogIn = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(LogIn);
            }
        });
    }
}
