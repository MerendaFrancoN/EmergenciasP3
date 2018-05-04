package hospital;

import eventos.Evento;
import eventos.EventoArribo;
import eventos.EventoFinSimulacion;
import fel.Fel;

public class Principal {

    public static void main(String[] args) {

        //TODO: Coleccion de estadísticas

        boolean finSimulacion = false;
        Evento actual;
        float tiempoSimulacion;

        // Creo la Fel e inicializo los  Servidores
        Fel fel = Fel.getFel();

        Servidores servidores = new Servidores();
        servidores.inicializarServidores(2, 1, 2);

        // Inicializamos el tiempo de la simulacion.
        tiempoSimulacion = 0;
        // Creo evento de Fin de Simulacion y lo cargo a la FEL, con 'tiempo' igual al tiempo que se desea ejecutar la simulacion.
        fel.insertarFel(new EventoFinSimulacion(604800)); // 168 Horas = 604800 Minutos

        // Creo primer evento de Arribo de cada tipo
        fel.insertarFel(new EventoArribo(tiempoSimulacion, (byte) 0));

        fel.insertarFel(new EventoArribo(tiempoSimulacion, (byte) 1));

        fel.insertarFel(new EventoArribo(tiempoSimulacion, (byte) 2));

        // Mostrar la lista para hacer Debug
        fel.mostrarFel();

        while (!finSimulacion) {
            // Actual toma el primer elemento del la Fel, el cual es el mas cercano en el tiempo.
            actual = fel.suprimirFel();
            // Actualizamos el tiempo de Simulacion.
            tiempoSimulacion = actual.getTiempo();

            // Planificamos el evento proximo a partir de 'actual'
            actual.planificarEvento(servidores);

            if (actual.getTipo() == 2) {
                // Si el evento es de 'FinSimulacion' terminar con el loop.
                finSimulacion = true;
            }
            // Mostrar la lista para hacer Debug
            fel.mostrarFel();
        }
        // Muestra de resultados
    }
}
