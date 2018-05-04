package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import hospital.Servidor;
import hospital.Servidores;

public class EventoSalida extends Evento {

    public EventoSalida(float tiempo, Paciente paciente) {
        super((byte) 1, tiempo, paciente);
    }

    @Override
    public void planificarEvento(Servidores servidores) {

        // Recuperamos el servidor que contenia el paciente que se esta yendo.
        Servidor servidorActual = servidores.getServidorConPaciente(this.getPaciente());

        if (servidorActual.getCola().hayCola()) {
            // Si el servidor acutal tiene cola tomamos el primer paciente de la cola, y con ese paciente creamos un evento de salida.
            Fel.getFel().insertarFel( new EventoSalida(this.getTiempo() + (float) GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(), this.getPaciente().getCuadroClinico()), servidorActual.getCola().suprimirCola()));
        } else { // Si no hay cola...

            // Marcamos servidor como no ocupado
            servidorActual.setOcupado(false);


            // Empezamos a contar tiempo de ocio
            servidorActual.setTiempoInicioOcio(this.getTiempo());
        }

        // Colecto tiempo en espera
        Paciente.setTiempoEsperaCola(this.getTiempo(), this.getPaciente().getTiempoDuracionServicio(), this.getPaciente().getTiempoArribo());

        // Colecto tiempo en tránsito
        Paciente.setTiempoTransito(this.getTiempo(), this.getPaciente().getTiempoArribo());

    }
}
