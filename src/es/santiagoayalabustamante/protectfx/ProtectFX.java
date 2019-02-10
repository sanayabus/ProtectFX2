
// En este proyecto crearemos el videojuego Protect, que está basado en una de las fases de la pelea con Undyne del
// videojuego Undertale, la cual consiste en usar una barrera para defenderte de unas lanzas que te lanza ella.
// Hecho por Santiago Ayala Bustamante en 10/02/2019

package es.santiagoayalabustamante.protectfx;

import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    
    int puntosSalud = 100;
    int ancho = 600;
    int alto = 600;
    double velocidadDificultad = 0;
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
    MediaPlayer mediaPlayer;
    Flecha flecha1 = new Flecha();
    Flecha flecha2 = new Flecha();
    Flecha flecha3 = new Flecha();
    Flecha flecha4 = new Flecha();
    boolean pausa = false;
    
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
        
        //Texto para el cronómetro y la puntuación máxima
        
        Text cronometro = new Text();
        cronometro.setFont (Font.font(24));
        cronometro.setFill (Color.WHITE);
        cronometro.setLayoutX (ancho-210);
        cronometro.setLayoutY (50);
        
        Text cronometroMáx = new Text ();
        cronometroMáx.setFont (Font.font(24));
        cronometroMáx.setFill (Color.WHITE);
        cronometroMáx.setLayoutX (ancho-260);
        cronometroMáx.setLayoutY (80);
        
        //Música
        //Puedes encontrar la canción aquí https://www.youtube.com/watch?v=8Ka1QZQ4zf0
        
        final URL resource = getClass().getResource("Alejandro Sans - alma partía [Mashup].mp3");
        Media media = new Media(resource.toString());
        mediaPlayer = new MediaPlayer(media);
        
        // Imagen Inicial
        
        Image image1 = new Image(getClass().getResourceAsStream("introduccion.png"));
        ImageView imageView1 = new ImageView(image1);
        
        //Pantalla, resolución y color de fondo
         
        Pane root = new Pane();
        Scene scene = new Scene(root, ancho, alto);
        scene.setFill(Color.BLACK);
        
        primaryStage.setTitle("ProtectFX");
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
        
        // Posicionamiento de las flechas inicialmente, así como la velocidad y orientación 
        // 
        
        velocidadDificultad = 2.0;
        flecha2.setRotate (90.0);        
        flecha3.setRotate (180.0);        
        flecha4.setRotate (270.0);
        flecha1.setLayoutX(-100);
        flecha2.setLayoutX(-100);
        flecha3.setLayoutX(-100);
        flecha4.setLayoutX(-100);
        flecha1.posX = (-20);
        flecha1.posY = alto/2;
        flecha1.velY = 0;
        flecha1.velX = velocidadDificultad;
        flecha2.posX = ancho/2;
        flecha2.posY = (-100);
        flecha2.velX = 0;
        flecha2.velY = velocidadDificultad;
        flecha3.posX = ancho+180;
        flecha3.posY = alto/2;
        flecha3.velX = -velocidadDificultad;
        flecha3.velY = 0;
        flecha4.posX = ancho/2;
        flecha4.posY = alto+260;
        flecha4.velX = 0;
        flecha4.velY = -velocidadDificultad;
        
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
        root.getChildren().add(imageView1);
        
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
                // Botón de cierre
                case ESCAPE:
                    primaryStage.close();
                    break;
                // Botón de Pausa
                case ENTER:
                    if (pausa == false){
                        mediaPlayer.pause();
                        animacion.stop();
                        pausa = true;
                    } else{
                        animacion.start();
                        mediaPlayer.play();
                        pausa = false;
                    }
                    break;
                // Botón de Inicio/Reinicio
                case SPACE:
                    imageView1.setOpacity(0.0);
                    inicial = LocalDateTime.now();
                    mediaPlayer.stop();// Con el mediaplayer stop y play, reiniciamos la canción
                    mediaPlayer.play();
                    animacion.start();
                    this.restart();
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
                cronometro.setText("Puntuación: "+format);
                System.out.println(milesimas);
                
               //Movimiento de las flechas
               
                flecha1.posX += flecha1.velX;
                flecha1.posY += flecha1.velY;
                flecha1.setLayoutX(flecha1.posX);
                flecha1.setLayoutY(flecha1.posY);
                
                flecha2.posX += flecha2.velX;
                flecha2.posY += flecha2.velY;
                flecha2.setLayoutX(flecha2.posX);
                flecha2.setLayoutY(flecha2.posY);
                
                flecha3.posX += flecha3.velX;
                flecha3.posY += flecha3.velY;
                flecha3.setLayoutX(flecha3.posX);
                flecha3.setLayoutY(flecha3.posY);
                
                flecha4.posX += flecha4.velX;                
                flecha4.posY += flecha4.velY;
                flecha4.setLayoutX(flecha4.posX);
                flecha4.setLayoutY(flecha4.posY);
                
                //Colisionamiento de las flechas con el escudo
                
                Shape colisionEscudo1 = Shape.intersect(flecha1.triangFlecha, rectangEscudo1);
                boolean colisionVacia1 = colisionEscudo1.getBoundsInLocal().isEmpty();

                if(colisionVacia1 == false){
                    this.orientacion(flecha1);
                }
                
                Shape colisionEscudo2 = Shape.intersect(flecha2.triangFlecha, rectangEscudo1);
                boolean colisionVacia2 = colisionEscudo2.getBoundsInLocal().isEmpty();

                if(colisionVacia2 == false){
                    this.orientacion(flecha2);
                }
                
                Shape colisionEscudo3 = Shape.intersect(flecha3.triangFlecha, rectangEscudo1);
                boolean colisionVacia3 = colisionEscudo3.getBoundsInLocal().isEmpty();

                if(colisionVacia3 == false){
                    this.orientacion(flecha3);
                }
                
                Shape colisionEscudo4 = Shape.intersect(flecha4.triangFlecha, rectangEscudo1);
                boolean colisionVacia4 = colisionEscudo4.getBoundsInLocal().isEmpty();
 
                if(colisionVacia4 == false){
                    this.orientacion(flecha4);
                }
                
                //Colisionamiento de las flechas con el corazón
                
                Shape colisionCorazon1 = Shape.intersect(flecha1.triangFlecha, circuloCorazon1);
                boolean daño1 = colisionCorazon1.getBoundsInLocal().isEmpty();

                if(daño1 == false){
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                    this.orientacion(flecha1);
                }
                
                Shape colisionCorazon2 = Shape.intersect(flecha2.triangFlecha, circuloCorazon1);
                boolean daño2 = colisionCorazon2.getBoundsInLocal().isEmpty();

                if(daño2 == false){
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                    this.orientacion(flecha2);
                }
                
                Shape colisionCorazon3 = Shape.intersect(flecha3.triangFlecha, circuloCorazon2);
                boolean daño3 = colisionCorazon3.getBoundsInLocal().isEmpty();

                if(daño3 == false){
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                    this.orientacion(flecha3);
                }
                
                Shape colisionCorazon4 = Shape.intersect(flecha4.triangFlecha, triangCorazon);
                boolean daño4 = colisionCorazon4.getBoundsInLocal().isEmpty();

                if(daño4 == false){
                    puntosSalud = puntosSalud-10;
                    indicadorPS.setWidth(puntosSalud);
                    this.orientacion(flecha4);
                }
                
                // Incremento de la velocidad de las flechas acorde con la canción hasta un punto razonable
                
                if (milesimas < 6500){
                    velocidadDificultad = 2;
                } else if (milesimas >= 6500 && milesimas < 14500){
                    velocidadDificultad = 2.5;
                } else if (milesimas >= 14500 && milesimas < 30500){
                    velocidadDificultad = 3;
                } else if (milesimas >= 30500 && milesimas < 38500){
                    velocidadDificultad = 3.5;
                } else if (milesimas >= 38500 && milesimas <46000){
                    velocidadDificultad = 4;
                } else if (milesimas >= 46000 && milesimas < 118000){
                    velocidadDificultad = 4.5;
                } else if (milesimas >= 118000){
                    velocidadDificultad = 5;
                }
                
                // Recuperación de vida cada 10 segundos, sólo hasta el segundo 60
                // Tienen un poco de margen porque no todas las milésimas aparecen en la variable, cosa
                // que se puede comprobar en la consola del sistema gracias a la línea 313
                
                if (milesimas >= 9990 && milesimas <= 10010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                if (milesimas >= 19990 && milesimas <= 20010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                if (milesimas >= 29990 && milesimas <= 30010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                if (milesimas >= 39990 && milesimas <= 40010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                if (milesimas >= 49990 && milesimas <= 50010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                if (milesimas >= 59990 && milesimas <= 60010){
                    if (puntosSalud < 100){
                        puntosSalud = puntosSalud+10;
                        indicadorPS.setWidth(puntosSalud);
                    }
                }
                
                //Cambiamos la barra de salud de color para dar una mayor sensación del estado crítico
                // y volvemos a cambiarla a amarillo en el caso de que recuperemos algo de vida
                
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
                } else {
                    bordePS.setFill(Color.YELLOW);
                    indicadorPS.setFill(Color.YELLOW);
                    izqHache.setFill(Color.YELLOW);
                    derHache.setFill(Color.YELLOW);
                    midHache.setFill(Color.YELLOW);
                    barraIzqP.setFill (Color.YELLOW);
                    barraDerP.setFill (Color.YELLOW);
                    barraSupP.setFill (Color.YELLOW);
                    barraInfP.setFill (Color.YELLOW);
                }
                
                // Para la animación cuando la salud llegue a 0 y comprueba si la puntuación conseguida es la nueva
                // puntuación máxima
                
                if(puntosSalud <=0){
                    mediaPlayer.stop();
                    this.stop();
                    if(milesimas > highScore){
                        highScore = milesimas;
                        cronometroMáx.setText("Puntuación Máx: "+format);
                    }
                }                                 
            };
            
            //Calles para las flechas
            
            public void orientacion(Flecha flecha){
                switch(random.nextInt(4)){
                    case IZQUIERDA:
                        flecha.posX = 0-random.nextInt(100);
                        flecha.posY = alto/2;
                        flecha.velX = velocidadDificultad;
                        flecha.velY = 0;
                        flecha.setLayoutX(flecha.posX);
                        flecha.setLayoutY(flecha.posY);
                        flecha.setRotate(0.0);
                        break;
                    case ARRIBA:
                        flecha.posX = ancho/2;
                        flecha.posY = 0-random.nextInt(100);
                        flecha.velX = 0;
                        flecha.velY = velocidadDificultad;
                        flecha.setLayoutX(flecha.posX);
                        flecha.setLayoutY(flecha.posY);
                        flecha.setRotate(90.0);
                        break;
                    case DERECHA:
                        flecha.posX = ancho+random.nextInt(100);
                        flecha.posY = alto/2;
                        flecha.velY = 0;
                        flecha.velX = -velocidadDificultad;
                        flecha.setLayoutX(flecha.posX);
                        flecha.setLayoutY(flecha.posY);
                        flecha.setRotate(180.0);
                        break;
                    case ABAJO:
                        flecha.posX = ancho/2;
                        flecha.posY = alto+random.nextInt(100);
                        flecha.velX = 0;
                        flecha.velY = -velocidadDificultad;
                        flecha.setLayoutX(flecha.posX);
                        flecha.setLayoutY(flecha.posY);
                        flecha.setRotate(270.0);
                        break;
                }

            }
            
        };
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

    
    //Reinicio
    
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
        velocidadDificultad = 2;
        flecha1.posX = (-20);
        flecha1.posY = alto/2;
        flecha1.velY = 0;
        flecha1.velX = velocidadDificultad;
        flecha2.posX = ancho/2;
        flecha2.posY = (-100);
        flecha2.velX = 0;
        flecha2.velY = velocidadDificultad;
        flecha3.posX = ancho+180;
        flecha3.posY = alto/2;
        flecha3.velX = -velocidadDificultad;
        flecha3.velY = 0;
        flecha4.posX = ancho/2;
        flecha4.posY = alto+260;
        flecha4.velX = 0;
        flecha4.velY = -velocidadDificultad;
        flecha1.setRotate(0.0);
        flecha2.setRotate (90.0);        
        flecha3.setRotate (180.0);        
        flecha4.setRotate (270.0);
        mediaPlayer.play();
        animacion.start();
    }
}