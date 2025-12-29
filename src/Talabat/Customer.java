package Talabat;
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

public class Customer extends User {
    private String name;
    private String address;
    private String phoneNo;
    private String email;
    private ArrayList<Order> orders;
    Presentable presenter;


    public Customer(Presentable presenter){
        this("","", false, "", "", "", "",new ArrayList<>(), presenter);
    }

    public Customer(String userName,
                    String passWord,
                    boolean logedIn,
                    String name,
                    String address,
                    String phoneNo,
                    String email,
                    ArrayList<Order> orders,
                    Presentable presenter) {

        super(userName, passWord, logedIn);
        setName(name);
        setAddress(address);
        setPhoneNo(phoneNo);
        setEmail(email);
        this.presenter = presenter;
        this.orders = new ArrayList<>(orders);
    }

    public Customer(Customer other, Presentable presenter) {
        this(other.getUserName(),
                other.getPassWord(),
                false,
                other.getName(),
                other.getAddress(),
                other.getPhoneNo(),
                other.getEmail(),
                other.orders,
                presenter);

        this.orders = new ArrayList<>(other.orders);
    }



    // the following 8 Methods are the setters and the getters for the fields
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

// From here the Methods that do actions For the Customers
    /*  the showResLis() Method it takes the customer choice for the Restaurant and returns
        that Restaurant to work on it on the next processes
     */

     private Resturant showResLis() {
         Resturant restaurantPicked; //this var will have the Restaurant that the Customer Choose

         //this restaurantLimit it has the total number we have of Restaurant as String to use in a condition
         String restaurantLimit = Integer.toString(ResturantRepo.getResturantlist().size());

         //show all Restaurants for the Customer
         presenter.print("Restaurants");

         int i = 1;
         for(Resturant res : ResturantRepo.getResturantlist())
             presenter.print("\t" + (i++) + "- " + res.getName());

         presenter.print("\n\nChoose the number of the Restaurant you want (enter X to cancle) : ");

         //this loop to validate the Choice of the Customer of the Restaurants
         while(true) {
             String choice = presenter.read();
             if (choice.toLowerCase().equals("x")) {
                 return null;
             }
             // this condition check if the Entered String is Number because if it was a String fn valueOf wiil Throw Exception
             else {
                 try {
                     int resNo = Integer.valueOf(choice);

                     if (resNo > 0 && resNo <= ResturantRepo.getResturantlist().size()) {
                         restaurantPicked = ResturantRepo.getResturantlist().get(resNo -1);
                         break;
                     } else {
                         throw new IllegalArgumentException();
                     }
                 } catch (IllegalArgumentException e) {
                     presenter.print("Invalid Choice plz Enter a valid Choice");
                 }
             }
         }
         placeOrder(restaurantPicked);

         return restaurantPicked;
     }

    /*
     the placeOrder() Method it takes the Restaurant that returned by the showResLis() and then
     takes the order from the customer and returned it for the next processes
    */
     private Order placeOrder(Resturant res )  {
         Order ord = new Order(presenter); //this will have the orer of the customer will order

         //this dishLimit it has the total number we have of Dishes as String to use in a condition
         String dishLimit = Integer.toString(res.getMenu().size());
         String answer; //this var for if the customer want to add another dish

         // this arraylist to add init the orderitem for customer and to make the order arraylist of menu have it
         ArrayList<OrderItem> ordItem = new ArrayList<>();

         OrderItem orderCustomerPicked = new OrderItem();
            ord.setResturant(res);

            presenter.print("Menu"+"\n\t");
            presenter.print(res.showMenu());
        while(true) {
            presenter.print("Enter Dish no. (enter X to cancel) : ");
            String dishChoice = presenter.read();// this var is the customer when he chooses number dish he want in menu

            if (dishChoice.toLowerCase().equals("x")) {
                return null;
            }
            // this condition check if the Entered String is Number because if it was a String fn valueOf wiil Throw Exception
            else {
                try {
                    int dishNo = Integer.valueOf(dishChoice);

                    if (dishNo > 0 && dishNo <= res.getMenu().size())
                        orderCustomerPicked.setOrderedDish(res.getMenu().get(dishNo - 1));
                    else
                        throw new IllegalArgumentException();

                } catch (IllegalArgumentException e) {
                    presenter.print("Invalid Choice plz Enter a valid Choice");
                    continue;
                }
            }

            presenter.print("Enter the amount: ");
            int amountOfDish = Integer.valueOf(presenter.read());//this var for the customer enter how many dish he wants

            //this while loop for thr validation of the amount of the dishes
            while (true) {
                if (amountOfDish > 0) {
                    orderCustomerPicked.setQuantity(amountOfDish);
                    break;
                } else {
                    presenter.print("Plz Enter a Valid Amount");
                }
            }
            ordItem.add(orderCustomerPicked);

            presenter.print("Do you want another Dish ? (Y/N)");
            answer = presenter.read();

            if (answer.toLowerCase().equals("n")) {
                ord.setMenu(ordItem);
                //here remeber that you will the constructur all the data you want
                ord = new Order(presenter);
                break;
            } else if (!answer.toLowerCase().equals("y")) {
                presenter.print("Invalid Choice plz enter the valid choice");
            }
        }
        this.orders.add(ord);
        ord.showOrder();
        return ord;
     }

    /*
    the newOrder() Method it show the whole process when the customer choose new order from the Restaurants
    available and the menu of the Restaurant that he chose
    */
     public void newOrder(){
        while(true)
            if (this.showResLis() == null)
                return;
     }

     public void cancelOrder(int orderNumber){

         for(int i=0;i<this.orders.size();i++){
             presenter.print((this.orders.get(i).getNumber()));
             presenter.print(this.orders.get(i).getResturant().getName());
             presenter.print("---------------------- \t ----------------------------");
         }
        for(int i=0 ;i<this.orders.size();i++){
           if(this.orders.get(i).getNumber()==orderNumber)
            {
            this.orders.remove(i);

            }
        }
     }

     public void trackOrder(){
        Integer orderPicked;
         for(int i=0;i<this.orders.size();i++){
             presenter.print((this.orders.get(i).getNumber()));
             presenter.print(this.orders.get(i).getResturant().getName());
             presenter.print("---------------------- \t ----------------------------");
         }
         presenter.print("Enter the number of the Order you want to track");

         //this loop to see if the number he enterd for the orderNumber valid or not found
         while(true) {
             orderPicked = Integer.valueOf(presenter.read());
             boolean found =false;
             for (int i = 0; i < this.orders.size(); i++) {

                 if (this.orders.get(i).getNumber() == orderPicked) {
                     this.orders.get(i).showOrder();
                     found = true;
                 }

             }
             if(found)
                 break;
             else {
                 presenter.print("Plz Enter a Valid Order Number");
             }
         }

     }

}
