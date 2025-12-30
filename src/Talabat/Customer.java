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

    public Customer(String username, Presentable presenter) {
        this(presenter);
        this.setUserName(username);
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
         Order customerOrder = new Order(presenter); //this will have the orer of the customer will order

         //this dishLimit it has the total number we have of Dishes as String to use in a condition
        // String dishLimit = Integer.toString(res.getMenu().size());
         String answer; //this var for if the customer want to add another dish

         // this arraylist to add init the orderitem for customer and to make the order arraylist of menu have it
         ArrayList<OrderItem> customerAllDishes = new ArrayList<>();

         OrderItem orderCustomerPicked = new OrderItem();
            customerOrder.setResturant(res);

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


            //this while loop for thr validation of the amount of the dishes
            while (true) {
                try {
                    //this var for the customer enter how many dish he wants
                    int amountOfDish = Integer.valueOf(presenter.read());
                    if (amountOfDish > 0) {
                        orderCustomerPicked.setQuantity(amountOfDish);
                        break;
                    } else {
                       throw new IllegalArgumentException();
                    }
                }catch(IllegalArgumentException e){
                    presenter.print("Invalid Amount plz Enter a valid Amount");

                }
            }
            customerAllDishes.add(orderCustomerPicked);

            presenter.print("Do you want another Dish ? (Y/N)");
            answer = presenter.read();

            if (answer.toLowerCase().equals("n")) {
                customerOrder.setMenu(customerAllDishes);
                //here remeber that you will the constructur all the data you want
                customerOrder = new Order(presenter);
                break;
            } else if (!answer.toLowerCase().equals("y")) {
                presenter.print("Invalid Choice plz enter the valid choice");
            }
        }
        this.orders.add(customerOrder);
        customerOrder.showOrder();
        return customerOrder;
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


     public void cancelOrder(){
        int orderNumber;
         for(Order order : this.orders){
             presenter.print((order.getNumber()));
             presenter.print(order.getResturant().getName());
             presenter.print("---------------------- \t ----------------------------");
         }
         while(true) {
             boolean found = false;
             try {
                 orderNumber = Integer.valueOf(presenter.read());
                 for (Order order : this.orders) {
                     if (order.getNumber() == orderNumber) {
                         this.orders.remove(order);
                         found = true;
                         break;

                     }
                 }
                 if(!found)
                     throw new NumberFormatException();
                 else
                     break;

             } catch (NumberFormatException e){
                 presenter.print("Invalid number Enter Invalid number");
             }
         }

     }

     public void trackOrder(){
        Integer orderPicked;
         for(Order order : this.orders){
             presenter.print((order.getNumber()));
             presenter.print(order.getResturant().getName());
             presenter.print("---------------------- \t ----------------------------");
         }
         presenter.print("Enter the number of the Order you want to track");

         //this loop to see if the number he enterd for the orderNumber valid or not found
         while(true) {
             try {
                 orderPicked = Integer.valueOf(presenter.read());
                 boolean found = false;

                 for (Order order : this.orders) {

                     if (order.getNumber() == orderPicked) {
                         order.showOrder();
                         found = true;
                         break;
                     }

                 }
                 if (!found)
                     throw new NumberFormatException();
                 else
                     break;
             }
             catch(NumberFormatException e) {
                 presenter.print("Plz Enter a Valid Order Number");
             }
         }

     }

    @Override
    public String toString() {
        return this.getName() + " "+this.getAddress()+ " "+this.getEmail()+" "+getPhoneNo();
    }

    @Override
    public boolean equals(Object obj) {
        Customer other = (Customer) obj;
        return this.getUserName().equals(other.getUserName());
    }

    public static Customer addCustomer (Presentable presenter){
         Customer customer = new Customer(presenter);
         presenter.print("Enter name : ");
         customer.setName(presenter.read());
         presenter.print("Enter phone : ");
         customer.setPhoneNo(presenter.read());
         presenter.print("Enter Email : ");
         customer.setEmail(presenter.read());
         presenter.print("Enter user name : ");
         customer.setUserName(presenter.read());
         presenter.print("Enter password : ");
         customer.setPassWord(presenter.read());
         presenter.print("Enter address : ");
         customer.setAddress(presenter.read());

         presenter.print("customer added\t welcome to TALABT");
         CustomerRepo.addCustomer(customer);
         return customer;
    }

    public static User findCustomer(Presentable presenter){
        while (true){
            presenter.print("Enter User name :");
            String username = presenter.read();

            User user = CustomerRepo.findCustomer(new Customer(username, presenter));

            if (username.equals("ADMIN")) {
                while (true) {
                    presenter.print("Enter password (Enter x to cancel:");
                    String password = presenter.read();
                    if (password.equals("x")) {
                        user = null;
                    } else if (!password.equals("admin123")){
                        presenter.print("wrong password, try again");
                        continue;
                    }else {
                        presenter.print("welcome back ADMIN");
                        user = new Admin(presenter);
                    }
                    break;
                }
            }
            else if (user == null) {
                presenter.print("no such user name, try again");
                continue;
            }
            else {
                while (true) {
                    presenter.print("Enter password (Enter x to cancel:");
                    String password = presenter.read();
                    if (password.equals("x"))
                        user = null;
                    else if (!password.equals(user.getPassWord())) {
                        presenter.print("wrong password, try again");
                        continue;
                    }
                    else
                        presenter.print("welcome back ADMIN");
                    break;
                }
            }

            return user;
        }
    }

}
