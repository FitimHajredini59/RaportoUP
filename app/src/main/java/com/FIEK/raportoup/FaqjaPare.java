package com.FIEK.raportoup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FaqjaPare extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String strExtras = "";

    /*-------------------------E shtuar per spinnerin--------------------------------*/
    private Spinner spinner;
    private static final String[] paths = {"profesor", "asistent", "zyrtar referent", "prodekan", "dekan"
            , "zyrtar të IT", "mbeturina", "dëmtime fizike të objekteve", "të tjera"};
    /*-------------------------E shtuar per spinnerin--------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqja_pare);

        if (getIntent().getExtras() != null)
            strExtras = getIntent().getExtras().getString("username");

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("Mirë se vini, " + strExtras + "!");

        /*-------------------------E shtuar per spinnerin--------------------------------*/
        spinner = (Spinner)findViewById(R.id.kategorite);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(FaqjaPare.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        /*-------------------------E shtuar per spinnerin--------------------------------*/
    }



    /*-------------------------E shtuar per spinnerin--------------------------------*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 4:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 5:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 6:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 7:
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 8:
                // Whatever you want to happen when the thrid item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
    /*-------------------------E shtuar per spinnerin--------------------------------*/

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
