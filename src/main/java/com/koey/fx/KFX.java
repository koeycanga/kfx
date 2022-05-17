package com.koey.fx;

import com.koey.fx.entity.BG;
import com.koey.fx.entity.Sprite;
import com.koey.fx.entity.UI;
import com.koey.fx.entity.UIGroup;
import com.koey.view.View;
import com.koey.view.ViewGroup;

public class KFX {

    private static ViewGroup rootViewGroup;

    private static ViewGroup bgMaps;

    private static ViewGroup sprites;

    private static ViewGroup uis;

    public static void init(int width,int height){
        rootViewGroup = new ViewGroup(0,0,width,height,null);

        bgMaps = new ViewGroup(0,0,width,height,rootViewGroup);

        sprites = new ViewGroup(0,0,width,height,rootViewGroup);

        uis = new ViewGroup(0,0,width,height,rootViewGroup);
    }

    public static synchronized void addEntity(View view){

        addEntity(view,null);

    }

    public static synchronized void addEntity(View view, ViewGroup viewGroup){

        if(viewGroup==null){

            if(view instanceof Sprite){
                view.setParentViewGroup(sprites);
            }

            if(view instanceof UI || view instanceof UIGroup){
                view.setParentViewGroup(uis);
            }

            if(view instanceof BG){
                view.setParentViewGroup(bgMaps);
            }

        }else{
            viewGroup.getChildren().add(view);
        }

    }

    public static ViewGroup getRootViewGroup() {
        return rootViewGroup;
    }

}
