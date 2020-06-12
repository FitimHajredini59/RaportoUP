package com.FIEK.raportoup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Hapesira;
    EditText Username, Password;
    Button LogInBtn, SignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        LogInBtn = (Button) findViewById(R.id.loginbtn);
        SignUpBtn = (Button) findViewById(R.id.signupbtn);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSignUpActivity();
            }
        });

    }

    private void openSignUpActivity() {
        Intent SignUp = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(SignUp);
    }

}
