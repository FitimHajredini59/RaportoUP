package com.FIEK.raportoup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

public class FaqjaPare extends AppCompatActivity {

    String strExtras = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqja_pare);

        if (getIntent().getExtras() != null)
            strExtras = getIntent().getExtras().getString("username");

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("Mirë se vini, " + strExtras);
    }

    boolean getBack = false;

    @Override
    public void onBackPressed() {

        if (getBack)
            super.onBackPressed();

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(FaqjaPare.this);
        alertDialog.setTitle(R.string.title_name);
        alertDialog.setMessage(R.string.alert_message);
        getBack = false;
        alertDialog.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getBack = true;
                onBackPressed();
            }
        });
        alertDialog.setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();


    }
}
