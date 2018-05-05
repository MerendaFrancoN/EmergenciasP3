package hospital;

public class Estadisticas {

    // Objetivos
    //      Tiempo medio de transito de los pacientes de cada tipo (3 valores)
    //      Tiempo medio de espera en cola de cada tipo (3 valores)
    //      Porcentaje de tiempo ocioso de cada servidor (5 valores, uno por cada servidor)

    // Guardamos las estadisticas de tiempo en cola en la clase de Estadisticas, no en Paciente.

    private static int[] cantPacientes = new int[3];
    private static double[] tiempoEsperaCola = new double[3];
    private static double[] tiempoTransito = new double[3];
    private static double[] tiempoEsperaColaMedia = new double[3];
    private static double[] tiempoTransitoMedio = new double[3];

    public static void actualizarCantidadPacientes(byte tipo) {
        // Suma 1 a la cantidad de Pacientes del tipo indicado.
        cantPacientes[tipo]++;
    }

    public static int getCantPacientes(byte tipo) {
        // Retorna la cantidad de pacientes del tipo indicado
        return cantPacientes[tipo];
    }

    public static int getCantPacientes() {
        // Retorna todos los pacientes que tuvo el sistema.
        return (cantPacientes[0] + cantPacientes[1] + cantPacientes[2]);
    }

    public static void actualizarTiempoEsperaCola(byte tipo, float tiempoActual, float tiempoDuracionServicio, float tiempoArribo) {
        // El tiempo de espera en cola total es igual a la sumatoria de todos los tiempos de espera en cola.
        // Cada tiempo de cola se calcula con el tiempo actual,
        // que seria el tiempo en el que termina de atenderse, menos el tiempo en el que llego menos el tiempo de duracion de servicio.
        tiempoEsperaCola[tipo] += tiempoActual - (tiempoDuracionServicio + tiempoArribo);
        // El valor sumado deberia ser mayor o igual a 0. tiempoActual debe ser siempre igual o mayor que la suma del tiempoDuracionServicio y tiempoArribo.
    }

    public static void actualizarTiempoTransito(byte tipo, float tiempoActual, float tiempoArribo) {
        // El tiempo de transito total es igual a la sumatoria de todos los tiempos de transito.
        // Cada tiempo de transito se calcula con el tiempo actual,
        // que seria el tiempo en que termina de atenderse, menos el tiempo en que llega.
        tiempoTransito[tipo] += tiempoActual - tiempoArribo;
    }

    public static void calcularEstadisticas(Servidores servidores, float tiempoFinSimulacion) {
        // Calculamos tiempo de espera medio por cada tipo de caso
        for (int i = 0; i < 3; i++) {
            if (tiempoEsperaCola[i] != 0)
                // Se hacen controles para que no de 0.
                tiempoEsperaColaMedia[i] = tiempoEsperaCola[i] / (float) cantPacientes[i];
        }


        // Calculamos el tiempo medio de transito por paciente
        for (int i = 0; i < 3; i++) {
            tiempoTransitoMedio[i] = tiempoTransito[i] / (float) cantPacientes[i];
        }

        // Calculamos el tiempo ocioso por Servidor.
        for (int i = 0; i < 3; i++) {
            for (Servidor x : servidores.listaServidoresPorTipo((byte) i)) {
                x.calcularPorcentajeTiempoOcioso(tiempoFinSimulacion);
            }
        }
    }

    public static void mostrarResutlados(Servidores servidores, float tiempoFinSimulacion) {
        System.out.println("##############################################");
        System.out.println("#######  RESULTADOS DE LA SIMULACION  ########");
        System.out.println("##############################################");
        System.out.println();
        System.out.println("Tiempo total de simulacion: " + tiempoFinSimulacion);
        System.out.println("Cantidad total de pacientes " + getCantPacientes());
        System.out.println();
        for (int i = 0; i < 3; i++) {
            switch (i){
                case 0:
                    System.out.println("Datos de tipo Leve:");
                    break;
                case 1:
                    System.out.println("Datos de tipo Medio:");
                    break;
                case 2:
                    System.out.println("Datos de tipo Grave:");
                    break;
            }
            System.out.println("Tiempo medio de espera en Cola: " + tiempoEsperaColaMedia[i]);
            System.out.println("Tiempo medio de transito: " + tiempoTransitoMedio[i]);
            System.out.println("Porcentaje de tiempo ocioso de los medicos:");
            for (Servidor x : servidores.listaServidoresPorTipo((byte) i)) {
                System.out.printf("   Valor: %.2f\n", x.getPorcentajeTiempoOcioso() * 100);
            }
            System.out.println("----------------------------------------");
        }
    }
}
