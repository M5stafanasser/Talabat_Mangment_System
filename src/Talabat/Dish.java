package Talabat;

import static Talabat.Category.APPETIZER;

public class Dish {
    private String name;
    private String discription;
    private Category category;
    private double price;
    private int timesOrdered = 0;
    private boolean visable = false;

    public Dish(String name, String discription, Category category, double price) throws IllegalArgumentException {
        try {
            setName(name);
            setDiscription(discription);
            setCategory(category);
            setPrice(price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Dish()  {
        this("", "", APPETIZER, 0);
    }

    public Dish(Dish dish)  {
        this(dish.name, dish.discription, dish.category, dish.price);
        this.timesOrdered = dish.timesOrdered;
        this.visable = dish.visable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
       if (discription == null)
           throw new IllegalArgumentException("Invalid discription");
        this.discription = discription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("Invalid category");
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        if (timesOrdered < 0)
            throw new IllegalArgumentException("TimesOrdered cannot be negative");
        this.timesOrdered = timesOrdered;
    }

    public boolean isVisable() {
        return visable;
    }

    public void setVisable(boolean visable) {
        this.visable = visable;
    }
}
