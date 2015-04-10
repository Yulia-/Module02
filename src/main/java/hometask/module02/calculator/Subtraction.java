package hometask.module02.calculator;

/**
 * Created by Юля on 10.04.2015.
 */
public class Subtraction implements MathOperations {
    Double rightOperand;
    String rightCurrency;
    Double leftOperand;
    String leftCurrency;

    public Subtraction(Value rightValue, Value leftValue) {
        rightOperand = rightValue.getNumber();
        rightCurrency = rightValue.getCurrency();
        leftOperand = leftValue.getNumber();
        leftCurrency = leftValue.getCurrency();
    }

    @Override
    public Value calculation (Value rightValue, Value leftValue) throws Exception {
        if (leftCurrency.equals(rightCurrency)) {
            return new Value(leftOperand - rightOperand, rightCurrency);
        } else {
            throw new Exception("Wrong currencies");
        }

    }
}
