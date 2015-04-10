package hometask.module02.calculator;

import java.util.LinkedList;


/**
 * Created by Юля on 30.03.2015.
 */
public class PolishStrategy implements CalculationStrategy {
    @Override
    public String calculate(String expression) throws Exception {

        LinkedList<Value> numbers = new LinkedList<>();
        LinkedList<Character> operators = new LinkedList<>();


        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (isDelimiter(currentChar)) {
                continue;
            }

            if (currentChar == '(') {
                operators.add('(');
            } else if (currentChar == ')') {
                while (operators.getLast() != '(') {
                    processOperator(numbers, operators.removeLast());
                }
                operators.removeLast();
            } else if (isOperator(currentChar)) {
                while (!operators.isEmpty()
                        && getPriority(operators.getLast()) >= getPriority(currentChar)) {
                    processOperator(numbers, operators.removeLast());
                }
                operators.add(currentChar);

            } else {
                StringBuilder value = new StringBuilder();
                StringBuilder nums = new StringBuilder();
                StringBuilder curr = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(currentChar)) || (isPoint(currentChar) ||
                        (isCurrency(currentChar)))) {
                    value.append(expression.charAt(i++));
                    currentChar = expression.charAt(i);


                    if (Character.isDigit(currentChar) || (isPoint(currentChar))) {
                        nums.append(currentChar);
                    }
                    if (isCurrency(currentChar)) {
                        curr.append(currentChar);
                    }
                }


                numbers.add(new Value(Double.parseDouble(nums.toString()), curr.toString()));

                --i;
            }
        }

            while (!operators.isEmpty()) {
                processOperator(numbers, operators.removeLast());
            }

            return String.valueOf(numbers.getFirst());

    }


    private boolean isDelimiter(char c) {
        return c == ' ';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isPoint(char c) {
        return c == '.';
    }

    private boolean isCurrency(char c) {
        return c == '$' || c == '€';
    }


    private int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private void processOperator(LinkedList<Value> values, char op) throws Exception {
        Value rightValue = values.removeLast();
        Value leftValue = values.removeLast();
        switch (op) {
            case '+':
                values.add(new Addition(rightValue, leftValue).calculation(rightValue, leftValue));
                break;
            case '-':
                values.add(new Subtraction(rightValue, leftValue).calculation(rightValue, leftValue));
                break;
            case '*':
                values.add(new Multiplication(rightValue, leftValue).calculation(rightValue, leftValue));
                break;
            case '/':
                values.add(new Division(rightValue, leftValue).calculation(rightValue, leftValue));
                break;
        }
    }




     public static void main(String[] args) {
        try {
            System.out.println(new PolishStrategy().calculate("10$ + 2$"));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
