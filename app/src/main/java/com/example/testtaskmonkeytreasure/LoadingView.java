package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class LoadingView extends AppCompatActivity {

    private String currentUSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view);

        Intent intent = getIntent();

        DownloadJSONTask task = new DownloadJSONTask();
        task.execute("https://www.cbr-xml-daily.ru/daily_json.js");
        try {
            currentUSD = task.get();
           // System.out.println("currentUSD="+ currentUSD);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Задержка перехода в активность activity_current_usd
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingView.this, CurrentUsd.class);
                intent.putExtra("currentUSD", currentUSD);
                startActivity(intent);
                finish();
            }
        }, 2 * 1000);  // задержка перехода на 2 сек


    }


    public static class DownloadJSONTask extends AsyncTask<String, Void, String>{

        private String currentUSD;

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null){
                    result.append(line);
                    line = reader.readLine();
                }

                String s = result.toString();
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObject1 = jsonObject.getJSONObject("Valute");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("USD");
                currentUSD = jsonObject2.getString("Value");
                 //Log.i("MyResult3333", currentUSD);

                return currentUSD;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
            }
            return null;
        }//doInBackground
    }//class DownloadJSON
} // class LoadingView
