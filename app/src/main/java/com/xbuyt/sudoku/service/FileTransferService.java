// Copyright 2011 Google Inc. All Rights Reserved.

package com.xbuyt.sudoku.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xbuyt.sudoku.activities.WiFiDirectActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * 处理数据传输的服务
 * A service that process each file transfer request i.e Intent by opening a
 * socket connection with the WiFi Direct Group Owner and writing the file
 */
public class FileTransferService extends IntentService {

    private static final int SOCKET_TIMEOUT = 5000;
    public static final String ACTION_SEND_FILE = "com.example.android.wifidirect.SEND_FILE";
    public static final String EXTRAS_GROUP_OWNER_ADDRESS = "go_host";
    public static final String EXTRAS_GROUP_OWNER_PORT = "go_port";
    public static final String EXTRAS_FILE_NAME = "name";
    public static boolean IS_NAME_SUCCESS = true;

    public FileTransferService(String name) {
        super(name);
    }

    public FileTransferService() {
        super("FileTransferService");
    }

    /*
     * (non-Javadoc)
     * @see android.app.IntentService#onHandleIntent(android.content.Intent)
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        Context context = getApplicationContext();
        if (intent.getAction().equals(ACTION_SEND_FILE)) {
            //主机地址
            String host = intent.getExtras().getString(EXTRAS_GROUP_OWNER_ADDRESS);
            //新建Socket
            Socket socket = new Socket();
            try {
                socket.setReuseAddress(true);
            } catch (SocketException e) {
                e.printStackTrace();
            }

            //端口号 8988
            int port = intent.getExtras().getInt(EXTRAS_GROUP_OWNER_PORT);
            //文件名
            String name = intent.getExtras().getString(EXTRAS_FILE_NAME);
            try {
                //连接主机
                socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);
                Log.d(WiFiDirectActivity.TAG, "文件发送端口开启");

                name = "nian";
                OutputStream stream = socket.getOutputStream();
                DataOutputStream da = new DataOutputStream(stream);
                da.writeUTF(name);
                da.flush();
                Log.d(WiFiDirectActivity.TAG, "文件名已发送");
                Log.d(WiFiDirectActivity.TAG, name);

            } catch (IOException e) {
                Log.e(WiFiDirectActivity.TAG, e.getMessage());
            } finally {
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                            Log.d(WiFiDirectActivity.TAG, "关闭socket");
                        } catch (IOException e) {
                            // Give up
                            e.printStackTrace();
                        }
                    }
                }

            }

        }
    }
}
