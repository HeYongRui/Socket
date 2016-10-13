package com.heyongrui.socket;

/**
 * Created by haiyuan1995 on 2016/10/12.
 */
public class User {
    public String num1;
    public String num2;

    public User(String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public String getNum1() {

        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }
    public String tostring(){
        return num1+num2;
    }

}
