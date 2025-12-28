package Talabat;


import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Order {
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    private int number ;
    private Resturant resturant ;
    private LocalDateTime deliveryTime ;
    private enum orderStatus{
        Preparing , Delivering , Finished
    }
    private orderStatus status;
    private double totalPrice ;
    private ArrayList<OderItem> menu;
    private Payment payment ;
    private static int counter = 0 ;

    public Order() {
        this(0,null,null,orderStatus.Preparing,new ArrayList<OderItem>()) ;
        this.totalPrice = 0.0;
    }

    private Order(int number, Resturant resturant, LocalDateTime deliveryTime,
                  orderStatus status, ArrayList<OderItem> dishList) {
        setResturant(resturant);
        setDeliveryTime(deliveryTime);
        setStatus(status);
        setNumber(number);
        setMenu(dishList);
    }
    public Order( Resturant resturant, LocalDateTime deliveryTime,
                  orderStatus status, ArrayList<OderItem> dishList) {
        this(counter++,resturant,LocalDateTime.now().plusMinutes(20),status,dishList) ;
    }


    public Order(Order otherOrder){
        this(otherOrder.number,otherOrder.resturant,otherOrder.deliveryTime,otherOrder.status,otherOrder.menu) ;
    }

    public void setMenu(ArrayList<OderItem> menu) {
        this.menu = menu;
        calcTotal();
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) throws IllegalArgumentException {
        this.deliveryTime = deliveryTime ;
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

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public Resturant getResturant() {
        return resturant;
    }

    public orderStatus getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDeliveryTime() {
        return deliveryTime.toString();
    }


    public void calcTotal(){
        for(OderItem item : menu){
//            this.totalPrice += item.getTotalPrice();
        }
    }


    private boolean finishOrder(String address){

        System.out.println("address:" + address);
        for(OderItem item : menu){
            System.out.println(item);
        }
        System.out.println("Sub_total:" + formatter.format(totalPrice));
        System.out.println("Delivery_price:" + formatter.format(20));
        System.out.println("Total:" + formatter.format(totalPrice+20));

        while(true){
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next() ;
            if(input.toLowerCase().equals("x")){
                return false ;
            }
            else if(input.toLowerCase().equals("b")){
                payment = new Payment() ;
//                  payment.processPayment();
                return true ;
            }
            else {
                System.out.println("Invaild input Try Again!");
            }

        }
    }




    public void showOrder(){

        System.out.println("Order Number: " +getNumber());

        System.out.println("Restaurant: " + getResturant());

        System.out.println("Status:"+ getStatus());

        System.out.println("Delivery Time:"+ getDeliveryTime());

        System.out.println("Items:");
        for(OderItem item : menu){
            System.out.println(item);
        }

        System.out.println("Total_Price:" + formatter.format(getTotalPrice()));

    }
}