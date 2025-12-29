package Talabat;

import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    private int number;
    private Resturant resturant;
    private double totalPrice;
    private LocalDateTime deliveryTime;
    private OrderStatus status;
    private ArrayList<OrderItem> menu;
    private Payment payment;
    private static int counter;
    private NumberFormat formatter;
    private Presentable presenter;

    public Order(Presentable presenter) {
        this(0,
                null,
                null,
                OrderStatus.Preparing,
                new ArrayList<OrderItem>(),
                presenter) ;

        this.totalPrice = 0.0;
    }

    private Order(int number, Resturant resturant, LocalDateTime deliveryTime,
                  OrderStatus status, ArrayList<OrderItem> dishList, Presentable presenter) {
        setResturant(resturant);
        setDeliveryTime(deliveryTime);
        setStatus(status);
        setNumber(number);
        setMenu(dishList);

        formatter = NumberFormat.getCurrencyInstance(Locale.US);
    }

    public Order(Resturant resturant,
                 ArrayList<OrderItem> dishList,
                 Presentable presenter) {

        this(counter++,
                resturant,
                LocalDateTime.now().plusMinutes(20),
                OrderStatus.Preparing,
                dishList,
                presenter ) ;
    }

    public Order(Order otherOrder){
        this(otherOrder.getNumber(),
                otherOrder.getResturant(),
                otherOrder.getDeliveryTime(),
                otherOrder.getStatus(),
                otherOrder.getMenu(),
                otherOrder.getPresenter());
    }


    public void setMenu(ArrayList<OrderItem> menu) {
        this.menu = new ArrayList<OrderItem>(menu);
        calcTotal();
    }

    public void setDeliveryTime(LocalDateTime deliveryTime){
        this.deliveryTime = deliveryTime;
    }

    public void setResturant(Resturant resturant) {
        this.resturant = resturant;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public int getNumber() {
        return number;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public ArrayList<OrderItem> getMenu() {
        return menu;
    }

    public Presentable getPresenter() {
        return presenter;
    }


    public void calcTotal(){
        this.totalPrice = 0;
        for(OrderItem item : menu){
            this.totalPrice += item.getTotalPrice();
        }
    }

    private boolean finishOrder(String address){

        presenter.print("address:" + address + "\n\n");

        for(OrderItem item : menu){
            presenter.print("\t-");
            presenter.print(item);
        }

        presenter.print("\n\n\tSub_total:" + formatter.format(totalPrice));
        presenter.print("\tDelivery_price:" + formatter.format(20));
        presenter.print("Total:" + formatter.format(totalPrice+20));

        while(true){
            presenter.print("Enter P to proceed to payment");
            presenter.print("Enter X to cancel order");

            String input = presenter.read();
            if(input.toLowerCase().equals("x"))
                return false ;
            else if(input.toLowerCase().equals("p")){
                payment = new Payment() ;
                payment.processPayment();
                return true;
            }
            else
                presenter.print("Invaild input Try Again!");
        }
    }

    public void showOrder(){
        presenter.print("Order Number: " + number);

        presenter.print("Restaurant: " + resturant);

        presenter.print("Status:"+ status);

        presenter.print("Delivery Time:"+ deliveryTime);

        presenter.print("\n\nItems:");
        for(OrderItem item : menu){
            presenter.print("\t -");
            presenter.print(item);
        }

        presenter.print("Total_Price:" + formatter.format(totalPrice));
    }

    @Override
    public boolean equals(Object obj) {
        Order other = (Order) obj;
        return (this.number == other.getNumber());
    }
}