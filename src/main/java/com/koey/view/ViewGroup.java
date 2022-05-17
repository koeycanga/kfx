package com.koey.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ViewGroup extends View{


    private List<View> children = new ArrayList<>();

    private int index = -1;


    public ViewGroup(double lx,double ly,double width,double height,ViewGroup viewGroup){
        super(lx,ly,width,height,viewGroup);

    }

    @Override
    public boolean onEvent(MouseEvent event) {

        return false;
    }

    @Override
    public void onDraw(GraphicsContext gc) {
        for(int i=children.size()-1;i>=0;i--){
            children.get(i).onDraw(gc);
        }
    }

    @Override
    public void onUpdate() {

    }

    public boolean disPatchEvent(MouseEvent event){
        switch (event.getEventType().toString()){
            case "MOUSE_MOVED":
            case "MOUSE_DRAGGED":
            case "MOUSE_PRESSED":
                for(int i=children.size()-1;i>=0;i--){
                    Object obj = children.get(i);
                    if(obj instanceof ViewGroup){
                        ViewGroup vg = (ViewGroup) obj;
                        if(vg.disPatchEvent(event)){
                            index = i;
                            return true;
                        }
                    }else if(obj instanceof View){
                        View view = (View) obj;
                        if(view.onEvent(event)){
                            index = i;
                            return true;
                        }
                    }
                }
                break;
            case "MOUSE_RELEASED":
                if(index>=0){
                    Object obj = children.get(index);
                    index = -1;
                    if(obj instanceof ViewGroup) {
                        ViewGroup vg = (ViewGroup) obj;
                        return vg.disPatchEvent(event);
                    }else if(obj instanceof View) {
                        View view = (View) obj;
                        return view.onEvent(event);
                    }
                }
                break;
        }
        return false;
    }


    public List<View> getChildren() {
        return children;
    }


}
