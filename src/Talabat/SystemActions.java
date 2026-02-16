package Talabat;

public class SystemActions {
    private static Presentable presenter;

    public static void setPresenter(Presentable presenter) {
        SystemActions.presenter = presenter;
    }

    public static User startSystem() {
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

    public static void showMainOptions(){}

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
        return restaurantPicked;
    }

}