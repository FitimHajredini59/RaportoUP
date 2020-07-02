package com.FIEK.raportoup.utilities;


import android.os.AsyncTask;

import com.FIEK.raportoup.fragmentet.InfoUP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchDataUP extends AsyncTask<Void, Void, Void> {
    String data = "";
    String singleParsed1 = "";
    String singleParsed2 = "";
    String singleParsed3 = "";
    String singleParsed4 = "";

    @Override
    protected Void doInBackground(Void... voids) {

        //url request
        URL url = null;
        try {
            try {
                url = new URL("http://up-data.herokuapp.com/updata");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);

            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed1 = JO.get("student") + "\nStudentë";
                singleParsed2 = JO.get("fakultete") + "\nFakultete";
                singleParsed3 = JO.get("personel") + "\nPersonel";
                singleParsed4 = JO.get("administrate") + "\nAdministratë";
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        InfoUP.data1.setText(this.singleParsed1);
        InfoUP.data2.setText(this.singleParsed2);
        InfoUP.data3.setText(this.singleParsed3);
        InfoUP.data4.setText(this.singleParsed4);
    }
}
