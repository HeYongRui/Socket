package com.example.myserivesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by haiyuan1995 on 2016/10/10.
 */
public class ChatSocket extends Thread {
    Socket socket;

    public ChatSocket(Socket s) {
        this.socket = s;
    }

    public void out(String out) {
        try {
            socket.getOutputStream().write(out.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                ChatManger.getCm().publish(this, line + "\n");
                System.out.println(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
