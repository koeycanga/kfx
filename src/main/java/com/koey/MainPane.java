package com.koey;


import com.koey.fx.KFX;

import com.koey.fx.view.View;
import com.koey.fx.view.ViewGroup;
import com.koey.view.bg.LT;
import com.koey.view.player.LuoTuo;
import com.koey.view.player.Player;

import com.koey.view.service.LTLogic;
import com.koey.view.ui.Button;
import com.koey.view.ui.ButtonGroup;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;


public class MainPane  extends Application  {


    public static void main(String[] args) {
        launch(args);
    }

    private static final int width = 1024;
    private static final int height = 768;

    private Random rand = new Random();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
       // drawShapes(gc);
        root.getChildren().add(canvas);


        KFX.init(width,height);

        Player p1 = new Player(0,0,30,30);
        Player p2 = new Player(0,0,30,30);

        p1.setName("p1");
        p2.setName("p2");

        Player p3 = new Player(10,10,30,30);
        p3.setName("p3");

        KFX.addEntity(p1);
        KFX.addEntity(p2);
        KFX.addEntity(p3);

        LT bg = new LT(0,0,width,height);
        KFX.addEntity(bg);

        LuoTuo l1 = new LuoTuo(50,50,50,30,1);
        KFX.addEntity(l1);

        LuoTuo l2 = new LuoTuo(50,80,50,30,6);
        KFX.addEntity(l2);


        ButtonGroup buttonGroup = new ButtonGroup(100,0,1000,500);
        KFX.addEntity(buttonGroup);

        Button move = new Button(100,500,80,30,"Move");
        KFX.addEntity(move,buttonGroup);

        LTLogic.handleTouZi();

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              //  System.out.println("ok："+mouseEvent.getEventType());
                System.out.println(mouseEvent.getX()+"    "+mouseEvent.getY());
                KFX.getRootViewGroup().disPatchEvent(mouseEvent);
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              //  System.out.println("ok2："+mouseEvent.getEventType());
                KFX.getRootViewGroup().disPatchEvent(mouseEvent);
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                KFX.getRootViewGroup().disPatchEvent(mouseEvent);
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                KFX.getRootViewGroup().disPatchEvent(mouseEvent);
            }
        });

        canvas.requestFocus();
       /* canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
                p1.setLx(p1.getLx()+5);
            }
        });*/

        /*timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        keyFrame = new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                gc.clearRect(0,0,1024,768);
                p1.setLx(p1.getLx()+2);
                p1.setLy(p1.getLy()+1.2);
                rootViewGroup.onDraw(gc);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();*/

        Scene scene = new Scene(root);

       // primaryStage.setMaximized(true);
       // primaryStage.initStyle(StageStyle.UNDECORATED);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
                if(keyEvent.getCode().toString().equals("W")){
                   // p1.setLy(p1.getLy()-10);
                }
                if(keyEvent.getCode().toString().equals("S")){
                   // p1.setLy(p1.getLy()+10);
                }
                if(keyEvent.getCode().toString().equals("A")){
                   // p1.setLx(p1.getLx()-10);
                }
                if(keyEvent.getCode().toString().equals("D")){
                   // p1.setLx(p1.getLx()+10);
                  //  canvas.setLayoutX(canvas.getLayoutX()+10);
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();


        AnimationTimer a =  new AnimationTimer() {

            @Override
            public void handle(long l) {
                onUpdate(KFX.getRootViewGroup());
              //  gc.clearRect(0,0,width ,height);
                onDraw(KFX.getRootViewGroup(),gc);
                gc.strokeText("中文",50,80);
                // gc.fillText("AAAA",50,80);
            }

            private void onUpdate(ViewGroup root) {
                for(int i=root.getChildren().size()-1;i>=0;i--){
                    View v = root.getChildren().get(i);
                    v.onUpdate();
                    if(v instanceof ViewGroup){
                        onUpdate((ViewGroup) v);
                    }
                }
            }

            private void onDraw(ViewGroup root, GraphicsContext gc) {
                int lg = root.getChildren().size();
                for(int i=0;i<lg;i++){
                    View v = root.getChildren().get(i);
                    v.onDraw(gc);
                    if(v instanceof ViewGroup){
                        onDraw((ViewGroup) v,gc);
                    }
                }
            }

        };
        a.start();

    }


    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

}
