package com.xbuyt.sudoku.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbuyt.sudoku.R;

public class MainActivity extends Activity {

    private Button mOnlineMode, mOfflineMode, mFAQ;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        mOnlineMode = (Button) findViewById(R.id.online_mode);
        mOfflineMode = (Button) findViewById(R.id.offline_mode);
        mFAQ = (Button) findViewById(R.id.btn_faq);

        mOnlineMode.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                                               LayoutInflater inflater = getLayoutInflater();
                                               final View loginView = inflater.inflate(R.layout.dialog_login, null);
                                               final EditText name = (EditText) loginView.findViewById(R.id.editTextUserId);
                                               final EditText key = (EditText) loginView.findViewById(R.id.text_word);

                                               builder.setView(loginView)
                                                       .setTitle(R.string.dialog_titleLogin)
                                                       .setPositiveButton(R.string.dialog_login, new DialogInterface.OnClickListener() {
                                                           @Override
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               String ID = name.getText().toString();
                                                               String password = key.getText().toString();
                                                               if (ID.equals("abc") && password.equals("123")) {
                                                                   Toast.makeText(MainActivity.this, R.string.dialog_loginSucceed, Toast.LENGTH_LONG).show();
                                                               } else if (ID.equals("abc") && !password.equals("123")) {
                                                                   Toast.makeText(MainActivity.this, R.string.dialog_loginFailed, Toast.LENGTH_LONG).show();
                                                               } else {
                                                                   Toast.makeText(MainActivity.this, R.string.dialog_loginNonExsist, Toast.LENGTH_LONG).show();
                                                               }
                                                           }
                                                       })
                                                       .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                                                           public void onClick(DialogInterface dialog, int id) {
                                                               Toast.makeText(MainActivity.this, R.string.dialog_cancel, Toast.LENGTH_SHORT).show();
                                                           }
                                                       });
                                               builder.show();
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
                Intent mainIntent = new Intent().setClass(
                        MainActivity.this, LicenseActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
