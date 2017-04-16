package com.xbuyt.sudoku.activities;

import android.content.ActivityNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.xbuyt.sudoku.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Nian on 17/4/14.
 */

public class LicenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        setTitle(R.string.user_license);

        //Android已经逐渐废弃ActionBar改用ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initState();

        try {
            WebView view = (WebView) findViewById(R.id.about_webview);
            view.loadUrl(getLicense());
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showErrorAndFinish();
        }
    }

    private void showErrorAndFinish() {
        Toast.makeText(this, R.string.settings_license_activity_unavailable, Toast.LENGTH_LONG)
                .show();
        finish();
    }

    private String getLicense() {

        try {
            InputStream stream = getAssets().open("license.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF8"));
            String line;
            StringBuilder buffer = new StringBuilder();
            /*
             * Makes sure that the character set will be loaded correctly.
             */
            buffer.append("data:text/html;charset=UTF8;,");
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException ex) {
            return ex.toString();
        }
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
