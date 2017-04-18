package com.xbuyt.sudoku.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.xbuyt.sudoku.R;

/**
 * Created by Nian on 17/4/15.
 */

public class SwitchActivity extends AppCompatActivity {
    private TextView mProfileName;
    private CardView mPracticeMode;
    private CardView mNormalMode;
    private CardView mOnlineMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Intent intent = getIntent();
        Boolean boolOnline = intent.getBooleanExtra("online", false);

        mProfileName = ((TextView) findViewById(R.id.profile_name));
        mPracticeMode = ((CardView) findViewById(R.id.practice));
        mNormalMode = ((CardView) findViewById(R.id.normal));
        mOnlineMode = ((CardView) findViewById(R.id.online));

        if (boolOnline) {
            SharedPreferences sp = getSharedPreferences("loginUser", 0);
            mProfileName.setText(sp.getString("username_online", getString(R.string.username_random)));
        } else {
            SharedPreferences sp = getSharedPreferences("loginUser", 0);
            mProfileName.setText(sp.getString("username_offline", getString(R.string.username_random)));
        }

        mPracticeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent().setClass(
                        SwitchActivity.this, GameActivity.class);
                startActivity(mainIntent);
            }
        });

        mNormalMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent().setClass(
                        SwitchActivity.this, GameActivity.class);
                startActivity(mainIntent);
            }
        });

        mOnlineMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent().setClass(
                        SwitchActivity.this, GameActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}