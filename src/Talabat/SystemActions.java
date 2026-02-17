package Talabat;

public class SystemActions {
    private static Presentable presenter;

    public static void setPresenter(Presentable presenter) {
        SystemActions.presenter = presenter;
    }

    public static User startSystem() {
        CustomerRepo.setPresenter(presenter);
        ResturantRepo.dummyData();
        CustomerRepo.dummyData();

        presenter.print("\t\t ======= welcome to Talabat =======");
        presenter.print("1) log in\n" + "2) sign in");
        while (true){
            String answer = presenter.read();
            if (answer.equals("1"))
                return login();
            else if (answer.equals("2"))
                return signin();

            System.out.println("invalid answer, please try again");
        }
    }

    public static User signin(){
        while (true){
            presenter.print("Enter User name :");
            String username = presenter.read();

            User user = CustomerRepo.findCustomer(new Customer(username, presenter));

            if (username.equals("ADMIN")) {
                while (true) {
                    presenter.print("Enter password (Enter x to cancel:");
                    String password = presenter.read();
                    if (password.toLowerCase().equals("x")) {
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
                    if (password.toLowerCase().equals("x"))
                        user = null;
                    else if (!password.equals(user.getPassWord())) {
                        presenter.print("wrong password, try again");
                        continue;
                    }
                    else {
                        presenter.print("welcome back" + user.getUserName());
                        user.login();
                    }
                    break;
                }
            }
            return user;
        }
    }

    public static User login(){
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

    public static void showMainOptions(User user){
        if (user instanceof Admin) {
            Admin admin = (Admin) user;

            String choice;
            do {
                presenter.print("\n====== ADMIN MENU ======\n" +
                        "1. View Customers\n" +
                        "2. View Restaurants\n" +
                        "3. Add Restaurant\n" +
                        "4. Edit Restaurant\n" +
                        "5. Edit Customer\n" +
                        "6. Remove Customer\n" +
                        "0. Logout \n");

                choice = presenter.read();

                if (choice.equals("1"))
                    admin.viewCustomerList(CustomerRepo.getCustomerList());
                else if (choice.equals("2"))
                    admin.viewResList(ResturantRepo.getResturantlist());
                else if (choice.equals("3"))
                    admin.addRes();
                else if (choice.equals("4")) {
                    admin.editResDetail();
                } else if (choice.equals("5"))
                    admin.editCustomerDetail();
                else if (choice.equals("6"))
                    admin.removeCustomer();
                else if (choice.equals("0"))
                    return;
                else
                    presenter.print("invalid input");
            } while (!choice.equals("0"));
        }
        else if (user instanceof Customer){
            Customer customer = (Customer) user;

            String choice;
            do {
                presenter.print("\n====== ADMIN MENU ======\n" +
                        "1. New Order\n" +
                        "2. Track Oder\n" +
                        "0. Logout \n");

                choice = presenter.read();

                if (choice.equals("1"))
                    customer.newOrder();
                else if (choice.equals("2"))
                    customer.trackOrder();
                else if (choice.equals("0"))
                    return;
                else
                    presenter.print("invalid input");
            } while (!choice.equals("0"));
        }
    }

    public static Resturant showResLis() {
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
                        restaurantPicked = ResturantRepo.getResturantlist().get(resNo-1);
                        break;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    presenter.print("Invalid Choice plz Enter a valid Choice");
                }
            }
        }
        return restaurantPicked;
    }

}