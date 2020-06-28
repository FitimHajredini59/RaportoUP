package com.FIEK.raportoup.aktivitetet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.FIEK.raportoup.R;

public class FaqjaHyrese extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faqja_hyrese);
        LinearLayout linearLayout = findViewById(R.id.activity_faqja_hyrese);
        linearLayout.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(FaqjaHyrese.this, Login.class);
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}

