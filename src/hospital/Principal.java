package hospital;

import eventos.Evento;
import eventos.EventoArribo;
import eventos.EventoFinSimulacion;
import eventos.Paciente;
import fel.Fel;
import fel.Queue;

public class Principal {

    public static void main(String[] args) {


        boolean finSimulacion = false;
        Evento actual;
        float tiempoSimulacion;

        // Creo la Fel, Queue y Servidor
        Fel fel = Fel.getFel();
        Queue queue = new Queue();
        Servidores servidores = new Servidores();
        servidores.inicializarServidores(2,1,2);

        // Inicializamos el tiempo de la simulacion.
        tiempoSimulacion = 0;
        // Creo evento de Fin de Simulacion y lo cargo a la FEL, con 'tiempo' igual al tiempo que se desea ejecutar la simulacion.
        fel.insertarFel(new EventoFinSimulacion(10080)); // 1 Semana = 10080 Minutos

        // Creo primer evento de Arribo
        fel.insertarFel(new EventoArribo(tiempoSimulacion));

        // Mostrar la lista para hacer Debug
        // fel.mostrarFel();

        while (!finSimulacion) {
            // Actual toma el primer elemento del la Fel, el cual es el mas cercano en el tiempo.
            actual = fel.suprimirFel();
            // Actualizamos el tiempo de Simulacion.
            tiempoSimulacion = actual.getTiempo();

            // Planificamos el evento proximo a partir de 'actual'
            actual.planificarEvento(servidor, queue);

            if (actual.getTipo() == 2) {
                // Si el evento es de 'FinSimulacion' terminar con el loop.
                finSimulacion = true;
            }
            // Mostrar la lista para hacer Debug
            // fel.mostrarFel();
        }
        // Muestra de resultados

        Estadisticas.calcularEstadisticas(Paciente.getTiempoEsperaCola(), Paciente.getTiempoTransito(), servidor.getTiempoOcioso(), tiempoSimulacion, Paciente.getCantidadItems());

        System.out.println("##############################################");
        System.out.println("#######  RESULTADOS DE LA SIMULACION  ########");
        System.out.println("##############################################");
        System.out.println("\n**Tiempo medio de espera de los pacientes:  ");
        System.out.println("    * " + Estadisticas.tiempoEsperaMedio + " min.");
        System.out.println("\n**Porcentaje de tiempo ocioso del médico:     ");
        System.out.printf("    * %%%.2f\n", (Estadisticas.porcentajeTiempoOcioso * 100));
        System.out.println("\n**Tiempo medio de tiempo de tránsito:    ");
        System.out.printf("    * %.2f min.\n", Estadisticas.tiempoMedioTransito);


    }

}
