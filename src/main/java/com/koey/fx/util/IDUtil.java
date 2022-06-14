package com.koey.fx.util;

public class IDUtil {

    private static long id = 0;

    public static synchronized long getNextId(){
        return ++id;
    }

}
