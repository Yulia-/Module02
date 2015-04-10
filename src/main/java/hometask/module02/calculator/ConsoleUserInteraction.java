package hometask.module02.calculator;

import java.util.Scanner;

/**
 * Created by Юля on 10.04.2015.
 */
public class ConsoleUserInteraction {

    public static void main(String[] args) {

        CalculationStrategy calcStrategy = new PolishStrategy();

        System.out.println("Currency Converter: \n" +
                "\tFor exit enter: exit");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter an expression to be calculated: ");
                // 100*(3+2*(5-1))+(10-1)
                String enteredExp = scanner.nextLine();
                if ("exit".equalsIgnoreCase(enteredExp)) {
                    break;
                }
                System.out.println(calcStrategy.calculate(enteredExp));
            }
        } catch (Exception exc) {
            System.out.println("The following error happened during processing your expression\n\t" + exc);
        }

        System.out.print("\nBye-bye!");
    }
}
