package com.koey.view;

import com.koey.fx.util.IDUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.Collections;
import java.util.Comparator;

public abstract class View {

     // 相对于父组件的左上角x坐标
     protected double lx;
     // 相对于父组件的左上角y坐标
     protected double ly;
     // 组件宽度
     protected double width;
     // 组件高度
     protected double height;
     // 相对于父组件的右下角X坐标
     protected double rx;
     // 相对于父组件的右下角y坐标
     protected double ry;

     // 左上角绝对坐标X
     protected double posiLX;
     // 左上角绝对坐标Y
     protected double posiLY;
    // 右上角绝对坐标X
    protected double posiRX;
    // 右上角绝对坐标Y
    protected double posiRY;

    protected ViewGroup parentViewGroup;

    protected boolean isAlive = true;

    protected int id;

    protected String name;

    protected String type;


    public View(double lx,double ly,double width,double height,ViewGroup viewGroup){
        if(viewGroup==null){
            this.id = -1;
        }else {
            this.id = IDUtil.getNextId();
        }
        this.lx = lx;
        this.ly = ly;
        this.width = width;
        this.height = height;
        this.rx = lx+width;
        this.ry = ly+height;
        this.posiLX = lx;
        this.posiLY = ly;
        this.posiRX = lx+width;
        this.posiRY = ly+height;
        this.parentViewGroup = viewGroup;
        this.handleViewGroup(viewGroup);
    }

    public View(double lx,double ly,double width,double height){
        this.id = IDUtil.getNextId();
        this.lx = lx;
        this.ly = ly;
        this.width = width;
        this.height = height;
        this.rx = lx+width;
        this.ry = ly+height;
        this.posiLX = lx;
        this.posiLY = ly;
        this.posiRX = lx+width;
        this.posiRY = ly+height;
    }

    protected void handleViewGroup(ViewGroup viewGroup){
        if(viewGroup!=null){
            this.posiLX = this.posiLX+getViewGroupPosiLX(viewGroup);
            this.posiLY = this.posiLY+getViewGroupPosiLY(viewGroup);
            this.posiRX = this.posiLX+width;
            this.posiRY = this.posiLY+height;
            viewGroup.getChildren().add(this);
        }
    }



    protected  double getViewGroupPosiLX(ViewGroup viewGroup){
         double result = 0;
         if(viewGroup!=null){
             result = viewGroup.getPosiLX();
             return result+getViewGroupPosiLX(viewGroup.getParentViewGroup());
         }
         return result;
    }

    protected  double getViewGroupPosiLY(ViewGroup viewGroup){
        double result = 0;
        if(viewGroup!=null){
            result = viewGroup.getPosiLY();
            return result+getViewGroupPosiLY(viewGroup.getParentViewGroup());
        }
        return result;
    }


    public boolean isIn(double clickX,double clickY){
         if(clickX>=posiLX&&clickX<=posiRX&&clickY>=posiLY&&clickY<=posiRY){
             return true;
         }
         return false;
     }

     public abstract boolean onEvent(MouseEvent event);
     public abstract void onDraw(GraphicsContext gc);

     public abstract void onUpdate();

    public ViewGroup getParentViewGroup() {
        return parentViewGroup;
    }

    public void setParentViewGroup(ViewGroup parentViewGroup) {
        this.parentViewGroup = parentViewGroup;
        this.handleViewGroup(parentViewGroup);
    }

    public double getPosiLX() {
        return posiLX;
    }

    public void setPosiLX(double posiLX) {
        this.posiLX = posiLX;
        this.posiRX = posiLX+width;
    }

    public double getPosiLY() {
        return posiLY;
    }

    public void setPosiLY(double posiLY) {
        this.posiLY = posiLY;
        this.posiRY = posiRY+height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getPosiRX() {
        return posiRX;
    }

    public void setPosiRX(double posiRX) {
        this.posiRX = posiRX;
    }

    public double getPosiRY() {
        return posiRY;
    }

    public void setPosiRY(double posiRY) {
        this.posiRY = posiRY;
    }

    public double getLx() {
        return lx;
    }

    public void setLx(double lx) {
        this.lx = lx;
        this.rx = lx+width;
    }

    public double getLy() {
        return ly;
    }

    public void setLy(double ly) {
        this.ly = ly;
        this.ry = ly+height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
