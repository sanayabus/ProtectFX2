
package es.santiagoayalabustamante.protectfx;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Santi
 */
public class ProtectFX extends Application {
    
    //Variables
    
    int flecha1X = 0;
    int flecha1Y = 0;
    int flecha2X = 0;
    int flecha2Y = 0;
    int flecha3X = 0;
    int flecha3Y = 0;
    int flecha4X = 0;
    int flecha4Y = 0;
    int puntosSalud = 100;
    int ancho = 600;
    int alto = 600;
    Rectangle bordePS;
    Rectangle huecoPS;
    Rectangle indicadorPS;
    Rectangle izqHache;
    Rectangle derHache;
    Rectangle midHache;
    Rectangle barraIzqP;
    Rectangle barraDerP;
    Rectangle barraSupP;
    Rectangle barraInfP;
    Group escudo = new Group();
    AnimationTimer animacion;
    
    Flecha flecha1 = new Flecha();
    Flecha flecha2 = new Flecha();
    Flecha flecha3 = new Flecha();
    Flecha flecha4 = new Flecha();
    
    //flecha1.posX = -10;
    
    //Variables para cronómetro
    
    LocalDateTime inicial;
    long milesimas = 0;
    long highScore = 0;
    
    //Constantes para canales de flechas
    
    final int IZQUIERDA = 0;
    final int ARRIBA = 1;
    final int DERECHA = 2;
    final int ABAJO = 3;
    
    //Números aleatorios
    
    Random random = new Random();
    
