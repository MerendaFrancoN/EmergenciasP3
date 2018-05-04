package fel;

import java.util.Random;
import java.math.*;

public class GeneradorTiempos {


    public static int getTiempoEntreArribos(float tiempoActual, byte tipo) {
        /*Considero el tiempo actual ya que si se encuentra entre las 7-9 o 20-22 la cantidad de arribos es mayor*/

        Random random = new Random();
        double numeroRandom = random.nextDouble();
        switch (tipo) {
            /*Cuadro Clinico= Leve*/
            //TODO: Pasar a minutos
            case 0: {
                if ((tiempoActual >= 420 && tiempoActual <= 540) || (tiempoActual >= 1200 && tiempoActual <= 1320)) { //Desde las 7 a 9 o desde las 20 a las 22
                    if (numeroRandom <= 0.5)
                        return 10; //Para acumulada de 0.5 => 10 Minutos.
                    else {
                        if (numeroRandom <= 0.85)
                            return 20; //Para acumulada de 0.85 => 20 Minutos.
                        else
                            return 30; //Para acumulada de 1 => 30 Minutos
                    }
                } else {
                    if (numeroRandom <= 0.3)
                        return 20; //Para acumulada de 0.3=> 20 Minutos.
                    else {
                        if (numeroRandom <= 0.7)
                            return 30; //Para acumulada de 0.7 => 30 Minutos.
                        else
                            return 40; //Para acumulada de 1 => 40 Minutos
                    }

                }
            }/*Fin Caso 0*/

            /*Cuadro Clinico= Medio*/
            case 1: {
                if ((tiempoActual >= 420 && tiempoActual <= 540) || (tiempoActual >= 1200 && tiempoActual <= 1320)) {
                    if (numeroRandom <= 0.35)
                        return 40; //Para acumulada de 0.35 => 40 Minutos.
                    else
                        return 50; //Para acumulada de 1 => 50 Minutos.
                } else {
                    if (numeroRandom <= 0.25)
                        return 60; //Para acumulada de 0.25 => 60 Minutos.
                    else
                        return 70; //Para acumulada de 1 => 70 Minutos.
                }
            }/*Fin Caso 1*/

            /*Cuadro Clinico= Grave*/
            case 2: {
                {
                    if ((tiempoActual >= 420 && tiempoActual <= 540) || (tiempoActual >= 1200 && tiempoActual <= 1320)) {
                        if (numeroRandom <= 0.4)
                            return 60; //Para acumulada de 0.4 => 60 Minutos.
                        else
                            return 90; //Para acumulada de 1 => 90 Minutos.
                    } else {
                        if (numeroRandom <= 0.5)
                            return 120; //Para acumulada de 0.5 => 120 Minutos.
                        else
                            return 180; //Para acumulada de 1 => 180 Minutos.
                    }
                }
            }/*Fin Caso 2*/

        }/*FIN SWITCH*/

        return 0; //*Para que no tire error de que falta return*/
    }

    public static double varAleaExp(double media) {


        double lmbd = 1 / media;
        double x = (-1 / lmbd) * (Math.log(1 - new Random().nextDouble()));

        return x;
    }

    public static double varAleaUniforme(double a, double b) {

        double x = a + (b - a) * new Random().nextDouble();
        return x;
    }

    public static double varNormal(double desviacionEstd, double media) {
        double mediaRandom = 0.0;

        double cantVar = 12;

        for (int i = 0; i < cantVar; i++) {
            mediaRandom += new Random().nextDouble();
        }

        double x = ((mediaRandom - (cantVar/2)) / (cantVar / 12.0)) * Math.sqrt(desviacionEstd) + media; //0.5 es la media de las variables que estan entre 0 y 1, y el 1/12 es la desviacion estandar de los Random que ya viene calculada.

        return x;
    }

    public static double getTiempoDuracionServicio(byte tipo) {

        switch (tipo) {
            //Cuadro Clinico LEVE: Exponencial
            case 0: {
                return varAleaExp(30.0); //Retorna minutos, 30 es la media de la consigna.
            }

            //Cuadro Clinico Medio: Uniforme
            case 1: {
                return varAleaUniforme(10, 20); //10 y 20 es el intervalo de la consigna.
            }

            //Cuadro Clinico GRAVE: Normal
            case 2: {
                return varNormal(30,120); //Por consigna Media = 120, Desv.Estd=30
            }
        }

        return 0;
    }
}
