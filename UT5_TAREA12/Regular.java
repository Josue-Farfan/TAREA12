package UT5_TAREA12;
public class Regular extends Vuelo {

    private int plazasLibres;

    public Regular(String destino, String modeloAvion, int plazas, int plazasLibres) {
        super(destino, modeloAvion, plazas);
        this.plazasLibres = plazasLibres;
    }

    public int getPlazasLibres() {
        return plazasLibres;
    }

    public void setPlazasLibres(int plazasLibres) {
        this.plazasLibres = plazasLibres;
    }

    @Override
    public String toString() {
        return "VUELO REGULAR\n-------------\nDestino: " + getDestino() + "\nModelo de avión: " + getModeloAvion()
                + "\nPlazas totales: " + getPlazas() + "\nPlazas libres: " + plazasLibres+"\n";
    }
}

