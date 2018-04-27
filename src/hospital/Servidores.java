package hospital;

import java.util.LinkedList;

public class Servidores {

/*TODO: Metodo de deteccion de colas y asignacion del paciente a las mismas. Calculo Estadisticas*/
    private LinkedList<Servidor> medicoResidente;
    private LinkedList<Servidor> medicoGeneral;
    private LinkedList<Servidor> medicoEspecialista;

    public Servidores(){
        this.medicoEspecialista=new LinkedList<>();
        this.medicoGeneral=new LinkedList<>();
        this.medicoResidente=new LinkedList<>();
    }
    public void inicializarServidores(int cantResidente, int cantGeneral, int cantEsp ){
        for(int i;i<cantResidente;i++){
            this.medicoResidente.add(new Servidor());
        }
        for(int i;i<cantGeneral;i++){
            this.medicoGeneral.add(new Servidor());
        }
        for(int i;i<cantEsp;i++){
            this.medicoEspecialista.add(new Servidor());
        }
    }

}
