package eventos;

import fel.Queue;
import hospital.Servidor;
import hospital.Servidores;

public abstract class Evento implements Comparable<Evento> {

    private byte tipo;
    /*
    Codificacion
        0: Arribo
        1: Fin de Servicio
        2: Fin de Simulacion
    */

    private byte cuadroClinico; //Variable para poder determinar que tipo de evento y gravedad es.
    private float tiempo;
    // Considerese el 'clock'
    private Paciente paciente;

    public Evento(byte tipo, float tiempo, Paciente paciente,byte cuadroClinico) {
        this.tiempo = tiempo;
        this.paciente = paciente;
        this.tipo = tipo;
        this.setCuadroClinico(cuadroClinico);
    }

    public byte getTipo() {
        return tipo;
    }


    public float getTiempo() {
        return tiempo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    // Implementa la planificacion de eventos.
    public abstract void planificarEvento(Servidores servidor, Queue queue,byte cuadro);

    @Override
    public int compareTo(Evento o) {
        //Ordenar por menor tiempo
        return Float.compare(this.getTiempo(), o.getTiempo());
    }


    public byte getCuadroClinico() {
        return cuadroClinico;
    }

    public void setCuadroClinico(byte cuadroClinico) {
        this.cuadroClinico = cuadroClinico;
    }
}
