package Talabat;

import java.util.ArrayList;

public class Admin extends User {
    private Presentable presenter;

    public Admin(Presentable presenter) {
        this.presenter = presenter;
    }

    public void adminMenu() {
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
                viewCustomerList(CustomerRepo.getCustomerList());
            else if (choice.equals("2"))
                viewResList(ResturantRepo.getResturantlist());
            else if (choice.equals("3"))
                addRes();
            else if (choice.equals("4")) {
                editResDetail();
            } else if (choice.equals("5"))
                editCustomerDetail();
            else if (choice.equals("6"))
                removeCustomer();
            else
                presenter.print("invalid input");

        } while (!choice.equals("0"));
    }

    public void viewCustomerList(ArrayList<Customer> list) {
        for (Customer c : list)
            presenter.print(c);
    }

    public void viewResList(ArrayList<Resturant> list) {
        for (Resturant r : list)
            presenter.print(r.getName());
    }

    public void addRes() {
        presenter.print("Restaurant name");
        String name = presenter.read();

        presenter.print("Phone");
        String phone = presenter.read();

        presenter.print("Address");
        String address = presenter.read();

        double rating;
        while (true){
            try {
                presenter.print("Rating");
                rating = Double.valueOf(presenter.read());
            } catch (NumberFormatException e) {
                presenter.print("invalid input");
                continue;
            }
            break;
        }


        ArrayList<Dish> menu = new ArrayList<>();

        while (true) {
            Dish d = new Dish();

            presenter.print("Dish name");
            d.setName(presenter.read());

            presenter.print("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
            char c = presenter.read().toUpperCase().charAt(0);

            if (c == 'A') d.setCategory(Category.APPETIZER);
            if (c == 'M') d.setCategory(Category.MAIN_COURSE);
            if (c == 'D') d.setCategory(Category.DESSERT);
            if (c == 'B') d.setCategory(Category.DRINK);

            try {
                presenter.print("Price");
                d.setPrice(Double.valueOf(presenter.read()));
            } catch (NumberFormatException e) {
                presenter.print("invalid input");
                continue;
            }
            menu.add(d);

            presenter.print("Add another dish? (y/n)");
            if (presenter.read().equalsIgnoreCase("n"))
                break;
        }

        ResturantRepo.getResturantlist().add(new Resturant(name, phone, rating, address, menu));
    }

    public void editResDetail() {
        Resturant resturant = null;
        while(true) {
            presenter.print("Enter restaurant name (Enter x to cancle):");
            String name = presenter.read();
            if(name.toLowerCase().equals("x"))
                return;
            try {
                resturant = ResturantRepo.getResturantlist().get(
                    ResturantRepo.getResturantlist().indexOf(new Resturant(name)));
            } catch (Exception e) {
                presenter.print("invalid input");
                continue;
            }
            break;
        }

        String choice;
        do {
            presenter.print("\n--- Edit Restaurant ---\n" +
                    "1. Edit Info\n" +
                    "2. Add Dish\n " +
                    "3. Remove Dish\n " +
                    "4. Update Dish\n " +
                    "0. Back");

            choice = presenter.read();

            if (choice.equals("1")) {
                presenter.print("New name");
                resturant.setName(presenter.read());

                presenter.print("New address");
                resturant.setAddress(presenter.read());

                presenter.print("New phone");
                resturant.setPhone(presenter.read());

                while (true){
                    double rating;
                    try {
                        presenter.print("New rating");
                        rating = Double.valueOf(presenter.read());
                    } catch (NumberFormatException e) {
                        presenter.print("invalid input");
                        continue;
                    }
                    resturant.setRating(rating);
                    break;
                }

            }
            else if (choice.equals("2")) {
                Dish newdish = new Dish();

                presenter.print("Dish name");
                newdish.setName(presenter.read());

                presenter.print("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
                char c = presenter.read().toUpperCase().charAt(0);

                if (c == 'A') newdish.setCategory(Category.APPETIZER);
                if (c == 'M') newdish.setCategory(Category.MAIN_COURSE);
                if (c == 'D') newdish.setCategory(Category.DESSERT);
                if (c == 'B') newdish.setCategory(Category.DRINK);

                while (true){
                    double price;
                    try {
                        presenter.print("Price");
                        price = Double.valueOf(presenter.read());
                    } catch (NumberFormatException e) {
                        presenter.print("invalid input");
                        continue;
                    }
                    newdish.setPrice(price);
                    break;
                }

                resturant.addDish(newdish);
            }
            else if (choice.equals("3")) {
                presenter.print("Dish name");
                for (Dish dish : resturant.getMenu()) {
                    if (dish.getName().equals(presenter.read())) {
                        resturant.removeDish(dish);
                    }
                }
            }
            else if (choice.equals("4")) {
                Dish oldDish = null;
               while(true) {
                   presenter.print("Old dish name(enter x to cancle) :");
                   String oldName = presenter.read();
                   if(oldName.toLowerCase().equals("x"))
                    return;
                   try {
                       oldDish = resturant.getMenu().get(
                               resturant.getMenu().indexOf(new Dish(oldName)));
                   } catch (Exception e) {
                       presenter.print("invalid input");
                       continue;
                   }
                   break;
               }
                presenter.print("old dish :" + oldDish);
                presenter.print("New dish name:");
                String newName = presenter.read();
                presenter.print("New description:");
                String newDesc = presenter.read();

                Category newCategory = Category.APPETIZER;
                presenter.print("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
                char c = presenter.read().toUpperCase().charAt(0);
                if (c == 'A') newCategory = Category.APPETIZER;
                if (c == 'M') newCategory = Category.MAIN_COURSE;
                if (c == 'D') newCategory = Category.DESSERT;
                if (c == 'B') newCategory = Category.DRINK;

                double price;
                while (true){
                    try {
                        presenter.print("Price");
                        price = Double.valueOf(presenter.read());
                    } catch (NumberFormatException e) {
                        presenter.print("invalid input");
                        continue;
                    }
                    break;
                }

                Dish newDish = new Dish(newName, newDesc, newCategory, price);

                resturant.updateDish(oldDish, newDish);
            }
            else
                presenter.print("invalid input");
        }while (! choice.equals("0"));
    }

    public void editCustomerDetail() {
        Customer customer;
        while(true){
            presenter.print("please enter the username of the customer to delete(x to cancle): ");
            String username = presenter.read();
            if(username.equals("x"))
                return;

            int index = CustomerRepo.getCustomerList().indexOf(new Customer(username, presenter));
            if(index == -1)
                continue;
            customer = CustomerRepo.getCustomerList().get(index);
            break;
        }
        presenter.print("Enter x to cancle");
        String chois= presenter.read();

        if (chois.toLowerCase().equals("x"))
                return;

        presenter.print("New name");
        customer.setName(presenter.read());

        presenter.print("New email");
        customer.setEmail(presenter.read());

        presenter.print("New phone");
        customer.setPhoneNo(presenter.read());

        presenter.print("New address");
        customer.setAddress(presenter.read());
    }

    public void removeCustomer() {
        while(true){
            presenter.print("please enter the username of the customer to delete:");
            String username = presenter.read();
            int index = CustomerRepo.getCustomerList().indexOf(new Customer(username, presenter));
            if(index == -1)
                continue;
            CustomerRepo.getCustomerList().remove(index);
            break;
        }
    }
}
