package com.example.testtaskmonkeytreasure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MonkeyTreasureActivity extends AppCompatActivity {

    private Dialog dialogPreview;
    private Dialog dialogGameOver;
    private Dialog dialogWinner;
    private GridView gridView;
    private final int victoryNum = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey_treasure);

        Intent intent = getIntent();
        // скрыть строку состояния
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // вызов диалогового окна с заданием
        previewDialog();

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Toast.makeText(MonkeyTreasureActivity.this, "toast " + position, Toast.LENGTH_SHORT).show();
                if(position == victoryNum) {
                    ((ImageView) itemClicked).setImageResource(R.drawable.win_gold);
                    // вызов диалогового окна "Ты помог мартышке найти золото. Заново?"
                    winnerDialog();
                } else
                // если нажата кнопка с пустой ячейкой
                if(logikGame1(position)) {
                    ((ImageView) itemClicked).setImageResource(R.drawable.cell_pressed);
                } else if (logikGame2(position)){
                    ((ImageView) itemClicked).setImageResource(R.drawable.slot_scorpio);
                    // вызов диалогового окна "Ты проиграл.Заново?"
                    gameOverDialog();
                }else if (logikGame3(position)){
                    ((ImageView) itemClicked).setImageResource(R.drawable.slot_snake);
                    gameOverDialog();
                } else {
                    ((ImageView) itemClicked).setImageResource(R.drawable.cell_pressed);
                }

            }
        });

    } // onCreate

    //ДИАЛОГОВОЕ ОКНО С ЗАДАНИЕМ
    private void previewDialog(){
        dialogPreview = new Dialog(this);
        dialogPreview.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPreview.setContentView(R.layout.previewdialog);
        dialogPreview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон за диалоговым окном
        dialogPreview.setCancelable(false); // окно нельзя закрыть системной кнопкой назад
        // кнопка "НАЧАТЬ"
        Button button = (Button) dialogPreview.findViewById(R.id.buttonContinue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogPreview.dismiss();
            }
        });
        dialogPreview.show();
    } // previewDialog()

    //ДИАЛОГОВОЕ ОКНО "Ты проиграл.Заново?"
    private void gameOverDialog(){
        dialogGameOver = new Dialog(MonkeyTreasureActivity.this);
        dialogGameOver.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogGameOver.setContentView(R.layout.game_over_dialog);
        dialogGameOver.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон за диалоговым окном
        dialogGameOver.setCancelable(false); // окно нельзя закрыть системной кнопкой назад
        // кнопка "ЗАНОВО"
        Button button = (Button) dialogGameOver.findViewById(R.id.buttonRepeat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonkeyTreasureActivity.this, MonkeyTreasureActivity.class);
                startActivity(intent); finish();
                dialogGameOver.dismiss();
            }
        });
        dialogGameOver.show();
    } // gameOver()

    //ДИАЛОГОВОЕ ОКНО "Ты помог мартышке найти золото.Заново?"
    private void winnerDialog(){
        dialogWinner = new Dialog(MonkeyTreasureActivity.this);
        dialogWinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWinner.setContentView(R.layout.winner_dialog);
        dialogWinner.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // прозрачный фон за диалоговым окном
        dialogWinner.setCancelable(false); // окно нельзя закрыть системной кнопкой назад
        // кнопка "ЗАНОВО"
        Button button = (Button) dialogWinner.findViewById(R.id.buttonWinner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MonkeyTreasureActivity.this, MonkeyTreasureActivity.class);
                startActivity(intent); finish();
                dialogWinner.dismiss();
            }
        });
        dialogWinner.show();
    } // winnerDialog()

    private boolean logikGame1(int position){
        int cellEmpty[] = new int[]{13,14,23,31,32,40,49,58,67,76,77,78,79,80,72,73,74,75};
        for (int i = 0; i < cellEmpty.length; i++) {
            if(position == cellEmpty[i]) return true;
        }
        return false;
    }
    private boolean logikGame2(int position){
        int cellScorpio[] = new int[]{12,15,21,22,24,30,33,39,41,42,48,50,57,59,64,65,66,68,69,70};
        for (int i = 0; i < cellScorpio.length; i++) {
            if(position == cellScorpio[i]) return true;
        }
        return false;
    }
    private boolean logikGame3(int position){
        int cellSnake[] = new int[]{0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,44,45,53,54,62,63,71};
        for (int i = 0; i < cellSnake.length; i++) {
            if(position == cellSnake[i]) return true;
        }
        return false;
    }

} // class MonkeyTreasureActivity
