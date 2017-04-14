package com.xbuyt.sudoku.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xbuyt.sudoku.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nian on 17/4/14.
 */

//之所以不用ActivityCompat是因为这里不使用ActionBar(5.0以下为兼容方案ToolBar) 使用Activity来得方便
public class SplashActivity extends Activity {
    // 设置为final变量，不允许任何地方修改
    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        //使用计时器对首屏计时，时间到后跳转游戏界面
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
