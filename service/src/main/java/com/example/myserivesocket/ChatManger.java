package com.example.myserivesocket;

import java.util.Vector;

/**
 * Created by haiyuan1995 on 2016/10/10.
 */
public class ChatManger {//此类为管理类，因为只能有一个管理类，所以设计为单例模式如下


    private ChatManger() {
    }

    private static final ChatManger cm = new ChatManger();

    public static ChatManger getCm() {
        return cm;
    }
    //为单例模式，只允许系统有一个类的实例对象


    Vector<ChatSocket> vector = new Vector<>();//    一个ChatSocket类型的记录器，

    //  向记录器中添加ChatSocket
    public void add(ChatSocket socket) {
        vector.add(socket);
    }

    //    此方法为一个ChatSocket向多个ChatSocket发消息
    public void publish(ChatSocket cs, String context) {
        for (int i = 0; i < vector.size(); i++) {
            ChatSocket self = vector.get(i);//获取当前发消息的ChatSocket的位置
            if (!cs.equals(self)) {//向除自己以外的ChatSocket发送消息
                self.out(context);
            }
        }
    }
}
