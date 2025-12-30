package Talabat;

import java.util.Scanner;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Payment {

    private int transactionID;
    private String payMethod;
    private double amount;

    private String cardNumber;
    private YearMonth expiredDate;
    private String cvv;

    private Presentable presenter;

    private Payment(String payMethod, double amount, int transactionID) {
        this.payMethod = payMethod;
        this.amount = amount;
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
            presenter.print("Invalid Expired Date :( ");
            presenter.print("Enter the correct Expired again (MM/yyyy): ");
            inputDate = presenter.read();
        }


    }


    // card payment function
    private boolean pay(String cardNumber, YearMonth expiredDate,  String cvv){
        return true;
    }

    // cash payment function
    private boolean pay(){
        return true;
    }

    public void processPayment()  {
        String paymentWay;
        while(true){
            presenter.print("Enter way of Payment (v for Visa : c for Cash): ");
            paymentWay = presenter.read();
            paymentWay = paymentWay.toLowerCase();
            if(paymentWay.equals("c")   || paymentWay.equals("v") )    { break; }
            else{
                presenter.print("Payment Method should be visa or cash -- \nEnter paymethod again should match (c / v): ");
            }
        }
        setPayMethod(paymentWay);

        if (payMethod.equals( "c")){
            pay();
        }
        else if (payMethod.equals("v")) {
            presenter.print("Enter Card number: ");
            cardNumber = presenter.read();

            presenter.print("Enter expired date (MM/yyyy)  : ");
            String inputDate = presenter.read();
            formatingExpiredDate(inputDate);

            presenter.print("Enter your CVV (3 numbers in the back of the Card): ..." );
            cvv = presenter.read();
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

    public void setCvv(String cvv) { this.cvv = cvv; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public YearMonth getExpiredDate() { return expiredDate; }

    public int getTransactionID() { return transactionID; }

    public String getPayMethod() { return payMethod; }

    public double getAmount() { return amount; }

    public String getCardNumber() { return cardNumber; }

    public String getCvv() { return cvv; }

}
