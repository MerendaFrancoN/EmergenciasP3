package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import hospital.Servidor;
import hospital.Servidores;

import java.util.LinkedList;

public class EventoArribo extends Evento {

    public EventoArribo(float tiempo, byte cuadroClinico) {
        // Considero que inicia en item n°1
        super((byte) 0, tiempo, new Paciente(Paciente.getCantidadItems() + 1,tiempo, cuadroClinico), cuadroClinico);

        // Actualizo la cantidad de Items.
        Paciente.setCantidadItems(Paciente.getCantidadItems() + 1);
    }


    public void planificarEvento(Servidores servidores, byte cuadro) {
        /* Planificar el nuevo evento de arribo */
        int i=0;
        LinkedList<Servidor> listaUtil=null;
        switch(cuadro){
            case 0:{
                listaUtil=servidores.getMedicoResidente();
                break;
            }
            case 1:{
                listaUtil=servidores.getMedicoGeneral();
                break;
            }
            case 2:{
                listaUtil=servidores.getMedicoEspecialista();
                break;
            }
        }

        while(i<listaUtil.size() && listaUtil.get(i).isOcupado()){
            //Si hay alguno sin estar ocupado significa que i va parar antes del final.
            i++;
        }

        if(i>=listaUtil.size()){

            //Si estaban todos ocupados llego al final pasandose de size-1, que son las posiciones en la lista.

            servidores.asignacionCola(cuadro).insertarCola(this.getPaciente()); //Cargo en cola correspondiente
        }
        else{ //Habia un servidor desocupado en la posicion i

            this.getPaciente().setTiempoDuracionServicio((float) GeneradorTiempos.getTiempoDuracionServicio(cuadro)); //Genero tiempo de duracion

            EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getPaciente().getTiempoDuracionServicio(),this.getPaciente(),cuadro);
            // Insertamos en la Fel el evento de salida
            Fel.getFel().insertarFel(eventoSalida);

            // Colecto tiempo ocioso
            listaUtil.get(i).setTiempoOcioso(this.getTiempo());

            // No estaba ocupado y pasa a estarlo
            listaUtil.get(i).setOcupado(true);
        }

        Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),cuadro),cuadro));
    }

}
