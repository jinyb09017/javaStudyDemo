package com.abbott.aspect;

public class RightManager {
    public static Right right;

    public static void register(Right r){
        right = r;
    }


    public static Right getRight(){
        return right;
    }
}
