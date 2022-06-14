package com.koey.view.service;

import java.util.Random;

public class TouZi {

    // 骆驼类型  1 红  2 绿  3 蓝  4 紫  5 黄  6 白  7 黑
    private int type ;

    private int ds;


    public TouZi(int type){
        this.type = type;
        Random random = new Random();
        if(type<6){
            this.ds = 1+random.nextInt(6);
        }else{
            this.ds = 1+random.nextInt(3);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }
}
