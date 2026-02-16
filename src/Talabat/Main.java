package Talabat;

public class Main {
    public static void main(String[] args) {
        ConsolePresenter presenter = new ConsolePresenter();
        User currUser;
        SystemActions.setPresenter(presenter);

        currUser = SystemActions.startSystem();


    }
}