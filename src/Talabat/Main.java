package Talabat;

public class Main {
    public static void main(String[] args) {
        ConsolePresenter presenter = new ConsolePresenter();
        CustomerRepo.setPresenter(presenter);
        Customer currCustomer;


        presenter.print("\t \t ----------Welcome to TALABAT----------\n\n");
        while(true){
            presenter.print("1- Log in \n" +
                    "2- Register \n" +
                    "Enter your choice :");

            String choice = presenter.read();
            if(choice.equals("1")){
                currCustomer = Customer.addCustomer(presenter);
            }else if(choice.equals("2")){
                currCustomer = Customer.findCustomer(presenter);
            }
        }
    }
}