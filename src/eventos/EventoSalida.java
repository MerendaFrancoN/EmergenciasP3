package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import hospital.Servidor;

public class EventoSalida extends Evento {

    public EventoSalida(float tiempo, Paciente paciente) {
        super((byte) 1, tiempo, paciente);
    }

    public void planificarEvento(Servidor servidor, Queue queue) {
        // Planificar nuevo evento de salida
        if (queue.HayCola()) {
            // Saco item de la cola y genero evento de salida con itemActual

            Fel.getFel().insertarFel(new EventoSalida(this.getTiempo() + GeneradorTiempos.getTiempoDuracionServicio(), queue.suprimirCola()));

        } else {
            // Si no hay cola, marcar al Servidor como no ocupado.
            servidor.setOcupado(false);

            // Debe empezar a contar el tiempo de ocio.
            servidor.setTiempoInicioOcio(this.getTiempo());

        }

        // Colecto tiempo en espera
        Paciente.setTiempoEsperaCola(this.getTiempo(), this.getPaciente().getTiempoDuracionServicio(), this.getPaciente().getTiempoArribo());

        // Colecto tiempo en tránsito
        Paciente.setTiempoTransito(this.getTiempo(), this.getPaciente().getTiempoArribo());
    }
}
