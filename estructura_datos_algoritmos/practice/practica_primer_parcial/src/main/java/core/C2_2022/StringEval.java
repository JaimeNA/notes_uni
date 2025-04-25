package core.C2_2022;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class StringEval {

    public String evaluate(String expression){
        Scanner scannerLine = new Scanner(expression).useDelimiter("\\s+");

        Stack<String> sp = new Stack<>();

        while(scannerLine.hasNext())
        {

            String token = scannerLine.next();
            if (token.matches("\\+|-|/|\\*|\\^")) {

                // First, get operands
                if (sp.isEmpty())
                    throw new RuntimeException("Invalid expression");

                String a = sp.pop();


                if (sp.isEmpty())
                    throw new RuntimeException("Invalid expression");

                String b = sp.pop();

                switch (token) {
                    case "+":
                        sp.push(sum(b, a));
                        break;
                
                    case "-":
                        sp.push(sub(b, a));  
                        break;
                
                    case "*":
                        sp.push(mult(b, a));
                        break;
                
                    case "/":
                        sp.push(div(b, a)); 
                        break;
                
                    case "^":
                        sp.push(pow(b, a)); 
                        break;
                }
            } else if (token.matches("[A-Za-z]+")) {
                sp.push(token);
            }
            else
                throw new RuntimeException("Invalid token");
            
        }

        if (sp.isEmpty())
            throw new RuntimeException("Invalid expression");

        return sp.pop();
    }

    private String sum(String a, String b) {
        return a + b;
    }

    private String sub(String a, String b) {
        return a.replaceFirst(b, "");
    }

    private String mult(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        int i = 0;
        int j = 0;

        String toReturn = new String();

        for (i = 0; i < aArray.length && j < bArray.length; i++) {
            toReturn += "" + aArray[i] + bArray[j++];
        }

        // Copy any remainders
        if (i != aArray.length){
            for (; i < aArray.length; i++)
                toReturn += aArray[i];
        } else {
            for (; j < bArray.length; j++)
                toReturn += bArray[j];
        }

        return toReturn;
    }

    private boolean belongs(char[] arr, char c) {
        for (char t : arr) {
            if (c == t)
                return true;
        }

        return false;
    }

    private String div(String a, String b) {
        char[] toDelete = b.toCharArray();
        String toReturn = new String();

        for (char c : a.toCharArray()) {
            if (!belongs(toDelete, c))
                toReturn += c;
        }
        return toReturn;
    }

    private String pow(String a, String b) {
        String toReturn = new String();

        char[] bArray = b.toCharArray();

        for (int i = 0; i < bArray.length; i++) {
            toReturn += a;
            for (int j = 0; j <= i; j++) {
                toReturn += bArray[j];
            }
        }
        return toReturn;
    }
    
    public static void main(String[] args) {
        StringEval stringEval = new StringEval();

        System.out.println(stringEval.evaluate("AAAAA BBBB +"));
        System.out.println(stringEval.evaluate("AAABBAABB BB -"));
        System.out.println(stringEval.evaluate("AAA BBBBB *"));
        System.out.println(stringEval.evaluate("AAAAABBBCCCDDDAAA AB /"));
        System.out.println(stringEval.evaluate("EE ABCD ^"));

        System.out.println(stringEval.evaluate("AA BB CC DEF ^ * AE / + BC -"));
        System.out.println(stringEval.evaluate("HOLA QUE + TAL COMO ^ ESTAS / BIEN * + BIEN -"));
    }
}
