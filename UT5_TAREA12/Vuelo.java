package UT5_TAREA12;

import java.util.Objects;

public abstract class Vuelo implements Comparable<Vuelo>{

    private String destino;
    private String modeloAvion;
    private int plazas;

    public Vuelo(String destino, String modeloAvion, int plazas) {
        this.destino = destino;
        this.modeloAvion = modeloAvion;
        this.plazas = plazas;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getModeloAvion() {
        return modeloAvion;
    }

    public void setMoodeloAvion(String modeloAvion) {
        this.modeloAvion = modeloAvion;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vuelo vuelo = (Vuelo) o;

        if (plazas != vuelo.plazas) return false;
        if (!Objects.equals(destino, vuelo.destino)) return false;
        return Objects.equals(modeloAvion, vuelo.modeloAvion);
    }

    @Override
    public int hashCode() {
        int result = destino != null ? destino.hashCode() : 0;
        result = 31 * result + (modeloAvion != null ? modeloAvion.hashCode() : 0);
        result = 31 * result + plazas;
        return result;
    }


    @Override
    public int compareTo(Vuelo o) {
        if (this.destino.compareTo(o.destino) == 0) {
            if (this.modeloAvion.compareTo(o.modeloAvion) == 0){
                if (this.plazas == o.plazas){
                    return 0;
                }
                return (this.plazas- o.plazas);
            }
            return (this.modeloAvion.compareTo(o.modeloAvion));
        }
        return (this.destino.compareTo(o.destino));
    }

    @Override
    public String toString() {
        return "Destino: " + destino +
                ", Modelo de avión: " + modeloAvion +
                ", Plazas: " + plazas;
    }
}
