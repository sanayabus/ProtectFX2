/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.santiagoayalabustamante.protectfx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Santi
 */
public class Flecha extends Group {
    Polygon triangFlecha;
    double posX = 0;
    double posY = 0;
    double velX = 0;
    double velY = 0;
    
    public Flecha() {
        Rectangle rectangFlecha = new Rectangle(0, 0, 16, 10);
        triangFlecha = new Polygon(new double[]{
            15.0, -5.0,
            25.0, 5.0,
            15.0, 15.0 });
        rectangFlecha.setFill(Color.AQUAMARINE);
        triangFlecha.setFill(Color.AQUAMARINE);
        this.getChildren().add(rectangFlecha);
        this.getChildren().add(triangFlecha);
    }
    
}
