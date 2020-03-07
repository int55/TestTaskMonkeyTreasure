package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startGame(View view) {
        Intent intent = new Intent(MainActivity.this, MonkeyTreasureActivity.class);
        startActivity(intent);
    }

    public void startWebView(View view) {
        Intent intent = new Intent(MainActivity.this, LoadingView.class);
        startActivity(intent);
    }
}
