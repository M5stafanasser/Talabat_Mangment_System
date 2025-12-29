package Talabat;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Character.toLowerCase;

public class Customer extends User {
    ConsolePresenter presenter;

    // the Constructors we have
    public Customer(){
        this.name = "User10022585110005";
        this.email="User1002258@gmail.com";
        this.address="Egypt Cairo ElMaadi ElSaada st";
        this.number = "01101010449";

    }

    public Customer(String name, String email, String number, String address) {
        setName(name);
        setEmail(email);
        setNumber(number);
        setAddress(address);
    }

    public Customer(Customer c1) {
        setName(c1.name);
        setEmail(c1.email);
        setNumber(c1.number);
        setAddress(c1.address);
        this.orders=c1.orders;

    }

    // the fields of the Class
    private String name;
    private String email;
    private String number;
    private String address;
    private ArrayList<Order> orders = new ArrayList<>();


    // the Methods of the Class

    // the following 8 Methods are the setters and the getters for the fields
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getNumber() {
        return number;
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
         Resturant restaurantPicked;
         //show all Restaurants for the Customer
         presenter.print("Restaurants");
         for(int i=0;i<ResturantRepo.getResturantlist().size();i++){
             presenter.print('\t'+ i+1+": "+ResturantRepo.getResturantlist().get(i).getName());
         }
         presenter.print("Choose the number of the Restaurant you want (enter X to cancle) : ");
         while(true) {
             String choice = presenter.read();
             if (choice.toLowerCase().charAt(0) == 'x') {
                 return null;
             }
             else if (Integer.valueOf(choice)>0 &&Integer.valueOf(choice)<= ResturantRepo.getResturantlist().size()+1){
                  restaurantPicked =ResturantRepo.getResturantlist().get(Integer.valueOf(choice));
                 break;
             }
             else {
                 presenter.print("Invalid Choice plz Enter a valid Choice");
                 continue;
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
        Order ord;
         char answer;

         //OderItem ordItem = new OderItem();
         // ord.setResturant = res;
         // ord.setDeliveryTime = ....;

            presenter.print(res.showMenu());
        while(true){
            presenter.print("Enter Dish no. (enter X to cancle) : ");
            String dishChoice = presenter.read();
            if(dishChoice.toLowerCase().charAt(0) == 'x' ){
                return null;
            }
            //else if()


            // you will the dish and add it in orderitem var that is called ordItem
            //and then you will add the ordItem in the arraylist of the dishes in the order

            presenter.print("Enter the amount ");

            //ord.dishList.get(dishList.size()-1).quantity = input.nextInt();

            presenter.print("Do you want another Dish ? (Y/N)");
            answer = presenter.read().charAt(0);

                if(toLowerCase(answer) == 'n') {
                    //here remeber that you will the constructur all the data you want
                    ord = new Order();
                    break;
                }
               else if (!(toLowerCase(answer) == 'y')) {
                    presenter.print("Invalid Choice plz enter the valid choice");
                    continue;
                }


        }
        this.orders.add(ord);
        // ord.showOrder();

        return ord;
     }

    /*
    the newOrder() Method it show the whole process when the customer choose new order from the Restaurants
    available and the menu of the Restaurant that he chose
    */
     public void newOrder(){

        while(true){
            if(this.showResLis() == null){
                return;
            }

        }




     }

     public void cancelOrder(int orderNumber){
        for(int i=0 ;i<this.orders.size();i++){
            /*
            show Orders all he has and the Restaurants

            if(this.orders.number==orderNumber&&order.status==false)
            {
            this.orders.remove(i);

            }


             */
        }


     }

     public void trackOrder(){
        Integer orderPicked;
        for(int i=0;i<this.orders.size();i++){

            /*
            if(this.orders.status==true){
                //YOU WILL PRINT ALL THE ORDERS RESTAURNAT HERE AND NUMBER AND STATUS


            }

             */

            orderPicked = Integer.valueOf(presenter.read());

            /*
             for(int i=0;i<this.orders.size();i++){
                 if(this.orders.get(i).number == orderPicked){
                     Order orderTracked = this.orders.get(i);
                 }

             }


             */

            //System.out.println(orderTracked.getNumber);
            //System.out.println(orderTracked.getResturant.getName);
            // System.out.println(orderTracked.getDeliveryTime);
            // System.out.println(orderTracked.getStatus);
            // System.out.println(orderTracked.getTotalPrice);

        }


     }

}
