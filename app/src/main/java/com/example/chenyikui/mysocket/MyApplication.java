package com.example.chenyikui.mysocket;

import android.app.Application;

import com.example.chenyikui.mysocket.utils.SocketClientThread;

/**
 * Created by chenyikui on 15-4-27.
 */
public class MyApplication extends Application {
    public static SocketClientThread clientThread=null;
    public static void  init(){
        clientThread = new SocketClientThread();
    }
}
