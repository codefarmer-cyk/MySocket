package com.example.chenyikui.mysocket.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.chenyikui.mysocket.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//public class HeartBeatService extends Service {
//    private PrintWriter out = null;
//    private boolean flag = true;
//    public HeartBeatService() {
//        this.out = MyApplication.getOut();
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        System.out.println("Service is Created");
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    MyApplication.mSocket=new Socket("10.42.0.1",4444);
//                    MyApplication.init();
//                    MyApplication.name="123";
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                MyApplication.out.println("LOGIN,123");
//                while(flag){
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    out.println(".");
//                }
//            }
//        }.start();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        this.flag = false;
//        System.out.println(this.getClass().getName()+" Destroyed");
//    }
//}
