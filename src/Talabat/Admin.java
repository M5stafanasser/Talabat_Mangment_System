package Talabat;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {

    private Scanner scanner = new Scanner(System.in);

    public void adminMenu() {
        int choice;

        do {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1. View Customers");
            System.out.println("2. View Restaurants");
            System.out.println("3. Add Restaurant");
            System.out.println("4. Edit Restaurant");
            System.out.println("5. Edit Customer");
            System.out.println("6. Remove Customer");
            System.out.println("0. Logout");

            choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    viewCustomerList(CustomerRepo.getCustomerList());
                    break;

                case 2:
                    viewResList(ResturantRepo.getResturantlist());
                    break;

                case 3:
                    addRes();
                    break;

                case 4:
                    System.out.println("Enter restaurant name");
                    editResDetail(scanner.nextLine());
                    break;

                case 5:
                    System.out.println("Enter customer name");
                    editCustomerDetail(scanner.nextLine());
                    break;

                case 6:
                    System.out.println("Enter customer name");
                    removeCustomer(scanner.nextLine());
                    break;

                case 0:
                    logout();
                    break;
            }

        } while (choice != 0);
    }

    public void viewCustomerList(ArrayList<Customer> list) {
        for (Customer c : list)
            System.out.println(c);
    }

    public void viewResList(ArrayList<Resturant> list) {
        for (Resturant r : list)
            System.out.println(r.getName());
    }

    public void addRes() {
        System.out.println("Restaurant name");
        String name = scanner.nextLine();

        System.out.println("Phone");
        String phone = scanner.nextLine();

        System.out.println("Address");
        String address = scanner.nextLine();

        System.out.println("Rating");
        double rating = scanner.nextDouble();


        ArrayList<Dish> menu = new ArrayList<>();

        while (true) {
            Dish d = new Dish();

            System.out.println("Dish name");
            d.setName(scanner.nextLine());

            System.out.println("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
            char c = scanner.nextLine().toUpperCase().charAt(0);

            if (c == 'A') d.setCategory(Category.APPETIZER);
            if (c == 'M') d.setCategory(Category.MAIN_COURSE);
            if (c == 'D') d.setCategory(Category.DESSERT);
            if (c == 'B') d.setCategory(Category.DRINK);

            System.out.println("Price");
            d.setPrice(scanner.nextDouble());

            menu.add(d);

            System.out.println("Add another dish? (y/n)");
            if (scanner.nextLine().equalsIgnoreCase("n"))
                break;
        }

        ResturantRepo.getResturantlist()
                .add(new Resturant(name, phone, rating, address, menu));
    }

    public void editResDetail(String name) {
        Resturant resturant = new Resturant();
        for (Resturant r : ResturantRepo.getResturantlist()){
            if (r.getName().equals(name)) {
                resturant = r;
            }
        }

        int choice;

        do {
            System.out.println("\n--- Edit Restaurant ---");
            System.out.println("1. Edit Info");
            System.out.println("2. Add Dish");
            System.out.println("3. Remove Dish");
            System.out.println("4. Update Dish");
            System.out.println("0. Back");

            choice = scanner.nextInt();


            if (choice == 1) {
                System.out.println("New name");
                resturant.setName(scanner.nextLine());

                System.out.println("New address");
                resturant.setAddress(scanner.nextLine());

                System.out.println("New phone");
                resturant.setPhone(scanner.nextLine());

                System.out.println("New rating");
                resturant.setRating(scanner.nextDouble());

            }

            if (choice == 2) {
                Dish d = new Dish();

                System.out.println("Dish name");
                d.setName(scanner.nextLine());

                System.out.println("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
                char c = scanner.nextLine().toUpperCase().charAt(0);

                if (c == 'A') d.setCategory(Category.APPETIZER);
                if (c == 'M') d.setCategory(Category.MAIN_COURSE);
                if (c == 'D') d.setCategory(Category.DESSERT);
                if (c == 'B') d.setCategory(Category.DRINK);

                System.out.println("Price");
                d.setPrice(scanner.nextDouble());


                resturant.addDish(d);
            }

            if (choice == 3) {
                System.out.println("Dish name");
               for (Dish dish : resturant.getMenu()){
                   if (dish.getName().equals(scanner.nextLine())) {
                       resturant.removeDish(dish);
                   }
               }
            }

            if (choice == 4) {
                System.out.println("Old dish name:");

                String oldName = scanner.nextLine();

                Dish oldDish = new Dish();
                for (Dish oldDishD : resturant.getMenu()){
                    if (oldDishD.getName().equals(oldName)) {
                        oldDish = oldDishD;
                    }
                }

                System.out.println("New dish name:");
                String newName = scanner.nextLine();

                System.out.println("New description:");
                String newDesc = scanner.nextLine();


                Category newCategory = Category.APPETIZER;
                System.out.println("Category (A Appetizer /M Main_Course /D Desert /B brevelge)");
                char c = scanner.nextLine().toUpperCase().charAt(0);
                if (c == 'A') newCategory = Category.APPETIZER;
                if (c == 'M') newCategory = Category.MAIN_COURSE;
                if (c == 'D') newCategory = Category.DESSERT;
                if (c == 'B') newCategory = Category.DRINK;

                System.out.println("New price:");
                double price = scanner.nextDouble();

                Dish newDish = new Dish(newName, newDesc, newCategory, price);

                resturant.updateDish(oldDish, newDish);
            }

        } while (choice != 0);
    }

    public void editCustomerDetail(String name) {
        Customer customer = new Customer();
        for (Customer customer1 : CustomerRepo.getCustomerList()){
            if (customer1.getName().equals(name)) {
                customer = customer1;
            }
        }
        System.out.println("New name");
        customer.setName(scanner.nextLine());

        System.out.println("New email");
        customer.setEmail(scanner.nextLine());

        System.out.println("New phone");
        customer.setNumber(scanner.nextLine());

        System.out.println("New address");
        customer.setAddress(scanner.nextLine());
    }

    public void removeCustomer(String name) {
        Customer customer = new Customer();
        for (Customer customer1 : CustomerRepo.getCustomerList()){
            if (customer1.getName().equals(name)) {
                customer = customer1;
            }
        }

        if (!CustomerRepo.getCustomerList().remove(customer))
            throw new IllegalArgumentException("Customer not found");
    }
}
