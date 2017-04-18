package com.xbuyt.sudoku.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.activities.GameActivity;
import com.xbuyt.sudoku.model.Sudoku;
import com.xbuyt.sudoku.util.Animations;
import com.xbuyt.sudoku.util.Constants;

public class CellFragment extends Fragment {
    TextView pencil1;
    TextView pencil2;
    TextView pencil3;
    TextView pencil4;
    TextView pencil5;
    TextView pencil6;
    TextView pencil7;
    TextView pencil8;
    TextView pencil9;
    TextView mainNumber;
    RelativeLayout layout;

    private TextView arrayPencil[] = new TextView[9];
    private boolean booleanPaintedCell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cell, container, false);

        mainNumber = (TextView) view.findViewById(R.id.frament_cell___main_number);
        layout = (RelativeLayout) view.findViewById(R.id.fragment_cell___layout);

        pencil1 = (TextView) view.findViewById(R.id.frament_cell___pencil_1);
        pencil2 = (TextView) view.findViewById(R.id.frament_cell___pencil_2);
        pencil3 = (TextView) view.findViewById(R.id.frament_cell___pencil_3);
        pencil4 = (TextView) view.findViewById(R.id.frament_cell___pencil_4);
        pencil5 = (TextView) view.findViewById(R.id.frament_cell___pencil_5);
        pencil6 = (TextView) view.findViewById(R.id.frament_cell___pencil_6);
        pencil7 = (TextView) view.findViewById(R.id.frament_cell___pencil_7);
        pencil8 = (TextView) view.findViewById(R.id.frament_cell___pencil_8);
        pencil9 = (TextView) view.findViewById(R.id.frament_cell___pencil_9);

        arrayPencil[0] = pencil1;
        arrayPencil[1] = pencil2;
        arrayPencil[2] = pencil3;
        arrayPencil[3] = pencil4;
        arrayPencil[4] = pencil5;
        arrayPencil[5] = pencil6;
        arrayPencil[6] = pencil7;
        arrayPencil[7] = pencil8;
        arrayPencil[8] = pencil9;

        return view;
    }

    public void cellClicked(final Sudoku sudoku, final int r, final int c) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!KeyboardFragment.currentNumber.equals("") && !booleanPaintedCell) {//判断当前是否为可填入区域，如果为空且判断为未知(即不是默认给出的数)就可以进行下面的操作(即草稿/答题模式)
                    if (GameActivity.penPencilOption == Constants.PEN_MODE) {
                        penMove(sudoku, view.getContext(), r, c);//填入数字，正确继续，错误给出提示，命-1
                    } else if (GameActivity.penPencilOption == Constants.PENCIL_MODE) {
                        pencilMove();//填入小数字，备用
                    }
                }
            }
        });
    }

    private void penMove(Sudoku sudoku, Context context, int r, int c) {
        resetPencilCell();//如果先前存在草稿模式的数据，则设置为不可见
        if (BoardGameFragment.completedBoardGame(BoardGameFragment.getArrayCell())) {
            sudoku.winGame(context);//如果全部被填满，且都是已绘区域就标记为赢得了比赛
        }

        for (int i = 0; i < Sudoku.list.size(); ) {
            if (Integer.parseInt(KeyboardFragment.currentNumber) == Sudoku.list.get(i)[r][c]) {
                setBooleanPaintedCell(true);//设置这块为已填入区域
                Animations.annimationCorrectCell(context, layout);//来个动画，表示正确
                mainNumber.setText(KeyboardFragment.currentNumber);//设置那个地方为填入的数字
                setBackgroundColor(R.drawable.corner_radius_correct_cell);//设置背景颜色为和已知区域一样的颜色
                Sudoku.list_work.clear();
                Sudoku.list_work.addAll(Sudoku.list);
                i++;
            } else {
                Sudoku.list.remove(i);
                i = 0;
            }
        }

        if (Sudoku.list.size() == 0) {
            Animations.animationIncorrectCell(context, layout);//来个动画，表示错误
            Animations.animationHeartEmpty(context, LifeFragment.arrayIcon[Sudoku.lifeCounter]);//来个动画，表示命-1
            setBackgroundColor(R.drawable.corner_radius_incorrect_cell);//设置颜色为表示错误的颜色
            if (Sudoku.lifeCounter == 0) {
                sudoku.loseGame(context);//判断是否有命可以继续玩下去，没命了就提示失败
            } else {
                Sudoku.lifeCounter = Sudoku.lifeCounter - 1;//还有命就继续玩，但是命-1
                Sudoku.list.clear();
                if (Sudoku.list_work.size() == 0) {
                    Sudoku.getBoardGameResult();
                } else {
                    Sudoku.list.addAll(Sudoku.list_work);
                }
            }
        }
    }

    private void pencilMove() {
        for (int i = 0; i < arrayPencil.length; i++) {
            if (KeyboardFragment.currentNumber.equals(Integer.toString(i + 1))) {//获取用户当前选择的数
                if (arrayPencil[i].getVisibility() == View.VISIBLE) {//如果现在已经显示了这个数
                    arrayPencil[i].setVisibility(View.INVISIBLE);//让这个数消失
                } else if (arrayPencil[i].getVisibility() == View.INVISIBLE) {//如果现在还没显示这个数
                    arrayPencil[i].setVisibility(View.VISIBLE);//那么让这个数显示出来
                }
            }
        }
    }

    public void resetPencilCell() {
        for (TextView pencil : arrayPencil) {
            pencil.setVisibility(View.INVISIBLE);//设置单元格的9个数不可见
        }
    }

    public void setBackgroundColor(int drawable) {
        layout.setBackgroundResource(drawable);
    }

    public void setMainNumber(String number) {
        mainNumber.setText(number);
    }

    public void setBooleanPaintedCell(boolean booleanPaintedCell) {
        this.booleanPaintedCell = booleanPaintedCell;
    }

    public boolean isBooleanPaintedCell() {
        return booleanPaintedCell;
    }
}