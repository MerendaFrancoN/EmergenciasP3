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

    //TODO: ARREGLAR CUANDO DEVUELVO SERVIDOR Y EL METODO EN SERVIDORES.
    @Override
    public void planificarEvento(Servidores servidores, byte cuadro) {

        Servidor servidorUtil; //Recupero en que servidor estoy trabajando y luego genero evento de salida, coleccionando estadisticas.

        servidorUtil=servidores.retirarCola(cuadro);

        if(servidorUtil!= null){//Hay cola
            Fel.getFel().insertarFel(new EventoSalida(this.getTiempo() + (float)GeneradorTiempos.getTiempoDuracionServicio(cuadro),servidorUtil.getCola().suprimirCola(),cuadro));
        }
        else{ //No hay Cola

            //Marco servidor como no ocupado
            //servidorUtil.setOcupado(false);

            switch (this.getCuadroClinico()){
                case 0:
                    for(int i=0;i<servidores.getMedicoResidente().size();i++){
                        if(servidores.getMedicoResidente().get(i).getCola().getCantidadItems()==0){

                            servidores.getMedicoResidente().get(i).setOcupado(false);
                            servidores.getMedicoResidente().get(i).setTiempoInicioOcio(this.getTiempo());
                            }
                    }

                case 1:
                    for(int i=0;i<servidores.getMedicoGeneral().size();i++){
                        if(servidores.getMedicoGeneral().get(i).getCola().getCantidadItems()==0){

                        servidores.getMedicoGeneral().get(i).setOcupado(false);
                        servidores.getMedicoGeneral().get(i).setTiempoInicioOcio(this.getTiempo());
                        }
                    }
                case 2:
                    for(int i=0;i<servidores.getMedicoEspecialista().size();i++){
                        if(servidores.getMedicoEspecialista().get(i).getCola().getCantidadItems()==0){

                            servidores.getMedicoEspecialista().get(i).setOcupado(false);
                            servidores.getMedicoEspecialista().get(i).setTiempoInicioOcio(this.getTiempo());
                         }
                    }
                    
            }

            //Empezar a contar tiempo de ocio
        }

        // Colecto tiempo en espera


        // Colecto tiempo en tránsito
        Paciente.setTiempoTransito(this.getTiempo(), this.getPaciente().getTiempoArribo());



    }
}
