package com.example.chenyikui.mysocket.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by chenyikui on 15-5-3.
 */
public class ReadThread implements Runnable {
    private BufferedReader in = null;
    private Handler handler = null;

    public ReadThread(BufferedReader in, Handler handler) {
        this.in = in;
        this.handler = handler;
    }

    @Override
    public void run() {
        String line = null;
        try {
            while (true) {
                line = in.readLine();
                String[] args = line.split(",");
                if (args[0].equals("MSG")) {
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = args[1];
                    handler.sendMessage(msg);
                    Log.d(this.getClass().getName(),line);
                }
                Log.d(this.getClass().getName(),line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(this.getClass().getName(), "error");
        }
        Looper.prepare();
        Looper.loop();
    }
}
