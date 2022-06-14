package com.koey.view.ui;

import com.koey.fx.KFX;
import com.koey.fx.entity.UI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Button extends UI {

    private Color commColor = Color.rgb(	0 ,139 ,139);

    private Color clickColor =  Color.rgb(0 ,255 ,255);

    private Color currentColor = commColor;

    private String text;
    private double textWidth;
    private double textHeight;

    private boolean isMoved = false;

   // private boolean isClicked = true;

    public Button(double lx, double ly, double width, double height,String text) {
        super(lx, ly, width, height);
        this.text = text;

    }

    private  double calculateTextPixelWidth(String text, Font font) {
        Text theText = new Text(text);
        theText.setFont(font);
        return theText.getLayoutBounds().getWidth(); //theText.getBoundsInLocal().getWidth();
    }

    private  double calculateTextPixelHeight(String text, Font font) {
        Text theText = new Text(text);
        theText.setFont(font);
        return theText.getLayoutBounds().getHeight(); //theText.getBoundsInLocal().getWidth();
    }


    private int index ;

    @Override
    public boolean onEvent(MouseEvent event) {
        switch (event.getEventType().toString()){
            case "MOUSE_MOVED":
                if(isIn(event.getX(),event.getY())){
                    if(!isMoved){
                        isMoved = true;


                    }
                }else {
                    if(isMoved) {
                      //  isMoved = false;
                      //  this.setLy(this.getLy()+10);
                    }
                }
                break;
            case "MOUSE_PRESSED":
                if(isIn(event.getX(),event.getY())){
                    isClicked = true;
                    this.currentColor = clickColor;
                    return true;
                }
            case "MOUSE_DRAGGED":

                break;
            case "MOUSE_RELEASED":
                if(isClicked) {
                    isClicked = false;
                    this.currentColor = commColor;
                }
                break;
        }
        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
         gc.save();
         gc.setFill(this.currentColor);
         gc.fillRect(this.getPosiLX(),this.getPosiLY(),this.width,this.height);
         if(this.textWidth==0){
             this.textWidth = calculateTextPixelWidth(text,gc.getFont());
             this.textHeight = calculateTextPixelHeight(text,gc.getFont());
         }
         gc.strokeText(text,this.getPosiLX()+this.width/2-this.textWidth/2,this.getPosiLY()+this.height/2+this.textHeight/4);

         gc.restore();
    }

    @Override
    public void onUpdate() {
        if(isMoved) {
            if(index<10){
                index++;
                this.setLy(this.getLy()-3);
            }else {
                KFX.removeEntity(this);
            }
        }
    }
}
