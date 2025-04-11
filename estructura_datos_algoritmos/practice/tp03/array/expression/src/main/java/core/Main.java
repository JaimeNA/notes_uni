package core;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Evaluator ev = new Evaluator();

        System.out.println(ev.infixToPostfix("3 * ( 4 + 8 )"));

        System.out.println(ev.evaluate());

        // float result = Evaluator.evaluate("-9 -1 - 10 2 * / 1 5 - 2 -3 / / *");

        // System.out.println(String.format("The result of the expression is: %f", result));
    }
}