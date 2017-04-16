package com.xbuyt.sudoku.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.model.Sudoku;

import java.util.Random;

public class BoardGameFragment extends Fragment {

    Sudoku sudoku = new Sudoku();


    private CellFragment cell_1_1;
    private CellFragment cell_1_2;
    private CellFragment cell_1_3;
    private CellFragment cell_1_4;
    private CellFragment cell_1_5;
    private CellFragment cell_1_6;
    private CellFragment cell_1_7;
    private CellFragment cell_1_8;
    private CellFragment cell_1_9;

    private CellFragment cell_2_1;
    private CellFragment cell_2_2;
    private CellFragment cell_2_3;
    private CellFragment cell_2_4;
    private CellFragment cell_2_5;
    private CellFragment cell_2_6;
    private CellFragment cell_2_7;
    private CellFragment cell_2_8;
    private CellFragment cell_2_9;

    private CellFragment cell_3_1;
    private CellFragment cell_3_2;
    private CellFragment cell_3_3;
    private CellFragment cell_3_4;
    private CellFragment cell_3_5;
    private CellFragment cell_3_6;
    private CellFragment cell_3_7;
    private CellFragment cell_3_8;
    private CellFragment cell_3_9;

    private CellFragment cell_4_1;
    private CellFragment cell_4_2;
    private CellFragment cell_4_3;
    private CellFragment cell_4_4;
    private CellFragment cell_4_5;
    private CellFragment cell_4_6;
    private CellFragment cell_4_7;
    private CellFragment cell_4_8;
    private CellFragment cell_4_9;

    private CellFragment cell_5_1;
    private CellFragment cell_5_2;
    private CellFragment cell_5_3;
    private CellFragment cell_5_4;
    private CellFragment cell_5_5;
    private CellFragment cell_5_6;
    private CellFragment cell_5_7;
    private CellFragment cell_5_8;
    private CellFragment cell_5_9;

    private CellFragment cell_6_1;
    private CellFragment cell_6_2;
    private CellFragment cell_6_3;
    private CellFragment cell_6_4;
    private CellFragment cell_6_5;
    private CellFragment cell_6_6;
    private CellFragment cell_6_7;
    private CellFragment cell_6_8;
    private CellFragment cell_6_9;

    private CellFragment cell_7_1;
    private CellFragment cell_7_2;
    private CellFragment cell_7_3;
    private CellFragment cell_7_4;
    private CellFragment cell_7_5;
    private CellFragment cell_7_6;
    private CellFragment cell_7_7;
    private CellFragment cell_7_8;
    private CellFragment cell_7_9;

    private CellFragment cell_8_1;
    private CellFragment cell_8_2;
    private CellFragment cell_8_3;
    private CellFragment cell_8_4;
    private CellFragment cell_8_5;
    private CellFragment cell_8_6;
    private CellFragment cell_8_7;
    private CellFragment cell_8_8;
    private CellFragment cell_8_9;

    private CellFragment cell_9_1;
    private CellFragment cell_9_2;
    private CellFragment cell_9_3;
    private CellFragment cell_9_4;
    private CellFragment cell_9_5;
    private CellFragment cell_9_6;
    private CellFragment cell_9_7;
    private CellFragment cell_9_8;
    private CellFragment cell_9_9;


    private static CellFragment arrayCell[][] = new CellFragment[9][9];

