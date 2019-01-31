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
    int posX = 0;
    int posY = 0;
    int velX = 0;
    int velY = 0;
    
    public Flecha() {
        Rectangle rectangFlecha = new Rectangle(0, 0, 15, 10);
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
