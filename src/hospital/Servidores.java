package hospital;

import java.util.LinkedList;

public class Servidores {

/*TODO: Metodo de deteccion de colas y asignacion del paciente a las mismas. Calculo Estadisticas*/
    private LinkedList<Servidor> medicoResidente;
    private LinkedList<Servidor> medicoGeneral;
    private LinkedList<Servidor> medicoEspecialista;

    public Servidores(){
        this.setMedicoEspecialista(new LinkedList<>());
        this.setMedicoGeneral(new LinkedList<>());
        this.setMedicoResidente(new LinkedList<>());
    }
    public void inicializarServidores(int cantResidente, int cantGeneral, int cantEsp ){
        for(int i=0;i<cantResidente;i++){
            this.getMedicoResidente().add(new Servidor());
        }
        for(int i=0;i<cantGeneral;i++){
            this.getMedicoGeneral().add(new Servidor());
        }
        for(int i=0;i<cantEsp;i++){
            this.getMedicoEspecialista().add(new Servidor());
        }
    }

    public LinkedList<Servidor> getMedicoResidente() {
        return medicoResidente;
    }

    public void setMedicoResidente(LinkedList<Servidor> medicoResidente) {
        this.medicoResidente = medicoResidente;
    }

    public LinkedList<Servidor> getMedicoGeneral() {
        return medicoGeneral;
    }

    public void setMedicoGeneral(LinkedList<Servidor> medicoGeneral) {
        this.medicoGeneral = medicoGeneral;
    }

    public LinkedList<Servidor> getMedicoEspecialista() {
        return medicoEspecialista;
    }

    public void setMedicoEspecialista(LinkedList<Servidor> medicoEspecialista) {
        this.medicoEspecialista = medicoEspecialista;
    }


}
