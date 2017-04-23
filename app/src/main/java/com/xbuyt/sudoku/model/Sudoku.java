package com.xbuyt.sudoku.model;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.fragments.BoardGameFragment;
import com.xbuyt.sudoku.fragments.KeyboardFragment;
import com.xbuyt.sudoku.fragments.LifeFragment;
import com.xbuyt.sudoku.util.AlertDialog;
import com.xbuyt.sudoku.util.Constants;

import java.util.ArrayList;
import java.util.Random;

public class Sudoku {
    public static int mode = 1;

    //总生命次数为5
    public static int lifeCounter = 4;

    private static int currentLevel = 0;//当前难度
    private static int currentNumber = 0;//当前题号

    public static int jiuGongGe[][] = new int[9][9];
    public static Boolean bl = false;
    public static int end_jiuGongGe[][] = new int[9][9];

    private static int[][] shuDu = new int[9][9];
    private static String[][] shuDu_gen = new String[9][9];

    public static ArrayList<int[][]> list = new ArrayList<int[][]>();
    public static ArrayList<int[][]> list_work = new ArrayList<int[][]>();

    //重新开始游戏
    public void resetGame(Context context, int numberOfCells, String level) {
        if (mode == 2) {
            bl = false;
            generateJiugongGe(0);
            int generateShuDu[][] = generateShuDu(end_jiuGongGe);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    shuDu_gen[i][j] = String.valueOf(generateShuDu[i][j]);
                }
            }
        } else if (mode == 1) {
            GameActivity.setTextLevel(level);
        } else if (mode == 3) {
            GameActivity.isPlayed = true;
        }
        currentLevel = numberOfCells;
        list.clear();
        list_work.clear();
        currentNumber = new Random().nextInt(Constants.BOARD_GAME[currentLevel].length);//boardGame_easy.length表示当前数组的长度
        BoardGameFragment.generateBoardGame(numberOfCells);
        LifeFragment.restartIcons(context);
        lifeCounter = 4;
        GameActivity.chronometer.setVisibility(View.VISIBLE);
        GameActivity.chronometer.setBase(SystemClock.elapsedRealtime());
        GameActivity.chronometer.start();
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);
        GameActivity.penPencilButton.setEnabled(true);
        KeyboardFragment.resetKeyboard();
        KeyboardFragment.setEnabledKeyboard(true);
        getBoardGameResult();
    }

    //赢了后的操作
    public void finishGame() {
        GameActivity.chronometer.stop();//计时暂停
        GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);//恢复模式为答题模式
        GameActivity.penPencilButton.setEnabled(false);//禁止点击答题模式Button
        KeyboardFragment.resetKeyboard();//把数字区域颜色统一为默认颜色
        KeyboardFragment.setEnabledKeyboard(false);//禁用数字区域的Button
    }

    //赢了后的操作
    public void winGame(Context context) {
        finishGame();
        AlertDialog.winner(context);//弹窗恭喜

    }

    //无法继续的操作
    public void loseGame(Context context) {
        finishGame();
        AlertDialog.gameOver(context);//弹窗再见
    }

    public static String[][] getBoardGame() {
        switch (mode) {
            case 1:
                return Constants.BOARD_GAME[currentLevel][currentNumber];
            case 2:
                return shuDu_gen;
            case 3:
                return GameActivity.network_Sudoku;
            default:
                return Constants.BOARD_GAME[currentLevel][currentNumber];

        }
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

    /**
     * 随机生成九宫格
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
                    end_jiuGongGe[i][j] = jiuGongGe[i][j];
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

    /**
     * 第二部分，通过第一部分生成的九宫格，对其中的81个数字随机选取46位（81-35=46）置零，以此形成数独游戏。
     *
     * @param ArryJiuGongGe
     * @return
     */
    public static int[][] generateShuDu(int ArryJiuGongGe[][]) {
        Random random = new Random();
        ArrayList list = new ArrayList(35);
        for (int i = 0; i < 35; i++) {
            int index = random.nextInt(81);
            while (list.contains(index)) {
                index = random.nextInt(81);
            }
            list.add(index);
        }
        int shuDu[][] = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!list.contains(i * 9 + j)) {
                    shuDu[i][j] = 0;
                } else {
                    shuDu[i][j] = ArryJiuGongGe[i][j];
                }
            }
        }
        return shuDu;
    }

    //打印二维数组a[m][n]
    public static void displayArray(int a[][], int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }


}