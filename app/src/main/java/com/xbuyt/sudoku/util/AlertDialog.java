package com.xbuyt.sudoku.util;

import android.content.Context;
import android.content.DialogInterface;

import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.model.Sudoku;

public class AlertDialog {

    public static void gameOver(final Context context) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);

        dialog.setTitle("Game over");
        dialog.setMessage("Do you want to restart?");
        dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sudoku sudoku = new Sudoku();
                sudoku.resetGame(context, Constants.MEDIUM_LEVEL_CELL_NUMBER, Constants.MEDIUM_LEVEL_TEXT);
            }
        });
        dialog.setNegativeButton(android.R.string.cancel, null);
        dialog.show();
    }

    public static void winner(final Context context) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);

        dialog.setTitle("You win");
        dialog.setMessage("Your score: " + GameActivity.chronometer.getText() + "\nDo you want to restart?");
        dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sudoku sudoku = new Sudoku();
                sudoku.resetGame(context, Constants.MEDIUM_LEVEL_CELL_NUMBER, Constants.MEDIUM_LEVEL_TEXT);
            }
        });
        dialog.setNegativeButton(android.R.string.cancel, null);
        dialog.show();
    }
}