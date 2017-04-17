package com.xbuyt.sudoku.model;

import android.content.Context;
import android.os.SystemClock;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.fragments.BoardGameFragment;
import com.xbuyt.sudoku.fragments.KeyboardFragment;
import com.xbuyt.sudoku.fragments.LifeFragment;
import com.xbuyt.sudoku.util.AlertDialog;

import java.util.ArrayList;
import java.util.Random;

public class Sudoku_Normal {
    //总生命次数为5
    public static int lifeCounter = 4;

    public static String[][] boardGame;
    private static int jiuGongGe[][] = new int[9][9];
    private static Boolean bl = false;
    private static String end_jiuGongGe[][] = new String[9][9];

    //重新开始游戏
    public void resetGame(Context context, int numberOfCells, String level) {
        generateJiugongGe(0);
        boardGame = end_jiuGongGe;
        displayArray(boardGame, 9, 9);
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

    /**
     * 随机生成九宫格的算法，通过回溯生成。
     *
     * @param k
     */
    public static void generateJiugongGe(int k) {
        if (bl) {
            return;
        }
        if (k == 81) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    end_jiuGongGe[i][j] = String.valueOf(jiuGongGe[i][j]);
                }
            }
            bl = true;
            return;
        }

        //取得第k+1个值所对应的坐标(x,y),k是从0开始的。
        int x = k / 9;
        int y = k % 9;

        if (jiuGongGe[x][y] == 0) {
            //index用来判断是否已经完全随机生成了1-9这个9个数
            int index = 0;
            while (index < 9) {
                //动态数组list用来储存已经随机生成的1-9的数字
                ArrayList list = new ArrayList(9);
                Random random = new Random();
                int i = random.nextInt(9) + 1;
                //当list中包含数字i时，在重新生成1-9的数字
                while (list.contains(i))
                    i = random.nextInt(9) + 1;
                list.add(i);
                index++;
                jiuGongGe[x][y] = i;
                //legal()函数是判断在九宫格中的坐标(x,y)的位置上插入i，是否符合规则
                if (legal(jiuGongGe, x, y, i)) {
                    generateJiugongGe(k + 1);
                }
            }
            jiuGongGe[x][y] = 0;     //回溯时，将坐标(x,y)的值置零

        } else {
            generateJiugongGe(k + 1);
        }
    }

    public static void displayArray(String a[][], int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] getBoardGame() {

        return boardGame;
    }
}