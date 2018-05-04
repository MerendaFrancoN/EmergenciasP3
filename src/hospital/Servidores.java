package hospital;

import java.util.LinkedList;

import eventos.Paciente;
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



    public Queue asignacionCola(byte cuadroClinico){ //cola mas corta o primera de todas si son de igual longitud

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
        return null;
    }

    /*
    Devuelve el servidor que contiene cola a retirar item más próximo a ser evaluado por servidor.
    Luego el servidor puede ser útil a la hora de calcular estadísticas.
     */
    public Servidor retirarCola(byte cuadroClinico){ //Devolver o el servidor que no tiene cola o el que tenga el item de menor tiempo

        /*Como las colas se que estan ordenadas, solo examino el primer item de cada una de ellas y ver
          quien tiene el paciente con menor tiempo*/

        float tiempoPaciente=0;
        int indiceServidor=0;
        int cantidadServidores;
        int i=0; //Variable para moverse entre los indices de las colas
        boolean hayCola=false;

        switch (cuadroClinico){

            case 0:{

                cantidadServidores= this.medicoResidente.size();
                while(this.medicoResidente.get(i).getCola().primerItem() != null && !hayCola){

                    //Es para el caso en que el primer servidor no tenga cola y el segundo si, busca
                    //algun servidor que tenga cola y asignarle el primer item para compararlos con las otras
                    //colas posibles de los otros servidores.

                    hayCola=true;
                    tiempoPaciente=this.medicoResidente.get(i).getCola().primerItem().getTiempoArribo();
                    i++;
                }

                for(i=0;i<cantidadServidores;i++){

                    if(this.medicoResidente.get(i).getCola().primerItem() != null && tiempoPaciente > this.medicoResidente.get(i).getCola().primerItem().getTiempoArribo()) {
                        tiempoPaciente = this.medicoResidente.get(i).getCola().primerItem().getTiempoArribo();
                        indiceServidor=i;
                    }
                }
                return this.medicoResidente.get(indiceServidor); //Devuelvo o bien el primero de los servidores ya que ninguno tiene cola, o el que tiene cola con el item de menor tiempo.
            }

            case 1:{



                cantidadServidores= this.medicoGeneral.size();
                while(i<this.medicoGeneral.size() && this.medicoGeneral.get(i).getCola().primerItem()!=null && !hayCola){ //Es para el caso en que el primer servidor no tenga cola y el segundo si, busca
                    //algun servidor que tenga cola y asignarle el primer item para compararlos con las otras
                    //colas posibles de los otros servidores.
                    hayCola=true;
                    tiempoPaciente=this.medicoGeneral.get(i).getCola().primerItem().getTiempoArribo();
                    i++;
                }

                for(i=0;i<cantidadServidores;i++){

                    if(this.medicoGeneral.get(i).getCola().primerItem() != null && tiempoPaciente > this.medicoGeneral.get(i).getCola().primerItem().getTiempoArribo()) {
                        tiempoPaciente = this.medicoGeneral.get(i).getCola().primerItem().getTiempoArribo();
                        indiceServidor=i;
                    }
                }
                return this.medicoGeneral.get(indiceServidor); //Devuelvo o bien el primero de los servidores ya que ninguno tiene cola, o el que tiene cola con el item de menor tiempo.
            }

            case 2:{

                cantidadServidores= this.medicoEspecialista.size();
                while(this.medicoEspecialista.get(i).getCola().primerItem()!=null && !hayCola){ //Es para el caso en que el primer servidor no tenga cola y el segundo si, busca
                    //algun servidor que tenga cola y asignarle el primer item para compararlos con las otras
                    //colas posibles de los otros servidores.
                    hayCola=true;
                    tiempoPaciente=this.medicoEspecialista.get(i).getCola().primerItem().getTiempoArribo();
                    i++;
                }

                for(i=0;i<cantidadServidores;i++){

                    if(this.medicoEspecialista.get(i).getCola().primerItem() != null && tiempoPaciente > this.medicoEspecialista.get(i).getCola().primerItem().getTiempoArribo()) {
                        tiempoPaciente = this.medicoEspecialista.get(i).getCola().primerItem().getTiempoArribo();
                        indiceServidor=i;
                    }
                }
                return this.medicoEspecialista.get(indiceServidor); //Devuelvo o bien el primero de los servidores ya que ninguno tiene cola, o el que tiene cola con el item de menor tiempo.
            }
        }
        return null;
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
