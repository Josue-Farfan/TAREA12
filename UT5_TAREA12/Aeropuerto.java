package UT5_TAREA12;

import java.util.*;

public class Aeropuerto {

    private Map<String, Set<Vuelo>> vuelos;

    public Aeropuerto() {
        vuelos = new TreeMap<>();
    }

    /**
     * A�ade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
     * no estuviese ya introducido, si la aerolinea no existiese se a�ade tanto la
     * aerolinea como el vuelo.
     */
    public void addVuelo(String aerolinea, Vuelo vuelo) {
        // Contiene este aerolinea y si la tiene añade al vuelo
        if (vuelos.containsKey(aerolinea)) {
            vuelos.get(aerolinea).add(vuelo);
        } else {
            // Si no lo tiene crea una aerolinea
            Set VueloAereolinea = new TreeSet<>();
            VueloAereolinea.add(vuelo);
            vuelos.put(aerolinea, VueloAereolinea);
        }
    }

    /**
     * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
     * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
     */
    public void ordenAerolineasAlfabetico() {
        for (String aereolinea : vuelos.keySet()) {
            System.out.println(aereolinea);
            System.out.println("===========\n");
            for (Vuelo vuelo : vuelos.get(aereolinea)) {
                System.out.println(vuelo);
            }
        }
    }

    /**
     * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
     * visualizaran de mayor a menor segun el numero de plazas
     *
     * @param aerolinea Aerolinea de la que se imprimiran los vuelos regulares
     */


    public void regularPorPlazas(String aerolinea) { // Ordenados por plazas libres
        if (vuelos.containsKey(aerolinea)) {
            Set<Regular> ordenadosPlazas = new TreeSet<>(new regularPorPlazasComparator());

            for (Vuelo vuelo : vuelos.get(aerolinea)) {
                if (vuelo instanceof Regular) {
                    Regular regular = (Regular) vuelo;
                    ordenadosPlazas.add(regular);
                }
            }
            for (Regular regular : ordenadosPlazas) {
                System.out.println(regular);
            }
        }
    }

    /**
     * Devuelve una lista con vuelos regulares con plazas libres
     *
     * @return aerolina Aerolina del avion charter con m�s capacidad
     */
    public List<Vuelo> plazasLibres() {
        List<Vuelo> vuelosConPlazasLibres = new ArrayList<>();

        for (String nombre : vuelos.keySet()) {
            Set<Vuelo> vuelosAsociados = vuelos.get(nombre);
            for (Vuelo vuelo : vuelosAsociados) {
                if (vuelo instanceof Regular) {
                    if (((Regular) vuelo).getPlazasLibres() > 0) {
                        vuelosConPlazasLibres.add(vuelo);
                    }
                }
            }
        }
        return vuelosConPlazasLibres;
    }

    /**
     * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
     * por parametro, ver resultados de ejecucion
     *
     * @param destino Destino del que se debe sacar la estadistica
     */
    public void estadisticaDestino(String destino) {

        for (String aereolinea : vuelos.keySet()) {
            Set<Vuelo> Coleccionvuelo = vuelos.get(aereolinea);
            int a = 0;
            for (Vuelo vuelo : Coleccionvuelo) {
                if (vuelo.getDestino().equals(destino)) {
                    a++;
                }
            }
            System.out.println(a + " de cada " + Coleccionvuelo.size() + " de la eareolinea " + aereolinea + " vuelan a: " + destino);
        }
    }

    /**
     * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
     * borrados, utiliza un conjunto de entradas
     *
     * @param nifEmpresa
     * @return numero de vuelos borrados
     */
    public int borrarVuelosEmpresa(String nifEmpresa) {
        int borrado = 0;

        for (String aereolinea : vuelos.keySet()) {
            Set<Vuelo> informacionVuelo = vuelos.get(aereolinea);
            Iterator<Vuelo> it = informacionVuelo.iterator();
            while (it.hasNext()) {
            Vuelo vuelo = it.next();
                if (vuelo instanceof Charter) {
                    if (((Charter) vuelo).getNifEmpresaContratadora().equals(nifEmpresa)) {
                        it.remove();
                        borrado++;
                    }
                }
            }
        }
        return borrado;
    }

    /**
     * Imprime la lista de vuelos pasada por parametro
     *
     * @param listaVuelos
     */
    public void imprimirListaVuelos(List<Vuelo> listaVuelos) {
        for (Vuelo vuelo : listaVuelos) {
            System.out.println(vuelo);
        }
    }

    /**
     * Represetacion textual del mapa tal y como se muestra en el enunciado
     */
    public String toString() {
        return "s";
    }

    /**
     * Rellena el mapa haciendo uso de un fichero de texto
     */
    public void leerFicheroCursos() {
        Scanner entrada = null;
        try {
            entrada = new Scanner(this.getClass().getResourceAsStream("\\aviones.txt"));
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                int pos = linea.indexOf(":");
                String aerolinea = linea.substring(0, pos);
                String[] vuelo = linea.substring(pos + 1).split(":");
                String destino = vuelo[1];
                String avion = vuelo[2];
                int plazas = Integer.parseInt(vuelo[3].trim());
                if (vuelo[0].equals("R")) {
                    int plazasLibres = Integer.parseInt(vuelo[4].trim());
                    this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres));
                } else {
                    String nifEmpresa = vuelo[4];
                    this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
                }
            }

        } finally {
            try {
                entrada.close();
            } catch (NullPointerException e) {
                System.out.println("Error en IO , no se ha creado el fichero");
            }
        }

    }

}
