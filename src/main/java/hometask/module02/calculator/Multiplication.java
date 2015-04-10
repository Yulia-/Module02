package hometask.module02.calculator;

/**
 * Created by Юля on 10.04.2015.
 */
public class Multiplication implements MathOperations {
    Double rightOperand;
    String rightCurrency;
    Double leftOperand;
    String leftCurrency;

    public Multiplication(Value rightValue, Value leftValue) {
        rightOperand = rightValue.getNumber();
        rightCurrency = rightValue.getCurrency();
        leftOperand = leftValue.getNumber();
        leftCurrency = leftValue.getCurrency();
    }

    @Override
    public Value calculation(Value rightValue, Value leftValue ) throws Exception {
        if (leftCurrency.equals(rightCurrency) && leftCurrency.equals(null) ||
                (!leftCurrency.equals(rightCurrency)) && rightCurrency.equals(null) && leftCurrency.equals(null)) {

            if (leftCurrency.equals(null)) {
                return new Value(leftOperand * rightOperand, rightCurrency);
            } else {
                return new Value(leftOperand * rightOperand, leftCurrency);
            }
        } else {
            throw new Exception("Wrong currencies");
        }

    }
}
