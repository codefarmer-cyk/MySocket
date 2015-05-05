package com.example.chenyikui.mysocket;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private EditText mUsernameView;
    private Handler mHandler = mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
            String str = ((String) msg.obj).replace(" ", "");
            String[] userList = str.substring(1, str.length() - 1).split(",");
            intent.putExtra("USERLIST", userList);
            startActivity(intent);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameView = (EditText) findViewById(R.id.username);
    }

    public boolean isUsernameValid(String username) {
        if (username.equals("") || username == null || username.length() < 5 || username.length() > 10) {
            return false;
        } else return true;
    }

    public void onClick(View view) {
        String username = mUsernameView.getText().toString();
//        if (!isUsernameValid(username)) {
//            Toast.makeText(this, "username invalid!", Toast.LENGTH_SHORT).show();
//            return;
//        }
        MyApplication.init();
        MyApplication.clientThread.setUsername(username);
        MyApplication.clientThread.setmHandler(mHandler);
        new Thread(MyApplication.clientThread).start();
    }
}