    @Override
    public void start(Stage primaryStage) {
        
        //Barra de Salud
        
        bordePS = new Rectangle (20, 20, 112, 40);
        huecoPS = new Rectangle (25, 25, 102, 30);
        indicadorPS = new Rectangle (26, 26, puntosSalud, 28);
        bordePS.setFill(Color.YELLOW);
        huecoPS.setFill(Color.BLACK);
        indicadorPS.setFill(Color.YELLOW);
        
        //Letra H de HP (Health Points)
        
        izqHache = new Rectangle (150, 25, 5, 30);
        derHache = new Rectangle (165, 25, 5, 30);
        midHache = new Rectangle (150, 37, 20, 5);
        izqHache.setFill(Color.YELLOW);
        derHache.setFill(Color.YELLOW);
        midHache.setFill(Color.YELLOW);
        
        //Letra P de HP (Heslth Points)
        
        barraIzqP = new Rectangle (180, 25, 5, 30);
        barraDerP = new Rectangle (194, 25, 5, 15);
        barraSupP = new Rectangle (180, 25, 19, 5);
        barraInfP = new Rectangle (180, 39, 19, 5);
        barraIzqP.setFill (Color.YELLOW);
        barraDerP.setFill (Color.YELLOW);
        barraSupP.setFill (Color.YELLOW);
        barraInfP.setFill (Color.YELLOW);
        
        //Escudo
        
        Rectangle rectangEscudo1 = new Rectangle (3, 3, 50, 3);
        Rectangle rectangEscudo2 = new Rectangle (23, 9, 30, 3);
        rectangEscudo1.setFill(Color.BLUEVIOLET);
        rectangEscudo2.setFill(Color.BLUEVIOLET);
        rectangEscudo2.setRotate(160.0);
        
        //Corazón

        Circle circuloCorazon1 = new Circle (5, 5, 5, Color.RED);
        Circle circuloCorazon2 = new Circle (13, 5, 5, Color.RED);
        Polygon triangCorazon = new Polygon (new double []{
            1.0, 8.0,
            17.0, 8.0,
            9.0, 17.0 });
        triangCorazon.setFill(Color.RED);
        
        //Texto Cronómetro
        
        Text cronometro = new Text();
        cronometro.setFont (Font.font(24));
        cronometro.setFill (Color.WHITE);
        cronometro.setLayoutX (ancho-80);
        cronometro.setLayoutY (50);
        
        Text cronometroMáx = new Text ();
        cronometroMáx.setFont (Font.font(24));
        cronometroMáx.setFill (Color.WHITE);
        cronometroMáx.setLayoutX (ancho-80);
        cronometroMáx.setLayoutY (80);
        
        //Pantalla, resolución y color
         
        Pane root = new Pane();
        Scene scene = new Scene(root, ancho, alto);
        scene.setFill(Color.BLACK);
        
        primaryStage.setTitle("FlechaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Agrupación de elementos anteriormente creados
            
        Group corazon = new Group ();
        corazon.getChildren().add(circuloCorazon1);
        corazon.getChildren().add(circuloCorazon2);
        corazon.getChildren().add(triangCorazon);
        
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
        
        // Orientaciones de las figuras importadas tipo flecha, y sacamos las flechas de
        //la pantalla para que no aparezcan en la esquina superior izquierda
        
        flecha2.setRotate (90.0);        
        flecha3.setRotate (180.0);        
        flecha4.setRotate (270.0);
        flecha1.setLayoutX (-100);
        flecha2.setLayoutX (-100);
        flecha3.setLayoutX (-100);
        flecha4.setLayoutX (-100);
        
        //Posicionamiento de el escudo y el corazón
        
        corazon.setLayoutX(ancho/2+3);
        corazon.setLayoutY(alto/2);
        
        escudo.setLayoutX(ancho/2-15);
        escudo.setLayoutY(alto/2-25);
        
        //Asignamiento de los grupos al panel
        
        root.getChildren().add(flecha1);
        root.getChildren().add(flecha2);
        root.getChildren().add(flecha3);
        root.getChildren().add(flecha4);
        root.getChildren().add(corazon);
        root.getChildren().add(escudo);
        root.getChildren().add(barraPuntosSalud);
        root.getChildren().add(letraHache);
        root.getChildren().add(letraP);
        root.getChildren().add(cronometro);
        root.getChildren().add(cronometroMáx);
        
        //Eventos al pulsar teclas
        
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
                case ESCAPE:
                    primaryStage.close();
                    break;
                case SPACE:
                    inicial = LocalDateTime.now();
                    animacion.start();
                    if(puntosSalud <=0){
                        this.restart();
                    }
                    break; 
            }
        });
        
        animacion = new AnimationTimer(){
            @Override
            public void handle(long now){
                
                //Cronómetro
                
                LocalDateTime actual = LocalDateTime.now();
                milesimas = ChronoUnit.MILLIS.between(inicial, actual);
                
                Locale locale  = new Locale("en", "UK");
                String pattern = "##0.00";
                DecimalFormat decimalFormat = (DecimalFormat)
                        NumberFormat.getNumberInstance(locale);
                decimalFormat.applyPattern(pattern);
                String format = decimalFormat.format(milesimas/1000.0);
                cronometro.setText(format);
                System.out.println(format);
                
               //Movimiento de las flechas 
                
               flecha1.setLayoutX(flecha1.posX + flecha1.velX);
               
                flecha1.setLayoutX(flecha1X-10);
                flecha1.setLayoutY(alto/2);
                
                flecha2.setLayoutX(ancho/2);
                flecha2.setLayoutY(flecha2Y-110);
                
                flecha3.setLayoutX(ancho + flecha3X+210);
                flecha3.setLayoutY(alto/2);
                
                flecha4.setLayoutX(ancho/2);
                flecha4.setLayoutY(alto + flecha4Y+360);
                
                //Colisionamiento de las flechas con el escudo
                
                Shape colisionEscudo1 = Shape.intersect(flecha1.triangFlecha, rectangEscudo1);
                boolean colisionVacia1 = colisionEscudo1.getBoundsInLocal().isEmpty();

                if(colisionVacia1 == false){
                    int numFlecha1 = random.nextInt(ancho/2);
                    flecha1.setLayoutX(-50);
                    flecha1X = 0-numFlecha1;
                }
                
                Shape colisionEscudo2 = Shape.intersect(flecha2.triangFlecha, rectangEscudo1);
                boolean colisionVacia2 = colisionEscudo2.getBoundsInLocal().isEmpty();

                if(colisionVacia2 == false){
                    int numFlecha2 = random.nextInt(alto/2);
                    flecha2.setLayoutY(-50);
                    flecha2Y = 0-numFlecha2;
                }
                
                Shape colisionEscudo3 = Shape.intersect(flecha3.triangFlecha, rectangEscudo1);
                boolean colisionVacia3 = colisionEscudo3.getBoundsInLocal().isEmpty();

                if(colisionVacia3 == false){
                    int numFlecha3 = random.nextInt(ancho/2);
                    flecha3.setLayoutX(ancho+50);
                    flecha3X = 0+numFlecha3;
                }
                
                Shape colisionEscudo4 = Shape.intersect(flecha4.triangFlecha, rectangEscudo1);
                boolean colisionVacia4 = colisionEscudo4.getBoundsInLocal().isEmpty();
 
                if(colisionVacia4 == false){
                    int numFlecha4 = random.nextInt(alto/2);
                    flecha4.setLayoutY(alto+50);
                    flecha4Y = 0+numFlecha4;
                }
                
                //Colisionamiento de las flechas con el corazón
                
                Shape colisionCorazon1 = Shape.intersect(flecha1.triangFlecha, circuloCorazon1);
                boolean daño1 = colisionCorazon1.getBoundsInLocal().isEmpty();

                if(daño1 == false){
                    int numFlecha1 = random.nextInt(ancho/2);
                    flecha1.setLayoutX(-50);
                    flecha1X = 0-numFlecha1;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon2 = Shape.intersect(flecha2.triangFlecha, circuloCorazon1);
                boolean daño2 = colisionCorazon2.getBoundsInLocal().isEmpty();

                if(daño2 == false){
                    int numFlecha2 = random.nextInt(alto/2);
                    flecha2.setLayoutY(-50);
                    flecha2Y = 0-numFlecha2;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon3 = Shape.intersect(flecha3.triangFlecha, circuloCorazon2);
                boolean daño3 = colisionCorazon3.getBoundsInLocal().isEmpty();

                if(daño3 == false){
                    int numFlecha3 = random.nextInt(ancho/2);
                    flecha3.setLayoutX(ancho+50);
                    flecha3X = 0+numFlecha3;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                Shape colisionCorazon4 = Shape.intersect(flecha4.triangFlecha, triangCorazon);
                boolean daño4 = colisionCorazon4.getBoundsInLocal().isEmpty();

                if(daño4 == false){
                    int numFlecha4 = random.nextInt(alto/2);
                    flecha4.setLayoutY(alto+50);
                    flecha4Y = 0+numFlecha4;
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                }
                
                // Incremento de la velocidad de las flechas cada 10 segundos
                
                if (milesimas < 10000){
                    flecha1.velX = 2;
                    flecha1X += 2;
                    flecha2Y += 2;
                    flecha3X -= 2;
                    flecha4Y -= 2;
                } else if (milesimas >= 10000 && milesimas < 20000){
                    flecha1X += 2.5;
                    flecha2Y += 2.5;
                    flecha3X -= 2.5;
                    flecha4Y -= 2.5;
                } else if (milesimas >= 20000 && milesimas < 30000){
                    flecha1X += 3;
                    flecha2Y += 3;
                    flecha3X -= 3;
                    flecha4Y -= 3;
                } else if (milesimas >= 30000 && milesimas < 40000){
                    flecha1X += 3.5;
                    flecha2Y += 3.5;
                    flecha3X -= 3.5;
                    flecha4Y -= 3.5;
                } else if (milesimas >= 40000 && milesimas <50000){
                    flecha1X += 4;
                    flecha2Y += 4;
                    flecha3X -= 4;
                    flecha4Y -= 4;
                } else if (milesimas >= 50000 && milesimas < 60000){
                    flecha1X += 4.5;
                    flecha2Y += 4.5;
                    flecha3X -= 4.5;
                    flecha4Y -= 4.5;
                } else if (milesimas >= 60000){
                    flecha1X += 5;
                    flecha2Y += 5;
                    flecha3X -= 5;
                    flecha4Y -= 5;
                }
                
                //Cambiamos la barra de salud de color para dar una mayor sensación del estado crítico
                
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
                
                //Para la animación cuando la salud llegue a 0
                
                if(puntosSalud <=0){
                    this.stop();
                    if(milesimas > highScore){
                        highScore = milesimas;
                        cronometroMáx.setText(format);
                    }
                }
                
            };            
        };
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //Calles para las flechas
    
    private void orientacion(){
        switch(random.nextInt(3)){
            case IZQUIERDA:
                
                break;
            case ARRIBA:
                
                break;
            case DERECHA:
                
                break;
            case ABAJO:
                
                break;
        }
        
    }
    
    //Reinicio después del fin de partida
    
    private void restart(){
        //LocalDateTime inicial = LocalDateTime.now();
        puntosSalud = 100;
        indicadorPS.setWidth(puntosSalud);
        bordePS.setFill(Color.YELLOW);
        indicadorPS.setFill(Color.YELLOW);
        izqHache.setFill(Color.YELLOW);
        derHache.setFill(Color.YELLOW);
        midHache.setFill(Color.YELLOW);
        barraIzqP.setFill (Color.YELLOW);
        barraDerP.setFill (Color.YELLOW);
        barraSupP.setFill (Color.YELLOW);
        barraInfP.setFill (Color.YELLOW);
        flecha1X = 0;
        flecha2Y = 0;
        flecha3X = 0;
        flecha4Y = 0;
        animacion.start();
    }
}
