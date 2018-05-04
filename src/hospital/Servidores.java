package hospital;

import eventos.Paciente;
import fel.Queue;

import java.util.LinkedList;

public class Servidores {

    private LinkedList<Servidor> medicoResidente;
    private LinkedList<Servidor> medicoGeneral;
    private LinkedList<Servidor> medicoEspecialista;

    public Servidores() {
        this.medicoEspecialista = new LinkedList<>();
        this.medicoGeneral = new LinkedList<>();
        this.medicoResidente = new LinkedList<>();
    }

    public void inicializarServidores(int cantResidente, int cantGeneral, int cantEsp) {
        for (int i = 0; i < cantResidente; i++) {
            this.medicoResidente.add(new Servidor());
        }
        for (int i = 0; i < cantGeneral; i++) {
            this.medicoGeneral.add(new Servidor());
        }
        for (int i = 0; i < cantEsp; i++) {
            this.medicoEspecialista.add(new Servidor());
        }
    }

    /**
     * @param cuadroClinico tipo de Cuadro Clinico del que se quiere conocer la cola mas corta.
     * @return Queue del servidor del Cuadro Clinico indicado mas corta, o la primera en caso de que sean todos iguales.
     */
    public Queue asignacionCola(byte cuadroClinico) {

        Queue colaAsignada = null;
        // La cola que retorna.
        int colaMasCorta = 1000000000;
        // Valor para comparar el tamaño de cola
        LinkedList<Servidor> listaServidores = null;
        // Lista con todos los servidores del tipo indicado.

        switch (cuadroClinico) {
            case 0: {
                listaServidores = this.medicoResidente;
                break;
            }
            case 1: {
                listaServidores = this.medicoGeneral;
                break;
            }
            case 2: {
                listaServidores = this.medicoEspecialista;
                break;
            }
        }

        for (Servidor x : listaServidores) {
            if (x.getCola().getCantidadItems() < colaMasCorta) {
                colaAsignada = x.getCola();
                colaMasCorta = x.getCola().getCantidadItems();
            }
        }
        return colaAsignada;
        // Podria hacerse un control por retorno como null.
    }

    /**
     * @param cuadroClinico Cuadro Clinico del que queremos ver si estan o no los servidores ocupados
     * @return Verdadero en caso de que todos los servidores esten ocupados, falso en caso de que encuentre por lo menos uno servidor del tipo indicado como libre.
     */
    public Boolean estanOcupados(byte cuadroClinico) {
        LinkedList<Servidor> listaServidores = null;

        switch (cuadroClinico) {
            case 0: {
                listaServidores = this.medicoResidente;
                break;
            }
            case 1: {
                listaServidores = this.medicoGeneral;
                break;
            }
            case 2: {
                listaServidores = this.medicoEspecialista;
                break;
            }
        }

        for (Servidor x : listaServidores) {
            if (!x.isOcupado()) {
                return false;
            }
        }
        return true;
    }

    public Servidor getServidorDesocupado(byte cuadroClinico) {
        LinkedList<Servidor> listaServidores = null;

        switch (cuadroClinico) {
            case 0: {
                listaServidores = this.medicoResidente;
                break;
            }
            case 1: {
                listaServidores = this.medicoGeneral;
                break;
            }
            case 2: {
                listaServidores = this.medicoEspecialista;
                break;
            }
        }

        for (Servidor x : listaServidores) {
            if (!x.isOcupado()) {
                return x;
            }
        }
        return null;
    }

    public Servidor getServidorConPaciente(Paciente x) {
        LinkedList<Servidor> listaServidores = null;

        switch (x.getCuadroClinico()) {
            case 0: {
                listaServidores = this.medicoResidente;
                break;
            }
            case 1: {
                listaServidores = this.medicoGeneral;
                break;
            }
            case 2: {
                listaServidores = this.medicoEspecialista;
                break;
            }
        }

        for (Servidor y : listaServidores) {
            if (y.getPaciente() == x) {
                return y;
            }
        }
        return null;
    }
}
