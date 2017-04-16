package com.xbuyt.sudoku.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.util.Animations;

public class LifeFragment extends Fragment {

    ImageView heart1;
    ImageView heart2;
    ImageView heart3;
    ImageView heart4;
    ImageView heart5;

    static ImageView arrayIcon[] = new ImageView[5];

    public LifeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_life, container, false);

        heart1 = (ImageView) view.findViewById(R.id.fragment_life_icon_1);
        heart2 = (ImageView) view.findViewById(R.id.fragment_life_icon_2);
        heart3 = (ImageView) view.findViewById(R.id.fragment_life_icon_3);
        heart4 = (ImageView) view.findViewById(R.id.fragment_life_icon_4);
        heart5 = (ImageView) view.findViewById(R.id.fragment_life_icon_5);

        arrayIcon[0] = heart1;
        arrayIcon[1] = heart2;
        arrayIcon[2] = heart3;
        arrayIcon[3] = heart4;
        arrayIcon[4] = heart5;

        return view;
    }

    public static void restartIcons(Context context) {
        for (ImageView icon : arrayIcon) {
            Animations.animationHeartFull(context, icon);
        }
    }
}