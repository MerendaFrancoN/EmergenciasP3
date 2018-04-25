package hospital;

public class Estadisticas {
    static float tiempoEsperaMedio = 0;
    static float porcentajeTiempoOcioso = 0;
    static float tiempoMedioTransito = 0;

    // Ver donde y como implementar el calculo final.
    public static void calcularEstadisticas(float tiempoEsperaCola, float tiempoTransito, float tiempoOcioso,
                                            float tiempoFinSimulacion, int cantidadItems) {

        /* Carteles que luego se eliminan, es para controlar si está funcionando bien.
        System.out.println("Tiempo Espera cola = " + tiempoEsperaCola);
        System.out.println("Tiempo Transito = " + tiempoTransito);
        System.out.println("Tiempo Ocioso = " + tiempoOcioso);
        System.out.println("Tiempo fin Simulacion = " + tiempoFinSimulacion);
        System.out.println("Cantidad de items = " + cantidadItems);
        */

        // Tiempo espera medio por paciente
        // Control para evitar tiempos invalidos
        if (tiempoEsperaCola != 0)
            tiempoEsperaMedio = tiempoEsperaCola / ((float) cantidadItems);
        // Porcentaje de tiempo Ocioso del médico
        porcentajeTiempoOcioso = (tiempoOcioso / tiempoFinSimulacion);
        // Tiempo medio de tránsito por paciente
        tiempoMedioTransito = tiempoTransito / ((float) cantidadItems);

    }
}
