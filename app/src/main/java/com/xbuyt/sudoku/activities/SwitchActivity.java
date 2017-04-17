package com.xbuyt.sudoku.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xbuyt.sudoku.R;

/**
 * Created by Nian on 17/4/15.
 */

public class SwitchActivity extends AppCompatActivity {
    private TextView mProfileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Intent intent = getIntent();
        Boolean online_mode = intent.getBooleanExtra("online", false);

        mProfileName = ((TextView) findViewById(R.id.profile_name));

        if (online_mode) {
            SharedPreferences sp = getSharedPreferences("loginUser", 0);
            mProfileName.setText(sp.getString("username_online", getString(R.string.username_random)));
        } else {
            SharedPreferences sp = getSharedPreferences("loginUser", 0);
            mProfileName.setText(sp.getString("username_offline", getString(R.string.username_random)));
        }

    }
}
