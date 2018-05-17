package com.abbott.aspect;

public class BrokerRight implements Right{
    @Override
    public boolean hasRight(String page) {

        if(page.equals("customer")){
            return true;
        }
        return false;
    }
}
