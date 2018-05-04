package eventos;

import fel.Fel;
import fel.GeneradorTiempos;
import fel.Queue;
import hospital.Servidor;
import hospital.Servidores;

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

        switch (cuadro){

            //Cuadro Clinico= Leve
            case 0:{

                while(servidores.getMedicoResidente().get(i).isOcupado())//Si hay alguno sin estar ocupado significa que i va parar antes del final.
                    i++;


                if(i>=servidores.getMedicoResidente().size()){ //Si estaban todos ocupados llego al final pasandose de size-1, que son las posiciones en la lista.

                    servidores.asignacionCola(cuadro).insertarCola(this.getPaciente()); //Cargo en cola correspondiente
                }
                else { //Habia un servidor desocupado en la posicion i

                    this.getPaciente().setTiempoDuracionServicio((float) GeneradorTiempos.getTiempoDuracionServicio((byte) 0)); //Genero tiempo de duracion

                    EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getPaciente().getTiempoDuracionServicio(),this.getPaciente(),(byte)0);
                    // Insertamos en la Fel el evento de salida
                    Fel.getFel().insertarFel(eventoSalida);

                    // Colecto tiempo ocioso
                    servidores.getMedicoResidente().get(i).setTiempoOcioso(this.getTiempo());

                    // No estaba ocupado y pasa a estarlo
                    servidores.getMedicoResidente().get(i).setOcupado(true);
                }

                Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),(byte)0),(byte)0));

            }

            //Cuadro Clinico= Medio
            case 1:{

                while(servidores.getMedicoGeneral().get(i).isOcupado()){ //Si hay alguno sin estar ocupado significa que i va parar antes del final.
                    i++;
                }

                if(i>=servidores.getMedicoGeneral().size()){ //Si estaban todos ocupados llego al final pasandose de size-1, que son las posiciones en la lista.

                    servidores.asignacionCola(cuadro).insertarCola(this.getPaciente()); //Cargo en cola correspondiente
                }
                else { //Habia un servidor desocupado en la posicion i

                    this.getPaciente().setTiempoDuracionServicio((float) GeneradorTiempos.getTiempoDuracionServicio((byte) 1)); //Genero tiempo de duracion

                    EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getPaciente().getTiempoDuracionServicio(),this.getPaciente(),(byte)1);
                    // Insertamos en la Fel el evento de salida
                    Fel.getFel().insertarFel(eventoSalida);

                    // Colecto tiempo ocioso
                    servidores.getMedicoGeneral().get(i).setTiempoOcioso(this.getTiempo());

                    // No estaba ocupado y pasa a estarlo
                    servidores.getMedicoGeneral().get(i).setOcupado(true);
                }

                Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),(byte)1),(byte)1));
            }

            //Cuadro Clinico= Grave
            case 2:{
                while(servidores.getMedicoGeneral().get(i).isOcupado()){ //Si hay alguno sin estar ocupado significa que i va parar antes del final.
                    i++;
                }

                if(i>=servidores.getMedicoEspecialista().size()){ //Si estaban todos ocupados llego al final pasandose de size-1, que son las posiciones en la lista.

                    servidores.asignacionCola(cuadro).insertarCola(this.getPaciente()); //Cargo en cola correspondiente
                }
                else { //Habia un servidor desocupado en la posicion i

                    this.getPaciente().setTiempoDuracionServicio((float) GeneradorTiempos.getTiempoDuracionServicio((byte) 2)); //Genero tiempo de duracion

                    EventoSalida eventoSalida = new EventoSalida(this.getTiempo() + this.getPaciente().getTiempoDuracionServicio(),this.getPaciente(),(byte)2);
                    // Insertamos en la Fel el evento de salida
                    Fel.getFel().insertarFel(eventoSalida);

                    // Colecto tiempo ocioso
                    servidores.getMedicoEspecialista().get(i).setTiempoOcioso(this.getTiempo());

                    // No estaba ocupado y pasa a estarlo
                    servidores.getMedicoEspecialista().get(i).setOcupado(true);
                }

                Fel.getFel().insertarFel(new EventoArribo(this.getTiempo() + GeneradorTiempos.getTiempoEntreArribos(this.getTiempo(),(byte)2),(byte)2));
            }
        }
    }

}
