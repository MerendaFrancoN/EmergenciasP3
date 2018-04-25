package eventos;

import fel.Queue;
import hospital.Servidor;

public abstract class Evento implements Comparable<Evento> {

    private byte tipo;
    /*
    Codificacion
        0: Arribo
        1: Fin de Servicio
        2: Fin de Simulacion
    */
    private float tiempo;
    // Considerese el 'clock'
    private Item item;

    public Evento(byte tipo, float tiempo, Item item) {
        this.tiempo = tiempo;
        this.item = item;
        this.tipo = tipo;
    }

    public byte getTipo() {
        return tipo;
    }

    public float getTiempo() {
        return tiempo;
    }

    public Item getItem() {
        return item;
    }

    // Implementa la planificacion de eventos.
    public abstract void planificarEvento(Servidor servidor, Queue queue);

    @Override
    public int compareTo(Evento o) {
        //Ordenar por menor tiempo
        return Float.compare(this.getTiempo(), o.getTiempo());
    }

}
