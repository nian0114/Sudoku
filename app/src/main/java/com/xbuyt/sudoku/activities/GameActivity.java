package com.xbuyt.sudoku.activities;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.xbuyt.sudoku.R;
import com.xbuyt.sudoku.fragments.KeyboardFragment;
import com.xbuyt.sudoku.model.Sudoku;
import com.xbuyt.sudoku.util.Constants;

public class GameActivity extends AppCompatActivity {

    Sudoku sudoku = new Sudoku();

    private final int RESULT_CODE_BTDEVICE = 0;

    private static ConnectionManager mConnectionManager;
    private final static int MSG_SENT_DATA = 0;
    private final static int MSG_RECEIVE_DATA = 1;

    private static TextView textLevel;
    public static Button penPencilButton;
    public static int penPencilOption = Constants.PEN_MODE;
    public static Chronometer chronometer;
    public static String[][] network_Sudoku = new String[9][9];
    public static boolean sent = false;
    public static boolean isHost = false;
    private Context context;

    private long lastStopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game);

        //Android已经逐渐废弃ActionBar改用ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textLevel = (TextView) findViewById(R.id.activity_board_game_level_text);
        penPencilButton = (Button) findViewById(R.id.activity_board_game_pen_pencil_button);
        chronometer = (Chronometer) findViewById(R.id.activity_board_game___chronometer);

        context = getApplicationContext();

        //申请蓝牙，如果没开，提示用户开
        BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!BTAdapter.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
            finish();
            return;
        }

        int hasPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
            finish();
            return;
        }

        mConnectionManager = new ConnectionManager(mConnectionListener);
        mConnectionManager.startListen();

        if (BTAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            i.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
            startActivity(i);
        }

        if (Sudoku.mode == 3) {
            textLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isHost = true;
                    if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_CONNECTED) {
                        mConnectionManager.disconnect();

                    } else if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_CONNECTING) {
                        mConnectionManager.disconnect();

                    } else if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_IDLE) {
                        Intent i = new Intent(GameActivity.this, BluetoothMatchActivity.class);
                        startActivityForResult(i, RESULT_CODE_BTDEVICE);
                    }

                }
            });
        }

        if (Sudoku.mode == 2) {
            textLevel.setVisibility(View.INVISIBLE);
        }
        penPencilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (penPencilOption == Constants.PEN_MODE) {
                    penPencilOption = Constants.PENCIL_MODE;
                    penPencilButton.setText(getResources().getString(R.string.activity_board_game_pencil_text));
                } else if (penPencilOption == Constants.PENCIL_MODE) {
                    penPencilOption = Constants.PEN_MODE;
                    penPencilButton.setText(getResources().getString(R.string.activity_board_game_pen_text));
                }
            }
        });
        if (Sudoku.mode == 1 || Sudoku.mode == 2)
            sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);

    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
        lastStopTime = SystemClock.elapsedRealtime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lastStopTime == 0) {    // On first start
            chronometer.setBase(SystemClock.elapsedRealtime());
        } else {                    // On resume after pause
            long intervalOnPause = (SystemClock.elapsedRealtime() - lastStopTime);
            chronometer.setBase(chronometer.getBase() + intervalOnPause);
        }
        chronometer.start();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (Sudoku.mode == 1)//仅练习模式支持选择难度
            getMenuInflater().inflate(R.menu.level_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_level___easy) {
            sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);
        } else if (id == R.id.menu_level___medium) {
            sudoku.resetGame(context, Constants.MEDIUM_LEVEL_CELL_NUMBER, Constants.MEDIUM_LEVEL_TEXT);
        } else if (id == R.id.menu_level___hard) {
            sudoku.resetGame(context, Constants.HARD_LEVEL_CELL_NUMBER, Constants.HARD_LEVEL_TEXT);
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setTextLevel(String text) {
        textLevel.setText(text);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case MSG_SENT_DATA: {

                    byte[] data = (byte[]) msg.obj;
                    boolean suc = msg.arg1 == 1;
                    if (data != null && suc) {

                        ChatMessage chatMsg = new ChatMessage();
                        chatMsg.messageSender = ChatMessage.MSG_SENDER_ME;
                        chatMsg.messageContent = new String(data);

                        if (!network_Sudoku[0][0].equals("")) {
                            sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);
                        }
                    }
                }
                break;

                case MSG_RECEIVE_DATA: {

                    byte[] data = (byte[]) msg.obj;
                    if (data != null) {

                        ChatMessage chatMsg = new ChatMessage();
                        chatMsg.messageSender = ChatMessage.MSG_SENDER_OTHERS;
                        chatMsg.messageContent = new String(data);

                        Log.d("TAG", chatMsg.messageContent);

                        if (chatMsg.messageContent.equals("win")) {
                            GameActivity.chronometer.stop();
                            GameActivity.penPencilButton.setText(R.string.activity_board_game_pen_text);
                            GameActivity.penPencilButton.setEnabled(false);
                            KeyboardFragment.resetKeyboard();
                            KeyboardFragment.setEnabledKeyboard(false);
                            Toast.makeText(context, "你输了", Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                network_Sudoku = convertToArray(chatMsg.messageContent, 9, 9);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (!network_Sudoku[0][0].equals("")) {
                                sudoku.resetGame(context, Constants.EASY_LEVEL_CELL_NUMBER, Constants.EASY_LEVEL_TEXT);
                            }
                        }
                    }

                }
                case 3: {
                    updateUI();
                    Log.d("Status", "current BT ConnectState=" + mConnectionManager.getState(mConnectionManager.getCurrentConnectState())
                            + " ListenState=" + mConnectionManager.getState(mConnectionManager.getCurrentListenState()));
                }
                break;
            }

        }
    };


    private ConnectionManager.ConnectionListener mConnectionListener = new ConnectionManager.ConnectionListener() {

        @Override
        public void onConnectStateChange(int oldState, int State) {
            mHandler.obtainMessage(3).sendToTarget();
        }

        @Override
        public void onListenStateChange(int oldState, int State) {
            mHandler.obtainMessage(3).sendToTarget();
        }

        @Override
        public void onSendData(boolean suc, byte[] data) {
            mHandler.obtainMessage(MSG_SENT_DATA, suc ? 1 : 0, 0, data).sendToTarget();
        }

        @Override
        public void onReadData(byte[] data) {
            mHandler.obtainMessage(MSG_RECEIVE_DATA, data).sendToTarget();

        }

    };

    public static void sendMessage(String content) {
        if (content != null) {
            content = content.trim();
            if (content.length() > 0) {
                Log.d("TAG", content);
                mConnectionManager.sendData(content.getBytes());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAG", "onActivityResult, requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == RESULT_CODE_BTDEVICE && resultCode == RESULT_OK) {
            String deviceAddr = data.getStringExtra("DEVICE_ADDR");
            mConnectionManager.connect(deviceAddr);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(MSG_SENT_DATA);
        mHandler.removeMessages(MSG_RECEIVE_DATA);

        if (mConnectionManager != null) {
            mConnectionManager.disconnect();
            mConnectionManager.stopListen();
        }
    }


    private void updateUI() {
        if (mConnectionManager == null) {
            return;
        }

        if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_CONNECTED) {
            textLevel.setText(R.string.disconnect);
            if (!sent && isHost) {
                network_Sudoku = Constants.BOARD_GAME[0][0];

                sendMessage(convertToString(network_Sudoku, 9, 9));
                sent = true;
            }
        } else if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_CONNECTING) {
            textLevel.setText(R.string.cancel);
        } else if (mConnectionManager.getCurrentConnectState() == ConnectionManager.CONNECT_STATE_IDLE) {
            textLevel.setText(R.string.connect);
        }
    }

    public String convertToString(String[][] array, int row, int col) {
        String str = "";
        String tempStr = null;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tempStr = array[i][j];
                str = str + tempStr + ",";
            }
        }
        return str;
    }

    public String[][] convertToArray(String str, int row, int col) {
        String[][] arrayConvert = new String[row][col];
        int count = 0;
        String[] strArray = str.split(",");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arrayConvert[i][j] = strArray[count];
                ++count;
            }
        }
        return arrayConvert;
    }

}