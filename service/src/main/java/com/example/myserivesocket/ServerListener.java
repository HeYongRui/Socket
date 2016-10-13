package com.example.myserivesocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Created by haiyuan1995 on 2016/10/10.
 */
public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);//端口号，随意设置的
            while (true) {
                Socket accept = serverSocket.accept();
                JOptionPane.showMessageDialog(null, "The client has connected!");
                //将socket传给新的线程
                ChatSocket socket = new ChatSocket(accept);//创建一个新的ChatSocket
                socket.start();
                ChatManger.getCm().add(socket);//调用单例模式ChatManager的方法将新创建的ChatSocket添加到记录器
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
