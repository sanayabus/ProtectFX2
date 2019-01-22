/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.santiagoayalabustamante.protectfx;

import java.util.Random;
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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author Santi
 */
public class ProtectFX extends Application {
    
    int flecha1X = 0;
    int flecha2Y = 0;
    int flecha3X = 0;
    int flecha4Y = 0;
    int puntosSalud = 100;
    
    @Override
    public void start(Stage primaryStage) {
        int ancho = 600;
        int alto = 600;
        
        Random random = new Random();
        
        Rectangle bordePS = new Rectangle (20, 20, 112, 40);
        Rectangle huecoPS = new Rectangle (25, 25, 102, 30);
        Rectangle indicadorPS = new Rectangle (26, 26, puntosSalud, 28);
        bordePS.setFill(Color.YELLOW);
        huecoPS.setFill(Color.BLACK);
        indicadorPS.setFill(Color.YELLOW);
        
        Rectangle izqHache = new Rectangle (150, 25, 5, 30);
        Rectangle derHache = new Rectangle (165, 25, 5, 30);
        Rectangle midHache = new Rectangle (150, 37, 20, 5);
        izqHache.setFill(Color.YELLOW);
        derHache.setFill(Color.YELLOW);
        midHache.setFill(Color.YELLOW);
        
        Rectangle barraIzqP = new Rectangle (180, 25, 5, 30);
        Rectangle barraDerP = new Rectangle (194, 25, 5, 15);
        Rectangle barraSupP = new Rectangle (180, 25, 19, 5);
        Rectangle barraInfP = new Rectangle (180, 39, 19, 5);
        barraIzqP.setFill (Color.YELLOW);
        barraDerP.setFill (Color.YELLOW);
        barraSupP.setFill (Color.YELLOW);
        barraInfP.setFill (Color.YELLOW);
        
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
         
        Pane root = new Pane();
        Scene scene = new Scene(root, ancho, alto);
        scene.setFill(Color.BLACK);
        
        primaryStage.setTitle("FlechaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
            
        Group corazon = new Group ();
        corazon.getChildren().add(circuloCorazon1);
        corazon.getChildren().add(circuloCorazon2);
        corazon.getChildren().add(triangCorazon);
        
        Group escudo = new Group();
        escudo.getChildren().add(rectangEscudo1);
        escudo.getChildren().add(rectangEscudo2);
        
        Group barraPuntosSalud = new Group();
        barraPuntosSalud.getChildren().add(bordePS);
        barraPuntosSalud.getChildren().add(huecoPS);
        barraPuntosSalud.getChildren().add(indicadorPS);
        
        Group letraHache = new Group();
        letraHache.getChildren().add(izqHache);
        letraHache.getChildren().add(derHache);
        letraHache.getChildren().add(midHache);
        
        Group letraP = new Group();
        letraP.getChildren().add(barraIzqP);
        letraP.getChildren().add(barraDerP);
        letraP.getChildren().add(barraSupP);
        letraP.getChildren().add(barraInfP);
        
        Flecha flecha1 = new Flecha();
        
        Flecha flecha2 = new Flecha();
        flecha2.setRotate (90.0);
        
        Flecha flecha3 = new Flecha();
        flecha3.setRotate (180.0);
        
        Flecha flecha4 = new Flecha();
        flecha4.setRotate (270.0);
        
        corazon.setLayoutX(ancho/2+3);
        corazon.setLayoutY(alto/2);
        
        escudo.setLayoutX(ancho/2-15);
        escudo.setLayoutY(alto/2-25);
        
        root.getChildren().add(flecha1);
        root.getChildren().add(flecha2);
        root.getChildren().add(flecha3);
        root.getChildren().add(flecha4);
        root.getChildren().add(corazon);
        root.getChildren().add(escudo);
        root.getChildren().add(barraPuntosSalud);
        root.getChildren().add(letraHache);
        root.getChildren().add(letraP);
        
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch(event.getCode()){
                case UP:
                    escudo.setRotate(0.0);
                    escudo.setLayoutX(ancho/2-15);
                    escudo.setLayoutY(alto/2-25);
                    break;
                case DOWN:
                    escudo.setRotate(180.0);
                    escudo.setLayoutX(ancho/2-15);
                    escudo.setLayoutY(alto/2+15);
                    break;
                case LEFT:
                    escudo.setRotate(270.0);
                    escudo.setLayoutX(ancho/2-35);
                    escudo.setLayoutY(alto/2-5);
                    break;
                case RIGHT:
                    escudo.setRotate(90.0);
                    escudo.setLayoutX(ancho/2+5);
                    escudo.setLayoutY(alto/2-5);
                    break;
            }
        });
        
