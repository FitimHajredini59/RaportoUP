package com.FIEK.raportoup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    //
    EditText etKoment;

    //lokacion
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private TextView textLatLong, textAdresa;
    private ProgressBar progressBar;
    private ResultReceiver resultReceiver;


    Button dergo;
    String spinner_value;
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                spinner_value = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        /*----------------------------------------------------------------------------*/


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
        final ImageView imageView = this.findViewById(R.id.cameraButton);
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
        /* -------------------------------------------------------------------------------- */


        /* """"""""""""""""""""""""""""""""""""""" Lokacioni """""""""""""""""""""""""""""""""""""""""""" */

        resultReceiver = new AddressResultReceiver(new Handler());

        textLatLong = findViewById(R.id.textLatLong);
        textAdresa = findViewById(R.id.textAdresa);
        progressBar = findViewById(R.id.progressBar);
        findViewById(R.id.buttonGetCurrentLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            FaqjaPare.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                } else {
                    getCurrentLocation();
                }

            }
        });

        /* """""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""" */

        /* --------------------------------- */
        etKoment = findViewById(R.id.koment);

        dergo = findViewById(R.id.dergo);
        dergo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                Bitmap b = ((BitmapDrawable) imageSelected.getDrawable()).getBitmap();
                /*krijo objektin e ByteArrayoutputStream class.
                Ndaje foton ne pjese bajt, duke thirr toByteArray()
                nga ByteOutputStream class dhe ruaj ate ne nje varg */
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] img = bos.toByteArray();

                //
                ContentValues cvv = new ContentValues();
                cvv.put(Raporti_i_ri.Username, strExtras);
                cvv.put(Raporti_i_ri.Koment, etKoment.getText().toString());
                cvv.put(Raporti_i_ri.Kategorite, spinner_value);
                cvv.put(Raporti_i_ri.SelectedImage, img);
                cvv.put(Raporti_i_ri.Koha, getDateTime());
                cvv.put(Raporti_i_ri.Adresa, textAdresa.getText().toString());

                SQLiteDatabase objDb = new Databaza(FaqjaPare.this).getWritableDatabase();


                try {
                    long retValue1 = objDb.insert(Databaza.RaportiRiTable, null, cvv);
                    if (retValue1 > 0) {
                        Toast.makeText(FaqjaPare.this, " Raporti u dërgua me sukses! ",
                                Toast.LENGTH_LONG).show();
                        //
                        Intent intentRaportetMia = new Intent(FaqjaPare.this, RaportetMia.class);
                        startActivity(intentRaportetMia);
                        //
                    }
                } catch (Exception ex) {
                    Log.e("except", ex.getMessage());
                } finally {
                    objDb.close();
                }
            }
        });
    }

    //Koha
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    //
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
    /*---------------------------------------------------------------------------*/

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
                Toast.makeText(this, "u lejua perdorimi kameres", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "nuk u lejua perdorimi kameres", Toast.LENGTH_LONG).show();
            }
        }
        //

        //per lokacion
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Leja u mohua!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
        progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(FaqjaPare.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(FaqjaPare.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocationIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude =
                                    locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            textLatLong.setText(
                                    String.format(
                                            "Latitude: %s\nLongitude: %s",
                                            latitude,
                                            longitude
                                    )
                            );
                            //
                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAdressFromLatLong(location);

                        } else {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, Looper.getMainLooper());
    }

    private void fetchAdressFromLatLong(Location location) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Konstanta.RECEIVER, resultReceiver);
        intent.putExtra(Konstanta.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Konstanta.SUCCESS_RESULT) {
                textAdresa.setText(resultData.getString(Konstanta.RESULT_DATA_KEY));
            } else {
                Toast.makeText(FaqjaPare.this, resultData.getString(Konstanta.RESULT_DATA_KEY), Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }
    }
//

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
                        //            File selectedImageFiles = new File(getPathFromUri(selectedImageUri));


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
//    private String getPathFromUri(Uri contentUri) {
//        String filePath;
//        Cursor cursor = getContentResolver()
//                .query(contentUri, null, null, null, null);
//        if (cursor == null) {
//            filePath = contentUri.getPath();
//        } else {
//            cursor.moveToFirst();
//            int index = cursor.getColumnIndex(Raporti_i_ri.SelectedImage);
//            filePath = cursor.getString(index);
//            cursor.close();
//        }
//        return filePath;
//    }
//    // ******************************************
}
