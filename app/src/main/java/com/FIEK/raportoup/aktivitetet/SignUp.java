package com.FIEK.raportoup.aktivitetet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.*;

public class SignUp extends AppCompatActivity {

    EditText etID, etEmailAddress, etUsername, etPassword, etPassword2;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etID = findViewById(R.id.etID);
        etEmailAddress = findViewById(R.id.etMail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPassword2 = findViewById(R.id.etPassword2);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String passPatern = "^(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{6,20}$";
                String idPattern = "^[0-9]{12}$";

                String coDomain = "student.uni-pr.edu";
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + Pattern.quote(coDomain) + "$";

                if (!Pattern.matches(idPattern, etID.getText().toString())) {
                    Toast.makeText(SignUp.this, "Ju lutem shkruani ID e juaj!", Toast.LENGTH_LONG).show();
                } else {
                    SQLiteDatabase myDb = new Databaza(SignUp.this).getReadableDatabase();

                    Cursor c = myDb.query(Databaza.PerdoruesitTable,
                            new String[]{Perdoruesi.ID, Perdoruesi.Username},
                            Perdoruesi.ID + "=?", new String[]{etID.getText().toString()}, "", "", "");

                    if (c.getCount() > 0) {
                        Toast.makeText(SignUp.this, "ID ekziston!", Toast.LENGTH_LONG).show();
                    } else {


                        if (!Pattern.matches(emailPattern, etEmailAddress.getText())) {
                            Toast.makeText(SignUp.this, "Nuk është valid email adresa", Toast.LENGTH_SHORT).show();
                        } else {

                            myDb = new Databaza(SignUp.this).getReadableDatabase();

                            c = myDb.query(Databaza.PerdoruesitTable,
                                    new String[]{Perdoruesi.ID, Perdoruesi.Username, Perdoruesi.Email},
                                    Perdoruesi.Email + "=?", new String[]{etEmailAddress.getText().toString()}, "", "", "");

                            if (c.getCount() > 0) {
                                Toast.makeText(SignUp.this, "Email ekziston!", Toast.LENGTH_LONG).show();
                            } else {
                                if (etUsername.getText().toString().equals("")) {
                                    Toast.makeText(SignUp.this, "Ju lutem plotësoni fushën username!", Toast.LENGTH_LONG).show();
                                } else {
                                    myDb = new Databaza(SignUp.this).getReadableDatabase();

                                    c = myDb.query(Databaza.PerdoruesitTable,
                                            new String[]{Perdoruesi.ID, Perdoruesi.Username, Perdoruesi.Password},
                                            Perdoruesi.Username + "=?", new String[]{etUsername.getText().toString()}, "", "", "");

                                    if (c.getCount() > 0) {
                                        Toast.makeText(SignUp.this, "Username ekziston!", Toast.LENGTH_LONG).show();

                                    } else {

                                        if (!Pattern.matches(passPatern, etPassword.getText().toString())) {
                                            Toast.makeText(SignUp.this, "Fjalëkalimi duhet të përmbajë 6-20 karaktere, së paku një shkronjë të madhe dhe numër", Toast.LENGTH_LONG).show();
                                        } else {
                                            if (!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
                                                Toast.makeText(SignUp.this, "Fjalëkalimi nuk përshtatet", Toast.LENGTH_LONG).show();
                                            } else {

                                                ContentValues cv = new ContentValues();
                                                cv.put(Perdoruesi.ID, etID.getText().toString());
                                                cv.put(Perdoruesi.Email, etEmailAddress.getText().toString());
                                                cv.put(Perdoruesi.Username, etUsername.getText().toString());
                                                cv.put(Perdoruesi.Password, Hash.md5(etPassword.getText().toString()));

                                                SQLiteDatabase objDb = new Databaza(SignUp.this).getWritableDatabase();

                                                try {
                                                    long retValue = objDb.insertOrThrow(Databaza.PerdoruesitTable, null, cv);
                                                    if (retValue > 0) {


                                                        Snackbar.make(v, "U regjistruat me sukses!", Snackbar.LENGTH_INDEFINITE)
                                                                .setAction("Kyçu", new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        Intent LogIn = new Intent(SignUp.this, Login.class);
                                                                        startActivity(LogIn);

                                                                    }
                                                                })
                                                                .setActionTextColor(getResources().getColor(android.R.color.holo_orange_dark))
                                                                .show();
                                                    }
                                                } catch (Exception ex) {
                                                    String string = ex.getMessage();
                                                    String[] arrOfStrings = string.split(":", 2);
                                                    Toast.makeText(SignUp.this, arrOfStrings[0], Toast.LENGTH_LONG).show();
                                                } finally {
                                                    objDb.close();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
