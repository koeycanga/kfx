package com.koey.view.ui;

import com.koey.fx.KFX;
import com.koey.fx.entity.UIGroup;
import com.koey.fx.view.ViewGroup;

public class ButtonGroup extends UIGroup {

    private boolean isRomve = false;

    public ButtonGroup(double lx, double ly, double width, double height) {
        super(lx, ly, width, height);
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            isRomve = true;
        }).start();
    }

    @Override
    public void onUpdate() {
         if(isRomve){
             KFX.removeEntity(this);
         }
    }
}