    public BoardGameFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_game, container, false);


        cell_1_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_1);
        cell_1_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_2);
        cell_1_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_3);
        cell_1_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_4);
        cell_1_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_5);
        cell_1_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_6);
        cell_1_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_7);
        cell_1_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_8);
        cell_1_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_1_9);

        cell_2_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_1);
        cell_2_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_2);
        cell_2_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_3);
        cell_2_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_4);
        cell_2_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_5);
        cell_2_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_6);
        cell_2_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_7);
        cell_2_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_8);
        cell_2_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_2_9);

        cell_3_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_1);
        cell_3_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_2);
        cell_3_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_3);
        cell_3_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_4);
        cell_3_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_5);
        cell_3_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_6);
        cell_3_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_7);
        cell_3_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_8);
        cell_3_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_3_9);

        cell_4_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_1);
        cell_4_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_2);
        cell_4_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_3);
        cell_4_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_4);
        cell_4_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_5);
        cell_4_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_6);
        cell_4_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_7);
        cell_4_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_8);
        cell_4_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_4_9);

        cell_5_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_1);
        cell_5_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_2);
        cell_5_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_3);
        cell_5_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_4);
        cell_5_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_5);
        cell_5_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_6);
        cell_5_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_7);
        cell_5_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_8);
        cell_5_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_5_9);

        cell_6_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_1);
        cell_6_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_2);
        cell_6_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_3);
        cell_6_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_4);
        cell_6_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_5);
        cell_6_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_6);
        cell_6_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_7);
        cell_6_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_8);
        cell_6_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_6_9);

        cell_7_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_1);
        cell_7_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_2);
        cell_7_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_3);
        cell_7_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_4);
        cell_7_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_5);
        cell_7_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_6);
        cell_7_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_7);
        cell_7_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_8);
        cell_7_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_7_9);

        cell_8_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_1);
        cell_8_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_2);
        cell_8_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_3);
        cell_8_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_4);
        cell_8_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_5);
        cell_8_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_6);
        cell_8_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_7);
        cell_8_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_8);
        cell_8_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_8_9);

        cell_9_1 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_1);
        cell_9_2 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_2);
        cell_9_3 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_3);
        cell_9_4 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_4);
        cell_9_5 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_5);
        cell_9_6 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_6);
        cell_9_7 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_7);
        cell_9_8 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_8);
        cell_9_9 = (CellFragment) getChildFragmentManager().findFragmentById(R.id.activity_board_game_cell_9_9);

        arrayCell[0][0] = cell_1_1;
        arrayCell[0][1] = cell_1_2;
        arrayCell[0][2] = cell_1_3;
        arrayCell[0][3] = cell_1_4;
        arrayCell[0][4] = cell_1_5;
        arrayCell[0][5] = cell_1_6;
        arrayCell[0][6] = cell_1_7;
        arrayCell[0][7] = cell_1_8;
        arrayCell[0][8] = cell_1_9;

        arrayCell[1][0] = cell_2_1;
        arrayCell[1][1] = cell_2_2;
        arrayCell[1][2] = cell_2_3;
        arrayCell[1][3] = cell_2_4;
        arrayCell[1][4] = cell_2_5;
        arrayCell[1][5] = cell_2_6;
        arrayCell[1][6] = cell_2_7;
        arrayCell[1][7] = cell_2_8;
        arrayCell[1][8] = cell_2_9;

        arrayCell[2][0] = cell_3_1;
        arrayCell[2][1] = cell_3_2;
        arrayCell[2][2] = cell_3_3;
        arrayCell[2][3] = cell_3_4;
        arrayCell[2][4] = cell_3_5;
        arrayCell[2][5] = cell_3_6;
        arrayCell[2][6] = cell_3_7;
        arrayCell[2][7] = cell_3_8;
        arrayCell[2][8] = cell_3_9;

        arrayCell[3][0] = cell_4_1;
        arrayCell[3][1] = cell_4_2;
        arrayCell[3][2] = cell_4_3;
        arrayCell[3][3] = cell_4_4;
        arrayCell[3][4] = cell_4_5;
        arrayCell[3][5] = cell_4_6;
        arrayCell[3][6] = cell_4_7;
        arrayCell[3][7] = cell_4_8;
        arrayCell[3][8] = cell_4_9;

        arrayCell[4][0] = cell_5_1;
        arrayCell[4][1] = cell_5_2;
        arrayCell[4][2] = cell_5_3;
        arrayCell[4][3] = cell_5_4;
        arrayCell[4][4] = cell_5_5;
        arrayCell[4][5] = cell_5_6;
        arrayCell[4][6] = cell_5_7;
        arrayCell[4][7] = cell_5_8;
        arrayCell[4][8] = cell_5_9;

        arrayCell[5][0] = cell_6_1;
        arrayCell[5][1] = cell_6_2;
        arrayCell[5][2] = cell_6_3;
        arrayCell[5][3] = cell_6_4;
        arrayCell[5][4] = cell_6_5;
        arrayCell[5][5] = cell_6_6;
        arrayCell[5][6] = cell_6_7;
        arrayCell[5][7] = cell_6_8;
        arrayCell[5][8] = cell_6_9;

        arrayCell[6][0] = cell_7_1;
        arrayCell[6][1] = cell_7_2;
        arrayCell[6][2] = cell_7_3;
        arrayCell[6][3] = cell_7_4;
        arrayCell[6][4] = cell_7_5;
        arrayCell[6][5] = cell_7_6;
        arrayCell[6][6] = cell_7_7;
        arrayCell[6][7] = cell_7_8;
        arrayCell[6][8] = cell_7_9;

        arrayCell[7][0] = cell_8_1;
        arrayCell[7][1] = cell_8_2;
        arrayCell[7][2] = cell_8_3;
        arrayCell[7][3] = cell_8_4;
        arrayCell[7][4] = cell_8_5;
        arrayCell[7][5] = cell_8_6;
        arrayCell[7][6] = cell_8_7;
        arrayCell[7][7] = cell_8_8;
        arrayCell[7][8] = cell_8_9;

        arrayCell[8][0] = cell_9_1;
        arrayCell[8][1] = cell_9_2;
        arrayCell[8][2] = cell_9_3;
        arrayCell[8][3] = cell_9_4;
        arrayCell[8][4] = cell_9_5;
        arrayCell[8][5] = cell_9_6;
        arrayCell[8][6] = cell_9_7;
        arrayCell[8][7] = cell_9_8;
        arrayCell[8][8] = cell_9_9;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                arrayCell[r][c].cellClicked(sudoku, r, c);
            }
        }
        return view;
    }

    public static void generateBoardGame(int numberOfCells) {
        clearBoardGame(arrayCell);
        printRandomNumberInBoardGame(arrayCell, Sudoku.getBoardGame(), numberOfCells);

    }

    private static void printRandomNumberInBoardGame(CellFragment[][] arrayCellFragment, String[][] boardGame, int numberOfCells) {
        Random rndRow = new Random();
        Random rndColumn = new Random();
        for (int i = 0; i < numberOfCells; i++) {
            int r = rndRow.nextInt(9);
            int c = rndColumn.nextInt(9);
            arrayCellFragment[r][c].setMainNumber(boardGame[r][c]);
            arrayCellFragment[r][c].setBackgroundColor(R.drawable.corner_radius_initial_cell);
            arrayCellFragment[r][c].setBooleanPaintedCell(true);
        }
    }

    private static void clearBoardGame(CellFragment[][] arrayCellFragment) {
        for (int r = 0; r < arrayCellFragment.length; r++) {
            for (int c = 0; c < arrayCellFragment.length; c++) {
                arrayCellFragment[r][c].setMainNumber("");
                arrayCellFragment[r][c].setBackgroundColor(R.drawable.corner_radius_unpainted_cell);
                arrayCellFragment[r][c].resetPencilCell();
                arrayCellFragment[r][c].setBooleanPaintedCell(false);
            }
        }
    }

    public static boolean completedBoardGame(CellFragment[][] arrayCellFragment) {
        boolean completedBoardGame = true;
        for (int r = 0; r < arrayCellFragment.length; r++) {
            for (int c = 0; c < arrayCellFragment.length; c++) {
                if (!arrayCellFragment[r][c].isBooleanPaintedCell()) {
                    completedBoardGame = false;
                }
            }
        }
        return completedBoardGame;
    }

    public static void setPaintedCellOptionToFalseInBoardBame(CellFragment[][] arrayCellFragment) {
        for (int r = 0; r < arrayCellFragment.length; r++) {
            for (int c = 0; c < arrayCellFragment.length; c++) {
                arrayCellFragment[r][c].setBooleanPaintedCell(true);
            }
        }
    }

    public static CellFragment[][] getArrayCell() {
        return arrayCell;
    }
}
