package com.koey.view.ui;

import com.koey.fx.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Bullt extends Sprite {



    public Bullt(double lx, double ly, double width, double height) {
        super(lx, ly, width, height);

        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.isAlive = false;
        }).start();
    }

    @Override
    public boolean onEvent(MouseEvent event) {
        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        gc.save();
        gc.setFill(Color.ORANGE);

         gc.fillRect(lx,ly,5,5);
         gc.restore();
    }

    @Override
    public void onUpdate() {

    }
}
