package Talabat;

import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {




    public void viewCustomerList(ArrayList<Customer> cusomerlist){
        for (Customer c:cusomerlist){
            System.out.println(c);
        }
    };

    public void  viewResList(ArrayList<Resturant> resturantlist){
        for (Resturant r:resturantlist){
            System.out.println(r);
        }
    };

    public void addRes(){
        System.out.println("Enter resturante name");
        Scanner input1=new Scanner(System.in);
        String name=input1.nextLine();

        System.out.println("Enter resturante phone");
        String phone=input1.nextLine();

        System.out.println("Enter resturante address");
        String address=input1.nextLine();

        System.out.println("Enter resturante rate");
        double rate=input1.nextDouble();


        ArrayList<Dish> menu=new ArrayList<>();

        while (true){
            Dish d2 =new Dish();

            System.out.println("Enter dish name");
            String dishname=input1.next();
            d2.setName(dishname);

            System.out.println("Enter dish category" +
                    "Enter A for APPETIZER" +
                    "Enter M for maincourse" +
                    "Enter D for desert" +
                    "Enter B for Beverages ");
            String category=input1.next().toLowerCase();

            if (category.equals("a")){
                d2.setCategory(Category.APPETIZER);
            }
            if (category.equals("a")){
                d2.setCategory(Category.MAIN_COURSE);
            }
            if (category.equals("D")){
                d2.setCategory(Category.DESSERT);
            }
            if (category.equals("B")){
                d2.setCategory(Category.DRINK);
            }
            System.out.println("Enter price ");
            double price= input1.nextDouble();
            d2.setPrice(price);


            menu.add(d2);
            System.out.println("Do you want to add anthor dish");
            String choise=input1.next().toLowerCase();
            if (choise=="n"){
                break;
            }
        }

       // Resturant es=new Resturant(name,phone,rate,address,);*************


       //Add resturant add es



    };/********** menu **********/

    public void editResDetail(String Resturantname){

         int index =ResturantRepo.getResturantlist().indexOf(Resturantname);


        if(index!=1){
            System.out.println("Do you want to remove the resturant account");
            Scanner choise=new Scanner(System.in);
            char c=choise.next().toLowerCase().charAt(0);
            if (c=='y'){
                ResturantRepo.getResturantlist().remove(index);
            }
            else {
                System.out.println("resturant name: " + ResturantRepo.getResturantlist().get(index).getName() +
                        "resturant address" + ResturantRepo.getResturantlist().get(index).getAddress() +
                        "resturant menu" + ResturantRepo.getResturantlist().get(index).getMenu() +
                        "resturant rate " + ResturantRepo.getResturantlist().get(index).getRating() +
                        "resturant phone " + ResturantRepo.getResturantlist().get(index).getPhone());

                System.out.println("Type the resturant's name");//Show data!!!!!!!!!!!!!
                Scanner input2=new Scanner(System.in);
                String resturants_name =input2.nextLine();

                System.out.println("Type the resturant's address");
                String resturants_address =input2.nextLine();




                /*menu!!!! show menu    any dish you want to change */

                System.out.println(ResturantRepo.getResturantlist().get(index).showMenu());
                System.out.println("Any dish you want to change");
                String chois2=input2.nextLine();


                System.out.println("Do you want to add or delete or edite");
                String chois3=input2.nextLine();

                if (chois3=="add"){

                    System.out.println("Enter dish name");
                    String dish_name=input2.nextLine();

                    System.out.println("Enter dish category");
                    Category dish_category= Category.valueOf(input2.nextLine());

                    System.out.println("Enter dish price");
                    double dish_price=input2.nextDouble();

                    System.out.println("Enter dish discription");
                    String dish_discription=input2.nextLine();

                    Dish d1=new Dish(dish_name,dish_discription,dish_category,dish_price);

                    ResturantRepo.getResturantlist().get(index).addDish(d1);


                }

                if (chois3=="delete"){

                    System.out.println("Enter dish name");
                    String dish_name=input2.nextLine();

                   ResturantRepo.getResturantlist().get(index).removeDish(new Dish(dish_name,"",null,0));


                }

                if (chois3=="edite"){

                    System.out.println("Enter dish name");
                    String dish_name=input2.nextLine();

                    System.out.println("Enter dish category");
                    Category dish_category= Category.valueOf(input2.nextLine());

                    System.out.println("Enter dish price");
                    double dish_price=input2.nextDouble();

                    System.out.println("Enter dish discription");
                    String dish_discription=input2.nextLine();


                    ArrayList<Dish> d=new ArrayList<>();
                    d.add(dish_name,dish_category,dish_price,dish_discription);
                    ResturantRepo.getResturantlist().set(get(index),d);


                }


                System.out.println("Type the resturant's rate");
                double resturants_rate =input2.nextDouble();

                System.out.println("Type the resturant's phone");
                String resturants_phone =input2.nextLine();

                ResturantRepo.getResturantlist().get(index).setName(resturants_name);

                ResturantRepo.getResturantlist().get(index).setPhone(resturants_phone);

                ResturantRepo.getResturantlist().get(index).setAddress(resturants_address);

                ResturantRepo.getResturantlist().get(index).setRating(resturants_rate);


            }



        }
        else {
            System.out.println("This resturante does not exist");
        }

    };//menu **********/



    public void editCustomerDetail(String name){

        int index=CustomerRepo.getCustomerList().indexOf(name);

        if(index!=-1){
            System.out.println("Do you want to remove the client account");
            Scanner choise=new Scanner(System.in);
            char c=choise.next().toLowerCase().charAt(0);
            if (c=='y'){
                CustomerRepo.getCustomerList().remove(index);
            }
            else {
                System.out.println("client name: " + CustomerRepo.getCustomerList().get(index).getName() +
                        "client email" + CustomerRepo.getCustomerList().get(index).getEmail() +
                        "client address" + CustomerRepo.getCustomerList().get(index).getAddress() +
                        "client number" + CustomerRepo.getCustomerList().get(index).getNumber());
                System.out.println("Type the client's name");//Show data!!!!!!!!!!!!!
                Scanner input2=new Scanner(System.in);
                String client_name =input2.nextLine();

                System.out.println("Type the client's email");
                String clients_email =input2.nextLine();

                System.out.println("Type the client's number");
                String clients_number =input2.nextLine();

                System.out.println("Type the client's address");
                String clients_address =input2.nextLine();

                CustomerRepo.getCustomerList().get(index).setName(client_name);

                CustomerRepo.getCustomerList().get(index).setEmail(clients_email);

                CustomerRepo.getCustomerList().get(index).setNumber(clients_number);

                CustomerRepo.getCustomerList().get(index).setAddress(clients_address);

            }

            System.out.println("Do you want to cancle order");
            Scanner input=new Scanner(System.in);
            String chois= input.nextLine();
            if (chois=="cancle"){
                CustomerRepo.getCustomerList().get(index).cancelOrder();
            }


        }
        else{
            System.out.println("This customer does not exist");
        }


    };


    public void removeCustomer (String name){

        int index=CustomerRepo.getCustomerList().indexOf(name);
        if(index!=-1) {
            CustomerRepo.getCustomerList().remove(index);
        }
        else{
            System.out.println("This customer does not exist");
        }
    };

}
