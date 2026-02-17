package Talabat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Resturant {
    private String name;
    private String phone;
    private double rating;
    private String address;
    private  ArrayList<Dish> menu;
    private static int dishCounter = 0 ;
    public Resturant(String name) {
        this();
        setName(name);
    }

    public Resturant(String name, String phone, double rating, String address, ArrayList<Dish> menu) throws IllegalArgumentException {
        try {
            setName(name);
            setPhone(phone);
            setRating(rating);
            setAddress(address);
            setMenu(menu);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    public Resturant() {
        this("", "", 0, "", new ArrayList<Dish>());
    }
    public Resturant(Resturant other) {
        this(other.name, other.phone, other.rating, other.address, other.menu);
    }

    public String showMenu() {
        StringBuilder menulist = new StringBuilder();

        ArrayList<Dish> menu = getMenu();
        menu.stream()
               .collect(Collectors.groupingBy(Dish::getCategory))
               .forEach((category, dishes) -> {
                   menulist.append( "===" ).append(category).append(" ====\n");
                    dishes.stream()
                            .forEach(dish ->
                                    menulist.append("\t").append(this.menu.indexOf(dish)+1+"-\t").append(dish.getName()).append(" ").append(dish.getPrice()).append(" \n")

                            );
               });

        return menulist.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");

        /*ArrayList<Resturant> resturants = ResturantRepo.getResturantlist();
        for(Resturant resturant : resturants) {
            if(name.equals(resturant.getName())) {
                throw  new IllegalArgumentException("Invalid name");
            }
        }*/
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null)
            throw new IllegalArgumentException("Invalid phone");
        this.phone = phone;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0 || rating > 10)
            throw new IllegalArgumentException("Invalid rating");
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null)
            throw new IllegalArgumentException("Invalid address");
        this.address = address;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Dish> menu) {
        if (menu == null)
            throw new IllegalArgumentException("Menu cannot be null");
        this.menu = menu;
    }

    public void addDish(Dish dish) {
        if (dish == null)
            throw new IllegalArgumentException("Dish cannot be null");
            menu.add(dish);
            dishCounter++;
    }

    public void removeDish(Dish dish) {
        getMenu().remove(dish);
        dishCounter--;
    }

    public void addRating(double rating) {
        setRating(rating/10 + this.rating);
        if(this.rating > 10)
            setRating(10);
    }

    public void updateDish(Dish oldDish, Dish newDish) {
        if (oldDish == null || newDish == null)
            throw new IllegalArgumentException("Dishes cannot be null");
        ArrayList<Dish> dishes = getMenu();
        int index = dishes.indexOf(oldDish);
        if (index  < 0 )
            throw new IllegalArgumentException("Dish not found");
        dishes.set(index, newDish);
    }

    @Override
    public boolean equals(Object obj) {
        Resturant resturant = (Resturant) obj;
        return this.name.equals(resturant.getName());
    }
}
