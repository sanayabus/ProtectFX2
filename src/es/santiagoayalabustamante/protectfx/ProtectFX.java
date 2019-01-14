/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.santiagoayalabustamante.protectfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 *
 * @author Santi
 */
public class ProtectFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        int ancho = 600;
        int alto = 400;
        Rectangle rectangFlecha = new Rectangle(0, 0, 15, 10);
        Polygon triangFlecha = new Polygon(new double[]{
            15.0, -5.0,
            25.0, 5.0,
            15.0, 15.0 });
        Circle circuloCorazon1 = new Circle (5, 5, 5, Color.RED);
        Circle circuloCorazon2 = new Circle (13, 5, 5, Color.RED);
        
        rectangFlecha.setFill(Color.BLACK);
        triangFlecha.setFill(Color.BLACK);
        Pane root = new Pane();
        Scene scene = new Scene(root, ancho, alto); 
        
        primaryStage.setTitle("FlechaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Group flecha = new Group();
        flecha.getChildren().add(rectangFlecha);
        flecha.getChildren().add(triangFlecha);
        
        Group corazon = new Group ();
        corazon.getChildren().add(circuloCorazon1);
        corazon.getChildren().add(circuloCorazon2);
        
        flecha.setLayoutX(0);
        flecha.setLayoutY(alto/2);
        
        root.getChildren().add(flecha);
        root.getChildren().add(corazon);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
