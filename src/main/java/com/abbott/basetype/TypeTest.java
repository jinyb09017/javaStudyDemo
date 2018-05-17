package com.abbott.basetype;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

public class TypeTest {
    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }

    @Timer
    public void checkType(){
        String value = "12.56";
        Float fValue = Float.valueOf(value);

        Integer a = 100;
        if(fValue > a){
            System.out.println("good");
        }

        System.out.println(fValue);

    }
}
