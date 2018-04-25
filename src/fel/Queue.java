package fel;

import eventos.Item;

import java.util.LinkedList;

public class Queue {

    private int cantidadItems;
    private LinkedList<Item> cola;

    public Queue() {
        cola = new LinkedList<>();
        cantidadItems = 0;
    }

    public void insertarCola(Item item) {
        this.cola.addLast(item);
        this.cantidadItems++;
    }

    public Item suprimirCola() {
        this.cantidadItems--;
        return this.cola.removeFirst();
    }

    public boolean HayCola() {
        return this.cantidadItems > 0;
    }

}
