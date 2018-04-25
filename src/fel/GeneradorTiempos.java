package fel;

import java.util.Random;


public class GeneradorTiempos {

    private static Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static int getTiempoEntreArribos(float tiempoActual,byte tipo) {
        /*Considero el tiempo actual ya que si se encuentra entre las 7-9 o 20-22 la cantidad de arribos es mayor*/

        double numeroRandom = random.nextDouble();
        switch (tipo)
        {
            /*Cuadro Clinico= Leve*/
            case 0:{
                if( (tiempoActual>=7 && tiempoActual<=9) || (tiempoActual>=20 && tiempoActual<=22) ) {
                    if (numeroRandom <= 0.5)
                        return 10; //Para acumulada de 0.5 => 10 Minutos.
                    else {
                        if (numeroRandom <= 0.85)
                            return 20; //Para acumulada de 0.85 => 20 Minutos.
                        else
                            return 30; //Para acumulada de 1 => 30 Minutos
                    }
                }
                else{
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
            case 1:{
                if( (tiempoActual>=7 && tiempoActual<=9) || (tiempoActual>=20 && tiempoActual<=22) ) {
                    if (numeroRandom <= 0.35)
                        return 40; //Para acumulada de 0.35 => 40 Minutos.
                    else
                        return 50; //Para acumulada de 1 => 50 Minutos.
                }
                else{
                    if (numeroRandom <= 0.25)
                        return 60; //Para acumulada de 0.25 => 60 Minutos.
                    else
                        return 70; //Para acumulada de 1 => 70 Minutos.
                }
            }/*Fin Caso 1*/

            /*Cuadro Clinico= Grave*/
            case 2:{
                {
                    if( (tiempoActual>=7 && tiempoActual<=9) || (tiempoActual>=20 && tiempoActual<=22) ) {
                        if (numeroRandom <= 0.4)
                            return 60; //Para acumulada de 0.4 => 60 Minutos.
                        else
                            return 90; //Para acumulada de 1 => 90 Minutos.
                    }
                    else{
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


    public static int getTiempoDuracionServicio() {
        double numeroRandom = random.nextDouble();
        if (numeroRandom <= 0.5)
            return 3; //Para acumulada de 0.5 => 3 Minutos.
        else {
            if (numeroRandom <= 0.9)
                return 4; //Para acumulada de 0.9 => 4 Minutos.
            else
                return 5; //Para acumulada de 1 => 5 Minutos
        }
    }
}
