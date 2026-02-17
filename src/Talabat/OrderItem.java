package Talabat;

public class OrderItem {

    private int quantity;
    private double TotalPrice;

    private Dish orderedDish;


    public OrderItem(int quantity, Dish orderedDish)throws IllegalArgumentException{
        try {
            setQuantity(quantity);
            setOrderedDish(orderedDish);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public OrderItem(){
        this(0, new Dish());
    }

    public OrderItem(OrderItem otherOrderItem){
        this(otherOrderItem.getQuantity(), otherOrderItem.getOrderedDish());
    }


    private void calculateTotalPrice() {
      this.TotalPrice = this.quantity * this.orderedDish.getPrice();
    }

    public void setOrderedDish(Dish orderedDish) throws IllegalArgumentException{
        if (orderedDish == null)
            throw new IllegalArgumentException("Dish can't be null");

        this.orderedDish = new Dish(orderedDish);
    }

    public void setQuantity(int quantity) throws IllegalArgumentException{
        if(quantity < 0){
            throw new IllegalArgumentException("quantity can't be negative");
        }
        this.quantity = quantity;
    }

    public double getTotalPrice()  {
        calculateTotalPrice();
        System.out.println(orderedDish);
        System.out.println(quantity);
        return TotalPrice;
    }

    public Dish getOrderedDish() { return orderedDish; }

    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return orderedDish.getName() +
                "\t"  + getQuantity()
                + "\t" + getTotalPrice();
    }
}


