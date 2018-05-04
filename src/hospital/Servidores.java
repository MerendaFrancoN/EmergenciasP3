package hospital;

import java.util.LinkedList;
import fel.Queue;

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



    public Queue asignacionCola(byte cuadroClinico){

           Queue colaAsignada; //Variable con la cola a donde asignar el paciente.
           int cantidadServidores; //Variable que contiene la cantidad de servidores por tipo de médico.
           int colaCorta; //Variable que lleva la cantidad de la cola más corta.

           switch (cuadroClinico){
               //Cuadro Leve
               case 0:{
                   cantidadServidores= this.medicoResidente.size();
                   colaCorta=this.medicoResidente.get(0).getCola().getCantidadItems();
                   colaAsignada=this.medicoResidente.get(0).getCola();

                   //Ya revise en i=0, por lo tanto arranco desde i=1
                   for(int i=1;i<cantidadServidores;i++)
                       if (colaCorta > this.medicoResidente.get(i).getCola().getCantidadItems()) {

                            colaCorta = this.medicoResidente.get(i).getCola().getCantidadItems();
                            colaAsignada = this.medicoResidente.get(i).getCola();
                       }
                       return colaAsignada;
               }

               //Cuadro Medio
               case 1:{
                   cantidadServidores= this.medicoGeneral.size();
                   colaCorta=this.medicoGeneral.get(0).getCola().getCantidadItems();
                   colaAsignada=this.medicoGeneral.get(0).getCola();

                   //Ya revise en i=0, por lo tanto arranco desde i=1
                   for(int i=1;i<cantidadServidores;i++)
                       if (colaCorta > this.medicoGeneral.get(i).getCola().getCantidadItems()) {

                           colaCorta = this.medicoGeneral.get(i).getCola().getCantidadItems();
                           colaAsignada = this.medicoGeneral.get(i).getCola();
                       }
                   return colaAsignada;
               }

               //Cuadro Grave
               case 2:{
                   cantidadServidores= this.medicoEspecialista.size();
                   colaCorta=this.medicoEspecialista.get(0).getCola().getCantidadItems();
                   colaAsignada=this.medicoEspecialista.get(0).getCola();

                   //Ya revise en i=0, por lo tanto arranco desde i=1
                   for(int i=1;i<cantidadServidores;i++)
                       if (colaCorta > this.medicoEspecialista.get(i).getCola().getCantidadItems()) {

                           colaCorta = this.medicoEspecialista.get(i).getCola().getCantidadItems();
                           colaAsignada = this.medicoEspecialista.get(i).getCola();
                       }
                   return colaAsignada;
               }
           }
        return new Queue(); //Solo decorativo ya que no cumple ninguna funcion solo para que no muestre missing return statement
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
