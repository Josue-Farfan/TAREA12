package UT5_TAREA12;

import java.util.Comparator;

public class regularPorPlazasComparator implements Comparator<Regular>
{
    @Override
    public int compare(Regular o1, Regular o2) {
        return Integer.compare(o2.getPlazasLibres(), o1.getPlazasLibres());
    }
}
