package Talabat;

import java.util.Scanner;

public class ConsolePresenter implements Presentable {

    @Override
    public void print(Object obj) {
        System.out.println(obj);
    }

    @Override
    public String read() {
        Scanner input= new Scanner(System.in);
        return input.next() ;
    }
}
