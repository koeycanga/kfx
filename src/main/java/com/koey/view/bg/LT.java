package com.koey.view.bg;

import com.koey.fx.entity.BG;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LT extends BG {

    public LT(double lx, double ly, double width, double height) {
        super(lx, ly, width, height);
    }

    @Override
    public boolean onEvent(MouseEvent event) {
        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        gc.save();
        gc.setFill(Color.rgb(210, 105, 30));
        gc.fillRect(0, 0, width, height);

        for(int i=0;i<8;i++) {
            gc.setFill(Color.rgb(216, 191, 216));
            gc.fillRect(50+i*100, 100, 100, 100);
            gc.setFill(Color.BLACK);
            gc.strokeRect(50+i*100, 100, 100, 100);
            gc.fillText((i+1)+"", 95+i*100, 150);
        }

        for(int i=0;i<8;i++) {
            gc.setFill(Color.rgb(216, 191, 216));
            gc.fillRect(50+i*100, 260, 100, 100);
            gc.setFill(Color.BLACK);
            gc.strokeRect(50+i*100, 260, 100, 100);
            gc.fillText((i+9)+"", 95+i*100, 310);
        }

        gc.restore();
    }

    @Override
    public void onUpdate() {

    }
}
