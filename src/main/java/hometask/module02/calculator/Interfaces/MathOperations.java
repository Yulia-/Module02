package hometask.module02.calculator.Interfaces;

import hometask.module02.calculator.ValueProperties.Value;

/**
 * Created by Юля on 10.04.2015.
 */
public interface MathOperations {

    Value calculate(Value val1, Value val2) throws Exception;
}
