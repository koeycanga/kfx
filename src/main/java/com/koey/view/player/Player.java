package com.koey.view.player;

import com.koey.fx.entity.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Player extends Sprite {

    private String name;

    private double clickX;
    private double clickY;

  //  private boolean isClicked = true;


    public Player(double lx, double ly, double width, double height){
        super(lx,ly,width,height);
    }


    @Override
    public boolean onEvent(MouseEvent event) {
        switch (event.getEventType().toString()){
            case "MOUSE_PRESSED":
                 clickX = event.getX();
                 clickY = event.getY();
                if(isIn(clickX,clickY)){
                    isClicked = true;
                    return true;
                }
            case "MOUSE_DRAGGED":
                if(isClicked){
                    double nowX = event.getX();
                    double nowY = event.getY();
                    double dx = nowX - clickX;
                    double dy = nowY - clickY;
                    this.setLx(this.getLx()+dx);
                    this.setLy(this.getLy()+dy);
                    this.setPosiLX(this.getPosiLX()+dx);
                    this.setPosiLY(this.getPosiLY()+dy);
                    clickX = nowX;
                    clickY = nowY;
                }
                break;
            case "MOUSE_RELEASED":
                if(isClicked) {
                    isClicked = false;
                    System.out.println("player " + name + " say hello");
                }
                break;
        }
        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        if(!isAlive){
            return;
        }
        gc.save();
        if(name.equals("p1")){
            gc.setFill(Color.GREEN);
        }
        if(name.equals("p2")){
            gc.setFill(Color.BLACK);
        }
        if(name.equals("p3")){
            gc.setFill(Color.OLDLACE);
        }

        gc.fillOval(posiLX,posiLY,width,height);

        gc.restore();
    }

    @Override
    public void onUpdate() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
