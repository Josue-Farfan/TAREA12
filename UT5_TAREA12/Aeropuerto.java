package UT5_TAREA12;
import java.util.*;

public class Aeropuerto {

	private Map<String, List<Vuelo>> vuelos;

	public Aeropuerto() {
		vuelos = new HashMap<>();
	}

	/**
	 * A�ade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
	 * no estuviese ya introducido, si la aerolinea no existiese se a�ade tanto la
	 * aerolinea como el vuelo.
	 */
	public void addVuelo(String aerolinea, Vuelo vuelo) {
		// Si la aerolínea no existe en el mapa, la añadimos
		if (!vuelos.containsKey(vuelo)) {
			vuelos.put(aerolinea, new ArrayList<>());
		}
		// Obtenemos la lista de vuelos de la aerolínea
		List<Vuelo> listVuelos = vuelos.get(aerolinea);

		// Si el vuelo no está en la lista, lo añadimos
		if (!listVuelos.contains(vuelo)){
			listVuelos.add(vuelo);
		}

	}

	/**
	 * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
	 * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
	 */
	public void ordenAerolineasAlfabetico() {

		List<String> aereolineasOrdenadas = new ArrayList<>(vuelos.keySet());
		Collections.sort(aereolineasOrdenadas);

		for (String aerolinea: aereolineasOrdenadas) {
		List<Vuelo> vuelosAereolineas = vuelos.get(aerolinea);
		Collections.sort(vuelosAereolineas,new VueloDestinoComparator());
		
			System.out.println(aerolinea+ " ");
			for (Vuelo vuelo: vuelosAereolineas) {
				System.out.println("" +vuelo.toString());
			}
		}

		/*
		for (String aereolinea: vuelos.keySet()) {
			List<Vuelo> listaVuelo = vuelos.get(aereolinea);
			Collections.sort(listaVuelo, new Comparator<Vuelo>(){
				public int compare(Vuelo v1, Vuelo v2){
					return v1.getDestino().compareTo(v2.getDestino());
				}
			}) ;
			System.out.println("Aerolinea: "+aereolinea);
			for (Vuelo vuelo: listaVuelo) {
				System.out.println(vuelo.toString());
			}
		}*/
	}

	/**
	 * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
	 * visualizaran de mayor a menor segun el numero de plazas
	 * 
	 * @param aerolinea
	 *            Aerolinea de la que se imprimiran los vuelos regulares
	 */
	public void regularPorPlazas(String aerolinea) {
		if (vuelos.containsKey(aerolinea)){
			List<Vuelo> listaVuelos = vuelos.get(aerolinea);
			List<Regular> listaRegulares = new ArrayList<>();

			for (Vuelo vuelo: listaVuelos){
				if (vuelo instanceof Regular){
					listaRegulares.add((Regular) vuelo);
				}
			}
			Collections.sort(listaRegulares, new regularPorPlazasComparator());

			System.out.println("Vuelos Regulados de la Aereolinea"+ aerolinea+" ordenados por numero de plazas: ");

			for (Regular r: listaRegulares) {
				System.out.println(r.toString());
			}
		}else {
			System.out.println("La Aereolinea "+ aerolinea +" no tiene vuelos registrados");
		}
	}

	/**
	 * Devuelve una lista con vuelos regulares con plazas libres
	 * 
	 * @return aerolina Aerolina del avion charter con m�s capacidad
	 */
	public List<Vuelo> plazasLibres() {
	List<Vuelo> vuelosConPlazasLibres= new ArrayList<>();

		for (List<Vuelo> listaVuelos : vuelos.values()) {
			for (Vuelo v: listaVuelos) {
				if (v instanceof Regular){
					Regular r = (Regular) v;
					if (((Regular) v).getPlazasLibres()>0){
						vuelosConPlazasLibres.add(r);
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
	 * @param destino
	 *            Destino del que se debe sacar la estadistica
	 */
	public void estadisticaDestino(String destino) {
		Map<String, Integer> aereolina = new HashMap<>();
		for (List<Vuelo> listaVuelos: vuelos.values()) {
			for (Vuelo v: listaVuelos) {
				if (v.getDestino().equals(destino)){

				}
			}
		}
	}

	/**
	 * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
	 * borrados, utiliza un conjunto de entradas
	 * 
	 * @param nifEmpresa
	 * @return numero de vuelos borrados
	 */
	//public int borrarVuelosEmpresa(String nifEmpresa) {AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

	//}

	/**
	 * Imprime la lista de vuelos pasada por parametro
	 * 
	 * @param listaVuelos
	 */
	public void imprimirListaVuelos(List<Vuelo> listaVuelos) {

	}

	/**
	 * Represetacion textual del mapa tal y como se muestra en el enunciado
	 */
	//public String toString() { AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

	//}

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
				}
				else {
					String nifEmpresa = vuelo[4];
					this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
				}
			}

		}

		finally {
			try {
				entrada.close();
			}
			catch (NullPointerException e) {
				System.out.println("Error en IO , no se ha creado el fichero");
			}
		}

	}

}
