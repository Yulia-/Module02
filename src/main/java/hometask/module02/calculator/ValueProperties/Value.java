package hometask.module02.calculator.ValueProperties;

/**
 * Created by Юля on 10.04.2015.
 */
public class Value {

    private double number;
    private String currency;

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Value(double number, String currency) {
        this.setNumber(number);
        this.setCurrency(currency);
    }

    @Override
    public String toString() {
        if (currency==null){
            return number + " ";
        }
        return number + currency;
    }
}
