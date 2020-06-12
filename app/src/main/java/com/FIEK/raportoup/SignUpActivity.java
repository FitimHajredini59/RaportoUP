package com.FIEK.raportoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    TextView Hapesira;
    EditText ID, Email, Username, Password, Password2;
    Button LogInBtn, SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ID = (EditText) findViewById(R.id.idja);
        Email = (EditText) findViewById(R.id.email);
        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        Password2 = (EditText) findViewById(R.id.password2);
        SignUpBtn = (Button) findViewById(R.id.signupbtn);
        LogInBtn = (Button) findViewById(R.id.loginbtn);

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSignInActivity();
            }
        });

    }

    private void openSignInActivity() {
        Intent LogIn = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(LogIn);
    }
}
