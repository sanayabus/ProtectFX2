/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.santiagoayalabustamante.protectfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Santi
 */
public class ProtectFX2 extends Application {
    
    int flechaX = 0;
    
    @Override
    public void start(Stage primaryStage) {
        int ancho = 800;
        int alto = 600;
        
        Rectangle rectangEscudo1 = new Rectangle (3, 3, 50, 3);
        Rectangle rectangEscudo2 = new Rectangle (23, 9, 30, 3);
        rectangEscudo1.setFill(Color.BLUEVIOLET);
        rectangEscudo2.setFill(Color.BLUEVIOLET);
        rectangEscudo2.setRotate(160.0);

        Circle circuloCorazon1 = new Circle (5, 5, 5, Color.RED);
        Circle circuloCorazon2 = new Circle (13, 5, 5, Color.RED);
        Polygon triangCorazon = new Polygon (new double []{
            1.0, 8.0,
            17.0, 8.0,
            9.0, 17.0 });
        triangCorazon.setFill(Color.RED);
        
        Rectangle rectangFlecha = new Rectangle(0, 0, 15, 10);
        Polygon triangFlecha = new Polygon(new double[]{
            15.0, -5.0,
            25.0, 5.0,
            15.0, 15.0 });
        rectangFlecha.setFill(Color.AQUAMARINE);
        triangFlecha.setFill(Color.AQUAMARINE);
        Pane root = new Pane();
        Scene scene = new Scene(root, ancho, alto);
        scene.setFill(Color.BLACK);
        
        primaryStage.setTitle("FlechaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Group flecha = new Group();
        flecha.getChildren().add(rectangFlecha);
        flecha.getChildren().add(triangFlecha);
        
        Group corazon = new Group ();
        corazon.getChildren().add(circuloCorazon1);
        corazon.getChildren().add(circuloCorazon2);
        corazon.getChildren().add(triangCorazon);
        
        Group escudo = new Group();
        escudo.getChildren().add(rectangEscudo1);
        escudo.getChildren().add(rectangEscudo2);
        
        corazon.setLayoutX(ancho/2);
        corazon.setLayoutY(alto/2);
        
        escudo.setLayoutX(ancho/2-20);
        escudo.setLayoutY(alto/2-30);
        
        root.getChildren().add(flecha);
        root.getChildren().add(corazon);
        root.getChildren().add(escudo);
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case UP:
                    escudo.setRotate(0.0);
                    escudo.setLayoutX(ancho/2-20);
                    escudo.setLayoutY(alto/2-30);
                    break;
                case DOWN:
                    escudo.setRotate(180.0);
                    escudo.setLayoutX(ancho/2-20);
                    escudo.setLayoutY(alto/2+25);
                    break;
                case LEFT:
                    escudo.setRotate(270.0);
                    escudo.setLayoutX(ancho/2-45);
                    escudo.setLayoutY(alto/2-5);
                    break;
                case RIGHT:
                    escudo.setRotate(90.0);
                    escudo.setLayoutX(ancho/2+10);
                    escudo.setLayoutY(alto/2-5);
                    break;
            }
        });
        
        AnimationTimer animacionFlecha = new AnimationTimer(){
            @Override
            public void handle(long now){
                flechaX += 2;
                flecha.setLayoutX(flechaX);
                flecha.setLayoutY(alto/2);
            };
        };
        animacionFlecha.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
