package com.xbuyt.sudoku.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xbuyt.sudoku.R;

public class Animations {

    public static void animationHeartFull(Context context, ImageView image) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_heart_full);//载入命全满的动画
        animation.setFillAfter(true);//在移动动画结束后即淡出，不会回到原来的位置在淡出
        image.startAnimation(animation);//开始了动画
    }

    public static void animationHeartEmpty(Context context, ImageView image) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_heart_empty);
        animation.setFillAfter(true);
        image.startAnimation(animation);
    }

    public static void annimationCorrectCell(Context context, RelativeLayout layout) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_correct_cell);
        animation.setFillAfter(true);
        layout.startAnimation(animation);
    }

    public static void animationIncorrectCell(Context context, RelativeLayout layout) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_incorrect_cell);
        animation.setFillAfter(false);
        layout.startAnimation(animation);
    }
}