        AnimationTimer animacion = new AnimationTimer(){
            @Override
            public void handle(long now){
                flecha1X += 1;
                flecha1.setLayoutX(flecha1X-10);
                flecha1.setLayoutY(alto/2);
                
                flecha2Y += 1;
                flecha2.setLayoutX(ancho/2);
                flecha2.setLayoutY(flecha2Y-110);
                
                flecha3X -= 1;
                flecha3.setLayoutX(ancho + flecha3X+210);
                flecha3.setLayoutY(alto/2);
                
                flecha4Y -= 1;
                flecha4.setLayoutX(ancho/2);
                flecha4.setLayoutY(alto + flecha4Y+360);
                
                Shape colisionEscudo1 = Shape.intersect(flecha1.triangFlecha, rectangEscudo1);
                boolean colisionVacia1 = colisionEscudo1.getBoundsInLocal().isEmpty();

                if(colisionVacia1 == false){
                    int numFlecha1 = random.nextInt(ancho/2);
                    flecha1.setLayoutX(-30);
                    flecha1X = 0-numFlecha1;
                }
                
                Shape colisionEscudo2 = Shape.intersect(flecha2.triangFlecha, rectangEscudo1);
                boolean colisionVacia2 = colisionEscudo2.getBoundsInLocal().isEmpty();

                if(colisionVacia2 == false){
                    flecha2.setLayoutY(-30);
                    flecha2Y = 0;
                }
                
                Shape colisionEscudo3 = Shape.intersect(flecha3.triangFlecha, rectangEscudo1);
                boolean colisionVacia3 = colisionEscudo3.getBoundsInLocal().isEmpty();

                if(colisionVacia3 == false){
                    flecha3.setLayoutX(ancho+30);
                    flecha3X = 0;
                }
                
                Shape colisionEscudo4 = Shape.intersect(flecha4.triangFlecha, rectangEscudo1);
                boolean colisionVacia4 = colisionEscudo4.getBoundsInLocal().isEmpty();
 
                if(colisionVacia4 == false){
                    flecha4.setLayoutY(alto+30);
                    flecha4Y = 0;
                }
                
                Shape colisionCorazon1 = Shape.intersect(flecha1.triangFlecha, circuloCorazon1);
                boolean daño1 = colisionCorazon1.getBoundsInLocal().isEmpty();

                if(daño1 == false){
                    flecha1.setLayoutX(-30);
                    flecha1X = 0;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon2 = Shape.intersect(flecha2.triangFlecha, circuloCorazon1);
                boolean daño2 = colisionCorazon2.getBoundsInLocal().isEmpty();

                if(daño2 == false){
                    flecha2.setLayoutY(-30);
                    flecha2Y = 0;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon3 = Shape.intersect(flecha3.triangFlecha, circuloCorazon2);
                boolean daño3 = colisionCorazon3.getBoundsInLocal().isEmpty();

                if(daño3 == false){
                    flecha3.setLayoutX(ancho+30);
                    flecha3X = 0;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon4 = Shape.intersect(flecha4.triangFlecha, triangCorazon);
                boolean daño4 = colisionCorazon4.getBoundsInLocal().isEmpty();

                if(daño4 == false){
                    flecha4.setLayoutY(alto+30);
                    flecha4Y = 0;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                if(puntosSalud <= 30){
                    bordePS.setFill(Color.RED);
                    indicadorPS.setFill(Color.RED);
                    izqHache.setFill(Color.RED);
                    derHache.setFill(Color.RED);
                    midHache.setFill(Color.RED);
                    barraIzqP.setFill (Color.RED);
                    barraDerP.setFill (Color.RED);
                    barraSupP.setFill (Color.RED);
                    barraInfP.setFill (Color.RED);
                }
            };            
        };
        animacion.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
