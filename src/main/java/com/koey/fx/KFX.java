package com.koey.fx;

import com.koey.fx.entity.BG;
import com.koey.fx.entity.Sprite;
import com.koey.fx.entity.UI;
import com.koey.fx.entity.UIGroup;
import com.koey.fx.view.View;
import com.koey.fx.view.ViewGroup;
import javafx.scene.canvas.GraphicsContext;


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
            view.setParentViewGroup(viewGroup);
        }

    }

    public static synchronized void removeEntity(View view){
        if(view instanceof Sprite){
            for(int i=sprites.getChildren().size()-1;i>=0;i--){
                View sprite = sprites.getChildren().get(i);
                if(sprite.getId()==view.getId()){
                    sprites.getChildren().remove(i);
                    break;
                }
            }
        }

        if(view instanceof UI || view instanceof UIGroup){

           for(int i=uis.getChildren().size()-1;i>=0;i--){
                View t = uis.getChildren().get(i);
                if(t.getId()==view.getId()){
                    uis.getChildren().remove(i);
                    break;
                }
                if(t instanceof UIGroup){
                    for(int j=((UIGroup) t).getChildren().size()-1;j>=0;j--){
                        removeEntity(view, (ViewGroup) t);
                    }
                }
            }
        }


        if(view instanceof BG){

        }
    }

    private static  void removeEntity(View view, ViewGroup t) {
        for(int i=t.getChildren().size()-1;i>=0;i--){
            View temp = t.getChildren().get(i);
            if(temp.getId()==view.getId()){
                t.getChildren().remove(i);
                break;
            }
            if(temp instanceof ViewGroup){
                removeEntity(view, (ViewGroup) temp);
            }
        }
    }




    public static ViewGroup getRootViewGroup() {
        return rootViewGroup;
    }

}
