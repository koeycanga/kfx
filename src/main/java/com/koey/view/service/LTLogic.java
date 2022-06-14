package com.koey.view.service;

import java.util.Random;

public class LTLogic {

    private static TouZi[] touZis = new TouZi[7];

    public static void handleTouZi(){
        for(int i=0;i<touZis.length;i++){
            touZis[i] = new TouZi(i+1);
        }
        Random random = new Random();
        for(int i=0;i<100;i++){
            int left = random.nextInt(touZis.length);
            int right = random.nextInt(touZis.length);
            if(left!=right){
                TouZi temp = touZis[left];
                touZis[left] = touZis[right];
                touZis[right] = temp;
            }
        }

    }


    public TouZi[] getTouZis() {
        return touZis;
    }

}
