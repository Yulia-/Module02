package hometask.module02.calculator.MathOperationsForCurrency;

import hometask.module02.calculator.Interfaces.MathOperations;
import hometask.module02.calculator.ValueProperties.Value;

/**
 * Created by Юля on 10.04.2015.
 */
public class Addition implements MathOperations {

    @Override
    public Value calculate (Value rightValue, Value leftValue) throws Exception {
        if (leftValue.getCurrency().equals(rightValue.getCurrency())){
            return new Value(leftValue.getNumber() + rightValue.getNumber(), rightValue.getCurrency());

        } else {
            throw new Exception ("Wrong currencies");
        }

    }
}
