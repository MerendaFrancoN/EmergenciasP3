package hospital;

import eventos.Paciente;

public class Servidor {
    private Paciente paciente;
    private boolean ocupado;
    private float tiempoOcioso;
    private float tiempoInicioOcio;


    public Servidor() {
        paciente = null;
        // No hay items en el servidor
        ocupado = false;
        // Desocupado
        tiempoOcioso = 0;
        // Tiempo ocioso inical es de 0.
        tiempoInicioOcio = 0;
        // Inicio de Ocio en 0
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public float getTiempoOcioso() {
        return tiempoOcioso;
    }

    /**
     * Calcula y guarda el tiempo ocioso total del servidor
     *
     * @param tiempoActual es el tiempo actual del sistema.
     */
    public void setTiempoOcioso(float tiempoActual) {
        this.tiempoOcioso += (tiempoActual - this.getTiempoInicioOcio());
    }

    public float getTiempoInicioOcio() {
        return tiempoInicioOcio;
    }

    public void setTiempoInicioOcio(float tiempoInicioOcio) {
        this.tiempoInicioOcio = tiempoInicioOcio;
    }
}
