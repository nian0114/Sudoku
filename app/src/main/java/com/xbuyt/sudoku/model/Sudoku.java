package com.xbuyt.sudoku.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.fragments.BoardGameFragment;
import com.xbuyt.sudoku.fragments.KeyboardFragment;
import com.xbuyt.sudoku.fragments.LifeFragment;
import com.xbuyt.sudoku.util.AlertDialog;
import com.xbuyt.sudoku.util.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Sudoku {
    //总生命次数为5
    public static int lifeCounter = 4;

    private static int currentLevel = 0;//当前难度
    private static int currentNumber = 0;//当前题号

    private static int[][] shuDu = new int[9][9];
    public static ArrayList<int[][]> list = new ArrayList<int[][]>();
    public static ArrayList<int[][]> list_work = new ArrayList<int[][]>();

    //重新开始游戏
    public void resetGame(Context context, int numberOfCells, String level) {
        currentLevel = numberOfCells;
        list.clear();
        list_work.clear();
        currentNumber = new Random().nextInt(Constants.BOARD_GAME[currentLevel].length);//boardGame_easy.length表示当前数组的长度
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
        getBoardGameResult();
    }

    //赢了后的操作
    public void winGame(Context context) {
        AlertDialog.winner(context);//弹窗恭喜
        GameActivity.chronometer.stop();//计时暂停
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);//恢复模式为答题模式
        GameActivity.penPencilButton.setEnabled(false);//禁止点击答题模式Button
        KeyboardFragment.resetKeyboard();//把数字区域颜色统一为默认颜色
        KeyboardFragment.setEnabledKeyboard(false);//禁用数字区域的Button
        SharedPreferences sp = context.getSharedPreferences("loginUser", 0);
        DateFormat format = new SimpleDateFormat("mm:ss", Locale.US);

        try {
            Date date1 = format.parse(GameActivity.chronometer.getText().toString());
            Date date2 = format.parse(sp.getString("chronometer", "00:00"));
            if (date1.before(date2)) {
                SharedPreferences mSharedPreferences = context.getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("chronometer", GameActivity.chronometer.getText().toString());
                editor.apply();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public static String[][] getBoardGame() {
        return Constants.BOARD_GAME[currentLevel][currentNumber];
    }

    public static void getBoardGameResult() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                shuDu[i][j] = Integer.parseInt(getBoardGame()[i][j]);
            }

        shuDu_solution(0);
    }


    /**
     * 判断在九宫格中的坐标(x,y)的位置上插入value，是否符合规则
     *
     * @param x
     * @param y
     * @param value
     * @return
     */
    public static Boolean legal(int a[][], int x, int y, int value) {

        for (int i = 0; i < 9; i++) {
            //如果列中有value，则返回false
            if (i != x && a[i][y] == value) {
                return false;
            }
            //如果行中有value，则返回false
            if (i != y && a[x][i] == value) {
                return false;
            }
        }

        //(minX,minY)是(x,y)所属小九宫格的左上角的坐标
        int minX = x / 3 * 3;
        int minY = y / 3 * 3;

        for (int i = minX; i < minX + 3; i++) {
            for (int j = minY; j < minY + 3; j++) {
                //如果小九宫格中的非(x,y)的坐标上的值为value，返回false
                if (i != x && j != y && a[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void shuDu_solution(int k) {
        if (k == 81) {
            /*
            修复ArrayList的值均为原始值的问题
                        list.add(shuDu);
            为错误写法，应该new个临时变量，进行add
            原因是ArrayList.add相当于是做指针链接
             */
            int[][] result = new int[9][9];
            for (int i = 0; i < 9; i++)
                System.arraycopy(shuDu[i], 0, result[i], 0, 9);
            list.add(result);
            return;
        }
        int x = k / 9;
        int y = k % 9;
        if (shuDu[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                shuDu[x][y] = i;
                if (legal(shuDu, x, y, i)) {
                    shuDu_solution(k + 1);
                }
            }
            shuDu[x][y] = 0;
        } else {
            shuDu_solution(k + 1);
        }
    }
}