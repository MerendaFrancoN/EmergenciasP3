package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import hospital.Servidor;
import hospital.Servidores;

public class EventoSalida extends Evento {

    public EventoSalida(float tiempo, Paciente paciente,byte cuadroClinico) {
        super((byte) 1, tiempo, paciente,cuadroClinico);
    }

    @Override
    public void planificarEvento(Servidores servidores, byte cuadro) {

        Servidor servidorUtil; //Recupero en que servidor estoy trabajando y luego genero evento de salida, coleccionando estadisticas.

        servidorUtil=servidores.retirarCola(cuadro);

        if(servidorUtil!= null){//Hay cola
            Fel.getFel().insertarFel(new EventoSalida(this.getTiempo() + (float)GeneradorTiempos.getTiempoDuracionServicio(cuadro),servidorUtil.getCola().suprimirCola(),cuadro));
        }
        else{ //No hay Cola

            //Marco servidor como no ocupado
            servidorUtil.setOcupado(false);

            //Empezar a contar tiempo de ocio
            servidorUtil.setTiempoInicioOcio(this.getTiempo());
        }

        // Colecto tiempo en espera


        // Colecto tiempo en tránsito
        Paciente.setTiempoTransito(this.getTiempo(), this.getPaciente().getTiempoArribo());



    }
}
