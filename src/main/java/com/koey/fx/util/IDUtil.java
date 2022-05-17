package com.koey.fx.util;

public class IDUtil {

    private static int id = 0;

    public static synchronized int getNextId(){
        return ++id;
    }

}
