package eventos;

public class Paciente {
    private static float tiempoEsperaCola = 0;
    private static float tiempoTransito = 0;
    private static int cantidadItems = 0;

    private int numero;
    private float tiempoArribo;
    private float tiempoDuracionServicio;

    public Paciente(int numero, float tiempoArribo) {
        this.numero = numero;
        this.tiempoArribo = tiempoArribo;
        this.tiempoDuracionServicio = 0;
    }

    public static int getCantidadItems() {
        return cantidadItems;
    }

    public static void setCantidadItems(int cantidadItems) {
        Paciente.cantidadItems = cantidadItems;
    }

    public static float getTiempoEsperaCola() {
        return tiempoEsperaCola;
    }

    public static void setTiempoEsperaCola(float tiempoActual, float tiempoDuracionServicio, float tiempoArribo) {
        // El tiempo de espera en cola total es igual a la sumatoria de todos los tiempos de espera en cola.
        // Cada tiempo de cola se calcula con el tiempo actual,
        // que seria el tiempo en el que termina de atenderse, menos el tiempo en el que llego menos el tiempo de duracion de servicio.
        tiempoEsperaCola += tiempoActual - (tiempoDuracionServicio + tiempoArribo);
        // El valor sumado deberia ser mayor o igual a 0. tiempoActual debe ser siempre igual o mayor que la suma del tiempoDuracionServicio y tiempoArribo.
    }

    public static float getTiempoTransito() {
        return tiempoTransito;
    }

    public static void setTiempoTransito(float tiempoActual, float tiempoArribo) {
        // El tiempo de transito total es igual a la sumatoria de todos los tiempos de transito.
        // Cada tiempo de transito se calcula con el tiempo actual,
        // que seria el tiempo en que termina de atenderse, menos el tiempo en que llega.
        tiempoTransito += tiempoActual - tiempoArribo;
    }

    public int getNumero() {
        return numero;
    }

    public float getTiempoArribo() {
        return tiempoArribo;
    }

    public float getTiempoDuracionServicio() {
        return tiempoDuracionServicio;
    }

    public void setTiempoDuracionServicio(float tiempoDuracionServicio) {
        this.tiempoDuracionServicio = tiempoDuracionServicio;
    }
}
