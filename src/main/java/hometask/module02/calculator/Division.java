package hometask.module02.calculator;

/**
 * Created by Юля on 10.04.2015.
 */
public class Division implements MathOperations {
    Double rightOperand;
    String rightCurrency;
    Double leftOperand;
    String leftCurrency;

    public Division(Value rightValue, Value leftValue) {
        rightOperand = rightValue.getNumber();
        rightCurrency = rightValue.getCurrency();
        leftOperand = leftValue.getNumber();
        leftCurrency = leftValue.getCurrency();
    }

    @Override
    public Value calculation (Value rightValue, Value leftValue) throws Exception {
        if (leftCurrency.equals(rightCurrency)&& leftCurrency.equals(null)||rightCurrency.equals(null)) {
            return new Value (leftOperand/rightOperand, leftCurrency);
        }
        else {
            throw new Exception("Wrong currencies");
        }
    }
}
