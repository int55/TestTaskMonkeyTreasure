package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MonkeyTreasureActivity extends AppCompatActivity {

    private Dialog dialog;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_treasure);

        imageView = (ImageView) findViewById(R.id.line1_btn1);

        Intent intent = getIntent();

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон за диалоговым окном
        dialog.setCancelable(false); // окно нельзя закрыть системной кнопкой назад

        //кнопка "НАЧАТЬ"
        Button button = (Button) dialog.findViewById(R.id.buttonStart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

        //подключение анимации
        final Animation a = AnimationUtils.loadAnimation(MonkeyTreasureActivity.this, R.anim.alfa);

        setImage(imageView);
    }

    private void setImage(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.cell_pressed);
            }
        });
//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                //касание картинки
//                if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
//                    //если коснулся картинки
//                    imageView.setImageResource(R.drawable.cell_pressed);
//                    //imgAllBtnBlock.setEnabel(false) // БЛОКИРУЕТ НАЖАТИЕ КАРТИНКИ!!!!!
//                }else if (motionEvent.getAction()==MotionEvent.ACTION_UP){
//                    //если отпустил палец
//
//                }
//                return true;
//            }
//        });
    }


}
