package eventos;

import fel.Queue;
import hospital.Servidor;
import hospital.Servidores;

public class EventoFinSimulacion extends Evento {

    public EventoFinSimulacion(float tiempo) {
        super((byte) 2, tiempo, new Paciente(-1, -1,(byte)0),(byte)0);
    }
    /*
    El tiempo de arribo va con un valor inválido y se le pasa como parametro 'tiempo'
    el tiempo de fin de simulacion, que para una semana son 10080 minutos.
    */
    public void planificarEvento(Servidores servidor, byte cuadro){
        /* Cuando termina la simulacion no se generan mas eventos.
        Se debe arrojar por pantalla los resultados de la simulacion.
        */
    }
}
