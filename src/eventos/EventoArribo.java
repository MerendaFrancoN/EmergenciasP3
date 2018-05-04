package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import hospital.Servidor;
import hospital.Servidores;

public class EventoArribo extends Evento {

    public EventoArribo(float tiempo, byte cuadroClinico) {
        // Considero que inicia en item n°1
        super((byte) 0, tiempo, new Paciente(Paciente.getCantidadItems() + 1, tiempo, cuadroClinico));


        // TODO: Cambiar, porque necesito contadores distintos segun el cuadro clinico.
        // Actualizo la cantidad de Items.
        Paciente.setCantidadItems(Paciente.getCantidadItems() + 1);
    }


    public void planificarEvento(Servidores servidores) {
        // Si todos los servidores estan ocupados, del cuadro dado, lo ponemos en la cola mas corta

        if (servidores.estanOcupados(this.getPaciente().getCuadroClinico())) {
            servidores.asignacionCola(this.getPaciente().getCuadroClinico()).insertarCola(this.getPaciente());
        } else {
            // Si no, lo ponemos en el primer servidor desocupado que encontremos.

            Servidor primerDescoupado = servidores.getServidorDesocupado(this.getPaciente().getCuadroClinico());
            // Recuperamos el servidor desocupado, el cual sabemos que existe.

            this.getPaciente().setTiempoDuracionServicio((float) GeneradorTiempos.getTiempoDuracionServicio(this.getPaciente().getCuadroClinico()));
            // Se setea a si mismo el tiempo de duracion del servicio.

            EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getPaciente().getTiempoDuracionServicio(), this.getPaciente());
            Fel.getFel().insertarFel(eventoSalida);
            // Agregamos a la fel el evento de salida.

            primerDescoupado.setOcupado(true);
            // Lo marcamos como ocupado.


            primerDescoupado.setTiempoOcioso(this.getTiempo());
            // Recolectamos el tiempo ocioso de este servidor.
        }

        // Un paciente de cuadro 'a' genera siempre uno de cuadro 'a'. Recordar a la hora de inicializar el sistema.
        Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(), this.getPaciente().getCuadroClinico()), this.getPaciente().getCuadroClinico()));
    }

}
