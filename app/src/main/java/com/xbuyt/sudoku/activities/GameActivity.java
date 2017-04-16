package com.xbuyt.sudoku.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.model.Sudoku;
import com.xbuyt.sudoku.util.Constants;

public class GameActivity extends AppCompatActivity {

    Sudoku sudoku = new Sudoku();

    private static TextView textLevel;
    public static Button penPencilButton;
    public static int penPencilOption = Constants.PEN_MODE;
    public static Chronometer chronometer;

    private Context context;

    private long lastStopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game);

        textLevel = (TextView) findViewById(R.id.activity_board_game_level_text);
        penPencilButton = (Button) findViewById(R.id.activity_board_game_pen_pencil_button);
        chronometer = (Chronometer) findViewById(R.id.activity_board_game___chronometer);


        context = getApplicationContext();


        penPencilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (penPencilOption == Constants.PEN_MODE) {
                    penPencilOption = Constants.PENCIL_MODE;
                    penPencilButton.setText(getResources().getString(R.string.activity_board_game_pencil_text));
                } else if (penPencilOption == Constants.PENCIL_MODE) {
                    penPencilOption = Constants.PEN_MODE;
                    penPencilButton.setText(getResources().getString(R.string.activity_board_game_pen_text));
                }
            }
        });
        sudoku.resetGame(context, Constants.MEDIUM_LEVEL_CELL_NUMBER, Constants.MEDIUM_LEVEL_TEXT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
        lastStopTime = SystemClock.elapsedRealtime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lastStopTime == 0) {    // On first start
            chronometer.setBase(SystemClock.elapsedRealtime());
        } else {                    // On resume after pause
            long intervalOnPause = (SystemClock.elapsedRealtime() - lastStopTime);
            chronometer.setBase(chronometer.getBase() + intervalOnPause);
        }
        chronometer.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.level_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_level___easy) {
            sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);
        } else if (id == R.id.menu_level___medium) {
            sudoku.resetGame(context, Constants.MEDIUM_LEVEL_CELL_NUMBER, Constants.MEDIUM_LEVEL_TEXT);
        } else if (id == R.id.menu_level___hard) {
            sudoku.resetGame(context, Constants.HARD_LEVEL_CELL_NUMBER, Constants.HARD_LEVEL_TEXT);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setTextLevel(String text) {
        textLevel.setText(text);
    }
}