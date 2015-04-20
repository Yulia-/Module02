package hometask.module02.calculator.MathOperationsForCurrency;

import hometask.module02.calculator.Interfaces.MathOperations;
import hometask.module02.calculator.ValueProperties.Value;

/**
 * Created by Юля on 10.04.2015.
 */
public class Division implements MathOperations {

    @Override
    public Value calculate (Value rightValue, Value leftValue) throws Exception {
        if ((leftValue.getCurrency().equals(rightValue.getCurrency())
                && (leftValue.getCurrency().equals(Value.EMPTY_CURRENCY)))
                ||(!leftValue.getCurrency().equals(rightValue.getCurrency())
                && rightValue.getCurrency().equals(Value.EMPTY_CURRENCY))) {
            return new Value (leftValue.getNumber()/rightValue.getNumber(), leftValue.getCurrency());
        }
        else {
            throw new Exception("Wrong currencies");
        }
    }
}
