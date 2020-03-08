package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CurrentUsd extends AppCompatActivity {

    private TextView textViewCurrentUSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_usd);

        Intent intent = getIntent();
        String currentUSD = intent.getStringExtra("currentUSD");

        textViewCurrentUSD = (TextView) findViewById(R.id.textViewCurrentUSD);
        textViewCurrentUSD.setText("Текущее значение доллара США:\n"+currentUSD);

    }

    public void goToBankiRu(View view) {
        Intent intent = new Intent(CurrentUsd.this, WebViewBankiRu.class);
        startActivity(intent);
    }
}
