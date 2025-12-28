package Talabat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Resturant {
    private String name;
    private String phone;
    private double rating;
    private String address;
    private HashMap<Category, ArrayList<Dish>> menu;
    public Resturant(String name, String phone, double rating, String address, HashMap<Category, ArrayList<Dish>> menu) throws IllegalArgumentException {
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
        this("", "", 0, "", new HashMap<>());
    }

    public Resturant(Resturant other) {
        this(other.name, other.phone, other.rating, other.address, other.menu);
    }

    public String showMenu() {
        String menuList = "";
        for (Category category : menu.keySet()) {
            if (menu.get(category).size() > 0) {
                menuList += "Category: " + category + "\n\n";
                for (Dish dish : menu.get(category)) {
                    menuList += "\t- " + dish.getName() + " : " + dish.getPrice() + "\n";
                }
                menuList += "\n";
            }
        }
        return menuList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");
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
        if (rating < 0 || rating > 5)
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

    public HashMap<Category, ArrayList<Dish>> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<Category, ArrayList<Dish>> menu) {
        if (menu == null)
            throw new IllegalArgumentException("Menu cannot be null");
        this.menu = new HashMap<>();
        for (Category category : Category.values()) {
            this.menu.put(category, new ArrayList<>());
        }
        for (Category category : menu.keySet()) {
            for (Dish dish : menu.get(category)) {
                this.menu.get(category).add(new Dish(dish));
            }
        }
    }

    public void addDish(Dish dish, Category category) {
        if (dish == null)
            throw new IllegalArgumentException("Dish cannot be null");

        if (!menu.containsKey(category))
            throw new IllegalArgumentException("Invalid category");

        menu.get(category).add(dish);
    }
    public void removeDish(Dish dish, Category category) {
        if(!(menu.containsKey(category) && dish!=null)) {
            throw new IllegalArgumentException("Dish not found");
        }
        menu.get(category).remove(dish);
    }

    public void updateDish(Dish oldDish, Dish newDish) {
        if (oldDish == null || newDish == null)
            throw new IllegalArgumentException("Dishes cannot be null");

        Category category = oldDish.getCategory();

        if (!menu.containsKey(category))
            throw new IllegalArgumentException("Category not found");

        ArrayList<Dish> dishes = menu.get(category);
        int index = dishes.indexOf(oldDish);
        if (index  < 0 )
            throw new IllegalArgumentException("Dish not found");

        dishes.set(index, newDish);

    }
}
