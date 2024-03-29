package com.xbuyt.sudoku.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xbuyt.sudoku.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends Activity {

    private Button mOnlineMode, mOfflineMode, mFAQ;
    private MainActivity activity;
    private RequestQueue requestQueue;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        mOnlineMode = (Button) findViewById(R.id.online_mode);
        mOfflineMode = (Button) findViewById(R.id.offline_mode);
        mFAQ = (Button) findViewById(R.id.btn_faq);

        requestQueue = Volley.newRequestQueue(this);

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
                                                               final String ID = name.getText().toString();
                                                               final String password = key.getText().toString();

                                                               request = new StringRequest(Request.Method.POST, "https://www.ttotoo.com/games/login.php", new Response.Listener<String>() {
                                                                   @Override
                                                                   public void onResponse(String response) {
                                                                       try {
                                                                           JSONObject jsonObject = new JSONObject(response);
                                                                           if (jsonObject.names().get(0).equals("success")) {
                                                                               Intent mainIntent = new Intent().setClass(
                                                                                       MainActivity.this, SwitchActivity.class);
                                                                               mainIntent.putExtra("online", true);
                                                                               SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                                                               SharedPreferences.Editor editor = mSharedPreferences.edit();
                                                                               editor.putString("username_online", name.getText().toString());
                                                                               editor.apply();
                                                                               startActivity(mainIntent);
                                                                           } else if (jsonObject.names().get(0).equals("wrong")) {
                                                                               Toast.makeText(getApplicationContext(), getString(R.string.dialog_loginFailed), Toast.LENGTH_SHORT).show();
                                                                           } else {
                                                                               Toast.makeText(getApplicationContext(), getString(R.string.dialog_loginNonExsist), Toast.LENGTH_SHORT).show();
                                                                           }
                                                                       } catch (JSONException e) {
                                                                       }
                                                                   }
                                                               }, new Response.ErrorListener() {
                                                                   @Override
                                                                   public void onErrorResponse(VolleyError error) {
                                                                       Toast.makeText(getApplicationContext(), getString(R.string.dialog_loginFailed), Toast.LENGTH_SHORT).show();
                                                                   }
                                                               }) {
                                                                   @Override
                                                                   protected Map<String, String> getParams() throws AuthFailureError {
                                                                       HashMap<String, String> hashMap = new HashMap<String, String>();
                                                                       hashMap.put("username", ID);
                                                                       hashMap.put("password", password);
                                                                       return hashMap;
                                                                   }
                                                               };

                                                               requestQueue.add(request);
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
                                                Intent mainIntent = new Intent().setClass(
                                                        MainActivity.this, SwitchActivity.class);
                                                mainIntent.putExtra("online", false);
                                                SharedPreferences sp = getSharedPreferences("loginUser", 0);
                                                if (sp.getString("username_offline", "").equals("")) {
                                                    SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                                                    editor.putString("username_offline", getString(R.string.username_random) + new Random().nextInt(100000) + 3000000);
                                                    editor.apply();
                                                }
                                                startActivity(mainIntent);
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
