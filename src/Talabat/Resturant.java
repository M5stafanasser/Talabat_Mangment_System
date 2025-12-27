package Talabat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Resturant {
    private String name;
    private String phone;
    private double rating=0;
    private String address;
    private HashMap<Category, ArrayList<Dish>> menu;

    public Resturant(){
        this.name = "";
        this.phone = "";
        this.address = "";
        this.menu = new HashMap<>();
        for (Category category : Category.values()){
            this.menu.put(category,new ArrayList<>());
        }
    }
    public Resturant(String name, String phone, double rating, String address, Dish[] dishes) {
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        this.address = address;
        this.menu = new HashMap<>();
        for (Category category : Category.values()) {
            this.menu.put(category, new ArrayList<>());
        }
        for (Dish d : dishes) {
            menu.get(d.getCategory()).add(d);
        }
    }
    public Resturant(Resturant other) {
        this.name = other.name;
        this.phone = other.phone;
        this.rating = other.rating;
        this.address = other.address;
        this.menu = new HashMap<>();
        for (Category c : Category.values()) {
            ArrayList<Dish> copyList = new ArrayList<>();
            for (Dish d : other.menu.get(c)) {
                copyList.add(new Dish(d));
            }
            this.menu.put(c, copyList);
        }
    }
    public void showMenu(){
        for (Category c : menu.keySet()) {
            System.out.println("Category: " + c);
            for (Dish d : menu.get(c)) {
                System.out.println("- " + d.getName() + " : " + d.getPrice());
            }
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<Category, ArrayList<Dish>> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Category, ArrayList<Dish>> menu) {
        this.menu = menu;
    }
}
