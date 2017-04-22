package com.xbuyt.sudoku.util;

import android.content.Context;
import android.content.DialogInterface;

import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.model.Sudoku;

public class AlertDialog {

    public static void gameOver(final Context context) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);//新建Dialog类型的变量

        dialog.setTitle("Game over");//提示游戏结束
        dialog.setMessage("Do you want to restart?");//你想重来吗
        dialog.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {//选择是，重开游戏
                Sudoku sudoku = new Sudoku();
                sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);
            }
        });
        dialog.setNegativeButton(android.R.string.cancel, null);//如果选择取消，就算了，什么都别做
        dialog.show();//显示对话框
    }

    public static void winner(final Context context) {
        android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(context);

        dialog.setTitle("You win");
        dialog.setMessage("Your score: " + GameActivity.chronometer.getText() + "\nDo you want to restart?");//多了个显示当前用时
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