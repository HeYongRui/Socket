package com.example.myserivesocket;

public class MyServiceSocket {
    public static void main(String args[]) {
        new ServerListener().start();//主方法中开启新的线程
    }
}
