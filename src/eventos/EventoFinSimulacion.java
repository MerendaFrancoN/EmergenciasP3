package eventos;

import fel.Queue;
import hospital.Servidor;

public class EventoFinSimulacion extends Evento {

    public EventoFinSimulacion(float tiempo) {
        super((byte) 2, tiempo, new Paciente(-1, -1));
    }
    /*
    El tiempo de arribo va con un valor inválido y se le pasa como parametro 'tiempo'
    el tiempo de fin de simulacion, que para una semana son 10080 minutos.
    */

    public void planificarEvento(Servidor servidor, Queue queue) {
        /* Cuando termina la simulacion no se generan mas eventos.
        Se debe arrojar por pantalla los resultados de la simulacion.
        */
    }
}
