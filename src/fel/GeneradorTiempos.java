package fel;

import java.util.Random;


public class GeneradorTiempos {

    private static Random random;

    static {
        random = new Random(System.currentTimeMillis());
    }

    public static int getTiempoEntreArribos() {
        double numeroRandom = random.nextDouble();
        if (numeroRandom <= 0.4)
            return 4; //Para acumulada de 0.4 => 4 Minutos.
        else {
            if (numeroRandom <= 0.7)
                return 5; //Para acumulada de 0.7 => 5 Minutos.
            else
                return 6; //Para acumulada de 1 => 6 Minutos
        }
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
