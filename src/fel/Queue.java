package fel;

import eventos.Paciente;

import java.util.LinkedList;

public class Queue {

    private int cantidadItems;
    private LinkedList<Paciente> cola;

    public Queue() {
        cola = new LinkedList<>();
        setCantidadItems(0);
    }
    public Paciente primerItem(){
        if(this.cola.size()==0)
            return null;
        else
            return this.cola.get(0);
    }

    public void insertarCola(Paciente paciente) {
        this.cola.addLast(paciente);
        this.setCantidadItems(this.getCantidadItems() + 1);
    }

    public Paciente suprimirCola() {
        this.setCantidadItems(this.getCantidadItems() - 1);
        return this.cola.removeFirst();
    }

    public boolean HayCola() {
        return this.getCantidadItems() > 0;
    }

    public int getCantidadItems() {
        return cantidadItems;
    }

    public void setCantidadItems(int cantidadItems) {
        this.cantidadItems = cantidadItems;
    }
}
