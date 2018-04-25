package fel;

import eventos.Paciente;

import java.util.LinkedList;

public class Queue {

    private int cantidadItems;
    private LinkedList<Paciente> cola;

    public Queue() {
        cola = new LinkedList<>();
        cantidadItems = 0;
    }

    public void insertarCola(Paciente paciente) {
        this.cola.addLast(paciente);
        this.cantidadItems++;
    }

    public Paciente suprimirCola() {
        this.cantidadItems--;
        return this.cola.removeFirst();
    }

    public boolean HayCola() {
        return this.cantidadItems > 0;
    }

}
