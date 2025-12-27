package Talabat;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Payment {

    private int transactionID;
    private String payMethod;
    private double amount;

    private String cardNumber;
    private YearMonth expiredDate;
    private int cvv;

    Scanner scan = new Scanner(System.in);

    private Payment(String payMethod, double amount, int transactionID) {
        this.payMethod = payMethod;
        try {
            setAmount(amount);
        } catch(Exception e){
            while(true){
                System.out.println(e.getMessage());
                System.out.println("Enter the Amount again :)  ");
                double enteringAmount  = scan.nextDouble();
                if(enteringAmount > 0){
                    setAmount(enteringAmount);
                    break;
                }
            }
        }
        this.transactionID = transactionID;
    }
    public Payment(String payMethod, double amount) {
            this(payMethod, amount, (int) Math.random() * 100000 + 1000 );
    }
    public Payment(){
        this("c", 0);
    }

    public Payment(Payment otherPayment){
        this(otherPayment.payMethod, otherPayment.amount, otherPayment.transactionID);

    }

    private void formatingExpiredDate(String inputDate)  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        while(true){
            if (inputDate.matches("(0[1-9]|1[0-2])/\\d{4}")) {
                setExpiredDate(YearMonth.parse(inputDate, formatter));
                break;
            }
            System.out.println("Invalid Expired Date :( ");
            System.out.println("Enter the correct Expired again (MM/yyyy): ");
            inputDate = scan.next();
        }


    }


    // card payment function
    private boolean pay(String cardNumber, YearMonth expiredDate,  int cvv){
        return true;
    }

    // cash payment function
    private boolean pay(){
        return true;
    }

    public void processPayment()  {
        String paymentWay;
        while(true){
            System.out.println("Enter way of Payment (v for Visa : c for Cash): ");
            paymentWay = scan.next();
            paymentWay = paymentWay.toLowerCase();
            if(paymentWay.equals("c")   || paymentWay.equals("v") )    { break; }
            else{
                System.out.println("Payment Method should be visa or cash -- \nEnter paymethod again should match (c / v): ");
            }
        }
        setPayMethod(paymentWay);

        if (payMethod.equals( "c")){
            pay();
        }
        else if (payMethod.equals("v")) {
            System.out.println("Enter Card number: ");
            cardNumber = scan.next();

            System.out.println("Enter expired date (MM/yyyy)  : ");
            String inputDate = scan.next();
            formatingExpiredDate(inputDate);
            
            System.out.println("Enter your CVV (3 numbers in the back of the Card): ..." );
            cvv = scan.nextInt();
            setCvv(cvv);

            pay(cardNumber, expiredDate, cvv);
        }
    }

    public void setAmount(double amount) throws IllegalArgumentException {
        if (amount <= 0){
            throw new IllegalArgumentException("Amount should be greater than 0 :( ");
        }
        this.amount = amount;
    }

    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

    public void setExpiredDate(YearMonth expiredDate) { this.expiredDate = expiredDate; }

    public void setCvv(int cvv) { this.cvv = cvv; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public YearMonth getExpiredDate() { return expiredDate; }

    public int getTransactionID() { return transactionID; }

    public String getPayMethod() { return payMethod; }

    public double getAmount() { return amount; }

    public String getCardNumber() { return cardNumber; }

    public int getCvv() { return cvv; }

}
