import edu.princeton.cs.stdlib.StdDraw;
import java.awt.*;
/**
 * Screensaver con lineas
 * @author Gabriel Véliz Garcés
 */
public class Main {
    public static void main(String[] args) {

        //Rango de dibujado//
        double min = -1.0;
        double max = 1.0;

        escala(min, max);

        buffering();


        //Se definen dos conjuntos (x, Y) para poder dibujar una linea//
        double x0 = min + (max-min) * Math.random();
        double y0 = min + (max-min) * Math.random();
        double x1 = min + (max-min) * Math.random();
        double y1 = min + (max-min) * Math.random();

        //se crean arreglos para guardar los conjuntos (x, Y) de las siguientes 5 lineas//
        double[] lineasX0 = new double[5];
        double[] lineasY0 = new double[5];
        double[] lineasX1 = new double[5];
        double[] lineasY1 = new double[5];

        //Se define la velocidad que tendran las lineas//
        double vx = Math.random()*0.04;
        double vy = Math.random()*0.04;

        double a0 = Math.random()*0.04;
        double b0 = Math.random()*0.04;


        coordLineas(x0, y0, x1, y1, lineasX0, lineasY0, lineasX1, lineasY1);




        //Se inicia el loop infinito que permitira que el screensaver no se detenga//
        loop(x0, y0, x1, y1, lineasX0, lineasY0, lineasX1, lineasY1, vx, vy, a0, b0);
    }
    /**
     * @param x0 coordenada x0 de la primera linea
     * @param y0 coordenada y0 de la primera linea
     * @param x1 coordenada x1 de la primera linea
     * @param y1 coordenada y1 de la primera linea
     * @param lineasX0 arreglo con la coordenada x0 de las 5 siguientes lineas
     * @param lineasY0 arreglo con la coordenada y0 de las 5 siguientes lineas
     * @param lineasX1 arreglo con la coordenada x1 de las 5 siguientes lineas
     * @param lineasY1 arreglo con la coordenada y1 de las 5 siguientes lineas
     * @param vx velocidad de las coordenadas x1 e x0
     * @param vy velocidad de las coordenadas y0 e y1
     * @param a0 segunda velocidad de las coordenadas x1 e x0 utilizada para hacer el movimiento de las lineas mas dinamico
     * @param b0 segunda velocidad de las coordenadas x1 e x0 utilizada para hacer el movimiento de las lineas mas dinamico
     */
    private static void loop(double x0, double y0, double x1, double y1, double[] lineasX0, double[] lineasY0, double[] lineasX1, double[] lineasY1, double vx, double vy, double a0, double b0) {
        while (true) {

            //Se define que las lineas al colisionar con el limite de la pantalla rebotara//
            if (Math.abs(x0 + vx) > 1.0 || Math.abs(x1 + vx) > 1.0) {
                vx = -vx;
            }
            if (Math.abs(y0 + vy) > 1.0 || Math.abs(y1 + vy) > 1.0) {
                vy = -vy;
            }
            if (Math.abs(x0 + a0) > 1.0 || Math.abs(x1 + a0) > 1.0) {
                a0 = -a0;
            }
            if (Math.abs(y0 + b0) > 1.0 || Math.abs(y1 + b0) > 1.0) {
                b0 = -b0;
            }


            //Se actualiza la posición de la linea original//
            x0 += a0;
            y0 += b0;
            x1 += vx;
            y1 += vy;

            //Debido al caracter aleatoria de la velocidad, puede que las lineas se queden atascadas debido a su tamaño//


            posLineas(x0, y0, x1, y1, lineasX0, lineasY0, lineasX1, lineasY1);


            dibujoLineas(x0, y0, x1, y1, lineasX0, lineasY0, lineasX1, lineasY1);


            Pantalla();

            delay();

        }
    }
    private static void buffering() {
        //Se ocupa para evitar el parpadeo de la pantalla//
        StdDraw.enableDoubleBuffering();
    }
    /**
     * @param min Se define el minimo de la escala del lienzo del dibujo
     * @param max Se define el maximo de la escala del lienzo del dibujo
     */
    private static void escala(double min, double max) {
        //Se define la escala del dibujado//
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
    }
    /**
     * @param x0 coordenada x0 de la primera linea
     * @param y0 coordenada y0 de la primera linea
     * @param x1 coordenada x1 de la primera linea
     * @param y1 coordenada y1 de la primera linea
     * @param lineasX0 arreglo con la coordenada x0 de las 5 siguientes lineas
     * @param lineasY0 arreglo con la coordenada y0 de las 5 siguientes lineas
     * @param lineasX1 arreglo con la coordenada x1 de las 5 siguientes lineas
     * @param lineasY1 arreglo con la coordenada y1 de las 5 siguientes lineas
     */
    private static void coordLineas(double x0, double y0, double x1, double y1, double[] lineasX0, double[] lineasY0, double[] lineasX1, double[] lineasY1) {
        //Se guardan las coordenas de las siguientes lineas desde la primera linea (la cual las demas lineas van a seguir)//
        for (int i = 0; i < 5; i++){
            lineasX0[i] = x0;
            lineasY0[i] = y0;

            lineasX1[i] = x1;
            lineasY1[i] = y1;
        }
    }
    /**
     * @param x0 coordenada x0 de la primera linea
     * @param y0 coordenada y0 de la primera linea
     * @param x1 coordenada x1 de la primera linea
     * @param y1 coordenada y1 de la primera linea
     * @param lineasX0 arreglo con la coordenada x0 de las 5 siguientes lineas
     * @param lineasY0 arreglo con la coordenada y0 de las 5 siguientes lineas
     * @param lineasX1 arreglo con la coordenada x1 de las 5 siguientes lineas
     * @param lineasY1 arreglo con la coordenada y1 de las 5 siguientes lineas
     */
    private static void posLineas(double x0, double y0, double x1, double y1, double[] lineasX0, double[] lineasY0, double[] lineasX1, double[] lineasY1) {
        //Se actualizan las coordenadas de las demas lineas//
        lineasX0[0] = x0;
        lineasY0[0] = (y0 - 0.05);
        lineasX1[0] = x1;
        lineasY1[0] = (y1 - 0.05);
        lineasX0[1] = x0;
        lineasY0[1] = (y0 - 0.1);
        lineasX1[1] = x1;
        lineasY1[1] = (y1 - 0.1);
        lineasX0[2] = x0;
        lineasY0[2] = (y0 - 0.15);
        lineasX1[2] = x1;
        lineasY1[2] = (y1 - 0.15);
        lineasX0[3] = x0;
        lineasY0[3] = (y0 - 0.2);
        lineasX1[3] = x1;
        lineasY1[3] = (y1 - 0.2);
        lineasX0[4] = x0;
        lineasY0[4] = (y0 - 0.25);
        lineasX1[4] = x1;
        lineasY1[4] = (y1 - 0.25);
    }
    /**
     * @param x0 coordenada x0 de la primera linea
     * @param y0 coordenada y0 de la primera linea
     * @param x1 coordenada x1 de la primera linea
     * @param y1 coordenada y1 de la primera linea
     * @param lineasX0 arreglo con la coordenada x0 de las 5 siguientes lineas
     * @param lineasY0 arreglo con la coordenada y0 de las 5 siguientes lineas
     * @param lineasX1 arreglo con la coordenada x1 de las 5 siguientes lineas
     * @param lineasY1 arreglo con la coordenada y1 de las 5 siguientes lineas
     */
    private static void dibujoLineas(double x0, double y0, double x1, double y1, double[] lineasX0, double[] lineasY0, double[] lineasX1, double[] lineasY1) {
        //Se define el color de las lineas y se dibujan//
        StdDraw.setPenColor(Color.red);
        StdDraw.clear(Color.black);
        StdDraw.line(x0, y0, x1, y1);
        StdDraw.setPenColor(Color.blue);
        StdDraw.line(lineasX0[0], lineasY0[0], lineasX1[0], lineasY1[0]);
        StdDraw.setPenColor(Color.orange);
        StdDraw.line(lineasX0[1], lineasY0[1], lineasX1[1], lineasY1[1]);
        StdDraw.setPenColor(Color.white);
        StdDraw.line(lineasX0[2], lineasY0[2], lineasX1[2], lineasY1[2]);
        StdDraw.setPenColor(Color.magenta);
        StdDraw.line(lineasX0[3], lineasY0[3], lineasX1[3], lineasY1[3]);
        StdDraw.setPenColor(Color.green);
        StdDraw.line(lineasX0[4], lineasY0[4], lineasX1[4], lineasY1[4]);
    }
    private static void delay() {
        //Se genera un delay de 10 milisegundos entre cada fotograma para que la animacion sea mas facil de ver//
        StdDraw.pause(10);
    }
    private static void Pantalla() {
        //se despliaga la pantalla//
        StdDraw.show();
    }
}