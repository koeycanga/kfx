package com.koey.view.player;

import com.koey.fx.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LuoTuo2 extends Sprite {

   
    private int type ;

    // 方向 0 正向 1负向
    private int dir ;


    public LuoTuo2(double lx, double ly,int width,int height, int type) {
        super(lx, ly, width, height);
        this.type = type;
        this.dir = 0;
        if(this.type==6||this.type==7){
            this.dir = 1;
        }
    }


    public boolean onEvent2(MouseEvent event) {
        return false;
    }

    @Override
    public boolean onEvent(MouseEvent event) {
        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        gc.save();
        extracted(gc);
        gc.fillRect(this.getPosiLX(),this.getPosiLY(),width,height);

        // test;

        double firstX = 120;
        double secondX = 190;
        double thirdX = 10;
        double firstY =30;
        double secondY =170;
        double thirdY =170;

        if(this.dir==0){
           // gc.fillOval(this.getPosiRX(),this.getPosiLY(),height,height);
            firstX = this.getPosiRX();
            secondX = firstX + 20;
            thirdX = firstX;
            firstY = this.getPosiLY();
            secondY = firstY + 15;
            thirdY = this.getPosiRY();
           gc.fillPolygon(new double[]{firstX, secondX,thirdX},
                   new double[]{firstY, secondY, thirdY}, 3);
        }
        if(this.dir==1){

        }
        gc.restore();
    }


    private void extracted(GraphicsContext gc) {
        if(type==1){
            gc.setFill(Color.RED);
        }
        if(type==2){
            gc.setFill(Color.GREEN);
        }
        if(type==3){
            gc.setFill(Color.BLUE);
        }
        if(type==4){
            gc.setFill(Color.PURPLE);
        }
        if(type==5){
            gc.setFill(Color.YELLOW);
        }
        if(type==6){
            gc.setFill(Color.WHITE);
        }
        if(type==7){
            gc.setFill(Color.BLACK);
        }
    }

    @Override
    public void onUpdate() {

    }
}
