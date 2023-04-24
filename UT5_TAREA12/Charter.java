package UT5_TAREA12;
public class Charter extends Vuelo{

    private String nifEmpresaContratadora;

    public Charter(String destino, String moodeloAvion, int plazas, String nifEmpresaContratadora) {
        super(destino, moodeloAvion, plazas);
        this.nifEmpresaContratadora = nifEmpresaContratadora;
    }

    public String getNifEmpresaContratadora() {
        return nifEmpresaContratadora;
    }

    public void setNifEmpresaContratadora(String nifEmpresaContratadora) {
        this.nifEmpresaContratadora = nifEmpresaContratadora;
    }


    public String toString(){
        return "Vuelo CHARTER\nDESTINO: " +getDestino()+ "\nMODELO DE AVION: "+getModeloAvion()
       + "\nPLAZS: "+getPlazas()+ "\nNIF EMPRESA: " +getNifEmpresaContratadora()+"\n";
    }


}
