package com.FIEK.raportoup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class FaqjaHyrese extends AppCompatActivity {

    private RelativeLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faqja_hyrese);
        LinearLayout llayout = (LinearLayout) findViewById(R.id.activity_faqja_hyrese);
        llayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intt= new Intent(FaqjaHyrese.this, LoginActivity.class);
                    startActivity(intt);
                }
            }
        );
    }
}

