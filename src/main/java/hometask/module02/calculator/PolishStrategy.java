package hometask.module02.calculator;

import java.util.LinkedList;

/**
 * Created by Юля on 30.03.2015.
 */
public class PolishStrategy implements CalculationStrategy {
    @Override
    public String calculate(String expression) {

        LinkedList<Double> numbers = new LinkedList<>();
        LinkedList<Character> operators = new LinkedList<>();
        //LinkedList<String> currency = new LinkedList<>();

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
                while (i < expression.length() && (Character.isDigit(currentChar)) || (isPoint(currentChar))){
                    value.append(expression.charAt(i++));
                }
                numbers.add(Double.parseDouble(value.toString()));
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

    private void processOperator(LinkedList<Double> st, char op) {
        Double rightOperand = st.removeLast();
        Double leftOperand = st.removeLast();
        switch (op) {
            case '+':
                st.add(leftOperand + rightOperand);
                break;
            case '-':
                st.add(leftOperand - rightOperand);
                break;
            case '*':
                st.add(leftOperand * rightOperand);
                break;
            case '/':
                st.add(leftOperand / rightOperand);
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println(new PolishStrategy().calculate(String.valueOf(10 * (3.3 + 2.7 ))));
    }
}
