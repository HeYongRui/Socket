package com.heyongrui.socket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//端口：49664
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText mEditText;
    private EditText ipdizhi;
    private Button connectButton;
    private Button sendButton;
    private Adapter adapter;
    private ListView listView;
    public List<User> melist = new ArrayList<>();
    private String shuru;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipdizhi = (EditText) findViewById(R.id.duankou);
        mEditText = (EditText) findViewById(R.id.context);
        sendButton = (Button) findViewById(R.id.send);
        connectButton = (Button) findViewById(R.id.connect);
        sendButton.setOnClickListener(this);
        connectButton.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listview);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect:
                connect();
                break;
            case R.id.send:
                send();
                if (!mEditText.getText().toString().trim().isEmpty()) {
                    melist.add(new User(shuru, null));
                    adapter = new Adapter(this, melist);
                    listView.setAdapter(adapter);
                }
                mEditText.setText("");//清空输入栏
                break;
        }
    }

    Socket clientSocket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;

    public void connect() {
        final String ip = ipdizhi.getText().toString();
        final AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    clientSocket = new Socket(ip, 12345);
                    writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
                    reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        publishProgress(line);
                    }
//                    publishProgress(lines);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                adapter = new Adapter(MainActivity.this, melist);
                melist.add(new User(null, values[0]));
                listView.setAdapter(adapter);
            }
        };
        read.execute();
    }

    public void send() {
        try {
            if (!mEditText.getText().toString().trim().isEmpty()) {
                shuru = mEditText.getText().toString();
                writer.write(mEditText.getText().toString() + "\n");//如果此处不加换行符，上面onProgressUpdate方法不执行
                //因为readLine没遇到"\n"或者"\r"会一直停留在哪里，不会继续向下执行
                writer.flush();//强制输出
            } else {
                Toast.makeText(MainActivity.this, "请输入聊天内容！", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}