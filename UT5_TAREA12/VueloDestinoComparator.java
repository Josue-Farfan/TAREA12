package UT5_TAREA12;

import java.util.Comparator;

public class VueloDestinoComparator implements Comparator<Vuelo> {
    @Override
    public int compare(Vuelo o1, Vuelo o2) {
        return o2.getDestino().compareTo(o1.getDestino());
    }
}
