package com.example.chenyikui.mysocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chenyikui.mysocket.utils.ReadThread;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class SMSAcitvity extends Activity {
    private PrintWriter out;
    private String username;
    private ListView listView;
    private EditText editText;
    private TextView textView;
    private ArrayList<String> arr;
    private ArrayAdapter<String> adapter;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = (String) msg.obj;
            Log.d(SMSAcitvity.class.getName() + "-", str);
            if (msg.what == 0) {
                arr.add(str);
            } else {
                arr.add(str);
            }
            editText.setText("");
            adapter.notifyDataSetChanged();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsacitvity);
        listView = (ListView) findViewById(R.id.list1);
        textView = (TextView) findViewById(R.id.title);
        editText = (EditText) findViewById(R.id.edit);

        arr = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.array_item, arr);
        listView.setAdapter(adapter);
        Intent intent = this.getIntent();
        username = intent.getStringExtra("username");
        textView.setText(username);
        Log.d(this.getClass().getName(), "Activity Create");
        BufferedReader in = MyApplication.clientThread.getIn();
        new Thread(new ReadThread(in,mHandler)).start();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
//                MyApplication.clientThread.setmHandler(mHandler);
                Message msg = new Message();
                String str = editText.getText().toString();
                if (str.equals("")) return;
                msg.obj = str;
                msg.what = 0;
                mHandler.sendMessage(msg);
                out = MyApplication.clientThread.getOut();
                out.println("TALKTO," + username + "," + str);
                break;
            case R.id.back:
                this.finish();
                break;
        }
    }

}
