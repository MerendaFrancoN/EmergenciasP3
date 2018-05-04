package hospital;

public class Estadisticas {
    // todo: implementar el calculo de estadisticas finales.
    /*
    Con este propósito, se ha tomado la dedición de realizar un estudio de simulación para
    evaluar el desempeño del servicio de guardia médica del hospital, ya que es un servicio
    crítico y ningún detalle puede ser dejado al azar.

    Además de lo mencionado anteriormente, los directivos desean conocer el tiempo
    medio de tránsito de los pacientes en el interior del hospital, el tiempo medio
    de espera en cola de cada tipo de atención que requieren los pacientes, el tiempo medio
    de ociosidad de cada especialista médico.
     */

    // Guardamos las estadisticas de tiempo en cola en la clase de Estadisticas, no en Paciente.
    private static double tiempoEsperaColaTipo0, tiempoEsperaColaTipo1, tiempoEsperaColaTipo2;
    private static double tiempoTransitoTipo0,tiempoTransitoTipo1, tiempoTransitoTipo2;

    public void calcularEstadisticas(Servidores servidores, float tiempoFinSimulacion, int cantPacientes){

    }

}
