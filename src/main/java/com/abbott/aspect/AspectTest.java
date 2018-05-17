package com.abbott.aspect;

import com.abbott.annotation.CheckLogin;
import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

public class AspectTest {

    public static void main(String[] args) {


        RightManager.register(new BrokerRight());

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();




    }



    @Timer
    @CheckLogin(right = "good",page = "customer")
    public void test(){
        System.out.println("it is test");
    }

    @Timer
    @CheckLogin(right = "good",page = "good")
    public void test1(){
        System.out.println("it is test1");
    }

    @Timer
    public void test2(){
        System.out.println("it is test2");
    }
}
