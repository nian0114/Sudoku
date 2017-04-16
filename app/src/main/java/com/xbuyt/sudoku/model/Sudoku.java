package com.xbuyt.sudoku.model;

import android.content.Context;
import android.os.SystemClock;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.fragments.BoardGameFragment;
import com.xbuyt.sudoku.fragments.KeyboardFragment;
import com.xbuyt.sudoku.fragments.LifeFragment;
import com.xbuyt.sudoku.util.AlertDialog;

public class Sudoku {
    //总生命次数为5
    private int lifeCounter = 4;

    //数独题目(暂时仅有一套题)
    private static String[][] boardGame = {{"8", "2", "7", "1", "5", "4", "3", "9", "6"},
            {"9", "6", "5", "3", "2", "7", "1", "4", "8"},
            {"3", "4", "1", "6", "8", "9", "7", "5", "2"},
            {"5", "9", "3", "4", "6", "8", "2", "7", "1"},
            {"4", "7", "2", "5", "1", "3", "6", "8", "9"},
            {"6", "1", "8", "9", "7", "2", "4", "3", "5"},
            {"7", "8", "6", "2", "3", "5", "9", "1", "4"},
            {"1", "5", "4", "7", "9", "6", "8", "2", "3"},
            {"2", "3", "9", "8", "4", "1", "5", "6", "7"}};

    //重新开始游戏
    public void resetGame(Context context, int numberOfCells, String level) {
        BoardGameFragment.generateBoardGame(numberOfCells);
        LifeFragment.restartIcons(context);
        lifeCounter = 4;
        GameActivity.setTextLevel(level);
        GameActivity.chronometer.setBase(SystemClock.elapsedRealtime());
        GameActivity.chronometer.start();
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);
        GameActivity.penPencilButton.setEnabled(true);
        KeyboardFragment.resetKeyboard();
        KeyboardFragment.setEnabledKeyboard(true);
    }

    //赢了后的操作
    public void winGame(Context context) {
        AlertDialog.winner(context);//弹窗恭喜
        GameActivity.chronometer.stop();//计时暂停
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);//恢复模式为答题模式
        GameActivity.penPencilButton.setEnabled(false);//禁止点击答题模式Button
        KeyboardFragment.resetKeyboard();//把数字区域颜色统一为默认颜色
        KeyboardFragment.setEnabledKeyboard(false);//禁用数字区域的Button
    }

    //无法继续的操作
    public void loseGame(Context context) {
        AlertDialog.gameOver(context);//弹窗再见
        GameActivity.chronometer.stop();
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);
        GameActivity.penPencilButton.setEnabled(false);
        KeyboardFragment.resetKeyboard();
        KeyboardFragment.setEnabledKeyboard(false);
    }

    public int getLifeCounter() {
        return lifeCounter;
    }

    public void setLifeCounter(int life_counter) {
        this.lifeCounter = life_counter;
    }

    public static String[][] getBoardGame() {
        return boardGame;
    }
}