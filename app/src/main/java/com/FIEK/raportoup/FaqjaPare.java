package com.FIEK.raportoup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class FaqjaPare extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // per ngarkim te fotos
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private ImageView imageSelected;
//

    /* -----------per kamere --------------------- */
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    /* ------------------------------------- */


    String strExtras = "";

    /*-------------------------E shtuar per spinnerin--------------------------------*/
    private Spinner spinner;
    private static final String[] paths = {"të tjera", "profesor", "asistent", "zyrtar referent", "prodekan", "dekan"
            , "zyrtar të IT", "mbeturina", "dëmtime të objekteve"};
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
        spinner = (Spinner) findViewById(R.id.kategorite);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FaqjaPare.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        /*-------------------------E shtuar per spinnerin--------------------------------*/


        /* ++++++++++++++++++++++++++++++++Per ngarkim te fotos +++++++++++++++++++++++++++++++++++++++++++++ */

        //
        imageSelected = findViewById(R.id.selectedImage);
        //

        findViewById(R.id.ngarkofoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            FaqjaPare.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else {
                    selectImage();
                }
            }
        });
        /* +++++++++++++++++++++++++++++++ */

        /* ------------------- per kamere ----------------------------------------- */

        this.imageView = (ImageView) this.findViewById(R.id.selectedImage);
        ImageView imageView = this.findViewById(R.id.cameraButton);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            FaqjaPare.this,
                            new String[]{Manifest.permission.CAMERA},
                            MY_CAMERA_PERMISSION_CODE
                    );
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        /* --------------------------------- */

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

    /* ++++++++++++++++++++++++++++++++Per ngarkim te fotos +++++++++++++++++++++++++++++++++++++++++++++ */

    private void selectImage() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "Leja u mohua!", Toast.LENGTH_SHORT).show();
            }
        }

        // per kamere
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
        //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {

                        InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageSelected.setImageBitmap(bitmap);

                        // ******************************************
                        //Më poshtë është selected image file
                        //bëj çfarëdo që të duash të bësh me selected image file...
                        File selectedImageFiles = new File(getPathFromUri(selectedImageUri));
                        // ********************************************

                    } catch (Exception exception) {
                        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT);
                    }
                }
            }
        }

        //per kamere
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        //
    }

    // ***************************************
    private String getPathFromUri(Uri contentUri) {
        String filePath;
        Cursor cursor = getContentResolver()
                .query(contentUri, null, null, null, null);
        if (cursor == null) {
            filePath = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
    // ******************************************


}
