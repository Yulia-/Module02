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
        LinkedList<Character> currency = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (isDelimiter(currentChar)) {
                continue;
            }
            if (isPoint(currentChar)) {
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
            /*} else if(currentChar == '$'){
                currency.add("$");
            } else if (currentChar == 'e'){
                if (currentChar == 'u')
                    continue;
                if (currentChar == 'r')
                    continue;
                currency.add("eur");*/
            } else if(isCurrency(currentChar)){
                if (currentChar == '$'){
                    currency.add('$');
                    if (currentChar == 'e' ||currentChar == 'u' || currentChar == 'r'){
                        throw new IllegalArgumentException("Wrong currencies");
                    }
                }
                if (currentChar == 'e' ||currentChar == 'u' || currentChar == 'r'){
                    currency.add(currentChar);
                    if (currentChar == '$'){
                        throw new IllegalArgumentException("Wrong currencies");
                    }
                }


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
    private boolean isCurrency(char c) {
        return c == '$' || c == 'e' || c == 'u' || c == 'r';
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
        try {
            System.out.println(new PolishStrategy().calculate("10 * 2"));
        }catch (NumberFormatException e){
            System.out.println(e.getStackTrace());
        }
    }
}
