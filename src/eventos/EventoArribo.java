package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import hospital.Servidor;

public class EventoArribo extends Evento {

    public EventoArribo(float tiempo) {
        // Considero que inicia en item n°1
        super((byte) 0, tiempo, new Item(Item.getCantidadItems() + 1, tiempo));
        // Actualizo la cantidad de Items.
        Item.setCantidadItems(Item.getCantidadItems() + 1);
    }

    public void planificarEvento(Servidor servidor, Queue queue) {
        /* Planificar el nuevo evento de arribo */

        if (servidor.isOcupado()) {
            queue.insertarCola(this.getItem());
        } else {
            // Si el servidor no estaba ocupado...
            this.getItem().setTiempoDuracionServicio(GeneradorTiempos.getTiempoDuracionServicio());
            // se setea a si mismo el tiempo de Duracion de Servicio...
            // y planifica nueva Salida.
            EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getItem().getTiempoDuracionServicio(),this.getItem());
            // Insertamos en la Fel el evento de salida
            Fel.getFel().insertarFel(eventoSalida);

            // Colecto tiempo ocioso
            servidor.setTiempoOcioso(this.getTiempo());

            // No estaba ocupado y pasa a estarlo
            servidor.setOcupado(true);

        }
        // El evento de Arribo genera otro de arribo, lo insertamos en la Fel.
        Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos()));


    }


}
