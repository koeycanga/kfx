package com.koey.fx.entity;

import com.koey.view.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class Sprite extends View {


    public Sprite(double lx, double ly, double width, double height) {
        super(lx, ly, width, height);
    }
}
