package com.example.chenyikui.mysocket.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

/**
 * Created by chenyikui on 15-4-29.
 */
public class SocketClientThread implements Runnable {
    private Socket mSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String username;
    private Handler mHandler = null;

    public SocketClientThread() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public Socket getmSocket() {
        return mSocket;
    }


    @Override
    public void run() {
        String inputLine = null;
        try {
            this.mSocket = new Socket("10.42.0.1", 4444);
            this.out = new PrintWriter(mSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));

            out.println("LOGIN," + username);
            Message msg = new Message();
            while (true) {
                inputLine = in.readLine();
                String[] args = inputLine.split(",",2);
                if (inputLine.equals("BYE")) {
                    break;
                }
                switch (args[0]) {
                    case "SUCCESS":
                        out.println("GETLIST");
                        break;
                    case "USERLIST":
                        msg.obj = args[1].replace(this.username+",","");
                        mHandler.sendMessage(msg);
                        Log.d("JUMP", "TOSUERLSIT");
                        Log.d(this.getClass().getName()+"-",mHandler.toString()+","+args[1]);
                        break;
                    case "MSG":
                        msg.what=1;
                        msg.obj = args[1]+"wtf";
                        mHandler.sendMessage(msg);
                        Log.d(this.getClass().getName()+"-",mHandler.toString()+","+args[1]);
                        break;
                    case "BYE":
                        break;
                }
//                Log.d("SocketIn", "input1:" + inputLine);

            }
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Looper.prepare();
        Looper.loop();
    }

}
