package Talabat;

import java.util.ArrayList;

public class ResturantRepo {

    private static ArrayList<Resturant> resturantlist = new ArrayList<Resturant>();

    public static ArrayList<Resturant> getResturantlist() {
        return resturantlist;
    }

    public static void addResturant(Resturant resturant) {
        resturantlist.add(resturant);
    }

    public static void editResturant(Resturant oldresturant, Resturant newresturant) {
        resturantlist.remove(oldresturant);
        resturantlist.add(newresturant);
    }

    public static void deleteResturant(Resturant cresturant) {
        resturantlist.remove(cresturant);
    }
}
