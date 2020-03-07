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

    private String currentUSD1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view);

        Intent intent = getIntent();

        DownloadJSONTask task = new DownloadJSONTask();
        task.execute("https://www.cbr-xml-daily.ru/daily_json.js");
        try {
            String curUSD = task.get();
            System.out.println("currentUSD1= " + curUSD + " *********************************************");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // задержка перехода в активность activity_current_usd
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingView.this, CurrentUsd.class);
                intent.putExtra("currentUSD", currentUSD1);
                startActivity(intent);
                finish();
            }
        }, 2 * 1000);  // задержка перехода на 2 сек


    }

//    public void onShowMessage() throws ExecutionException, InterruptedException {
//        DownloadJSONTask task = new DownloadJSONTask();
//        task.execute("https://www.cbr-xml-daily.ru/daily_json.js");
//        String curUSD = task.get();
//    }

    public static class DownloadJSONTask extends AsyncTask<String, Void, String>{
        private String currentUSD;

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();
            //String currentUSD;
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
                Log.i("11111111111111111", "*********************************");

                String s = result.toString();
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObject1 = jsonObject.getJSONObject("Valute");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("USD");
                currentUSD = jsonObject2.getString("Value");
                 Log.i("MyResult3333", currentUSD);
                //return result.toString();
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
            return "hello My frend";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("MyResult", s);
            currentUSD = s;
            Log.i("MyResultC", s);
        }


    }
}
