package com.xbuyt.sudoku.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xbuyt.sudoku.R;

public class MainActivity extends Activity {

    private Button mOnlineMode, mOfflineMode, mFAQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOnlineMode = (Button) findViewById(R.id.online_mode);
        mOfflineMode = (Button) findViewById(R.id.offline_mode);
        mFAQ = (Button) findViewById(R.id.btn_faq);

        mOnlineMode.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Log.d("Sudoku", "Online");
                                           }
                                       }

        );

        mOfflineMode.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Log.d("Sudoku", "Offline");
                                            }
                                        }

        );

        mFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sudoku", "mFaq");
            }
        });
    }
}
