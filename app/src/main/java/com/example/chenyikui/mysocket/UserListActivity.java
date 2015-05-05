package com.example.chenyikui.mysocket;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.PrintWriter;


public class UserListActivity extends ListActivity {
    private String[] userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        userList = MyApplication.userList;
        this.userList=this.getIntent().getStringArrayExtra("USERLIST");
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String username = userList[position];
        Toast.makeText(UserListActivity.this,username,Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(UserListActivity.this,SMSAcitvity.class);
        intent.putExtra("username",username);
        PrintWriter out = MyApplication.clientThread.getOut();
        out.println("TALKTO,"+username+",Hello");
        startActivity(intent);
    }
}
