package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class Evaluator {

	private static Map<String, Integer> mapping = new HashMap<String, Integer>()
	{   { put("+", 0); put("-", 1); put("*", 2); put("/", 3); } };
	 
	private static boolean[][] precedenceMatriz=
	{   { true,  true,  false, false },
		{ true,  true,  false, false },
		{ true,  true,  true,  true  },
		{ true,  true,  true,  true },
	};

    private Scanner scannerLine;

    @SuppressWarnings("resource")   // Horrible code provided by teachers
    public Evaluator()
    {
        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notacion postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();
        String parsedLine = infixoPostfix(line);

        scannerLine = new Scanner(parsedLine).useDelimiter("\\s+"); // Parse to postfix then store it

        inputScanner.close();

    }

    /*
     * Recieves expression as String with Whitespaces as tokens between operators and operands.
     * Uses a Stack internatlly.
     */
    public Double evaluate()
    {
        Stack<Double> sp = new Stack<>();

        HashMap<String, BinaryOperator<Double>> table = new HashMap<>();    // Switch alternative, allows for future expansion

        while( scannerLine.hasNext() )
        {

            String token = scannerLine.next();
            System.out.println(token);
            if (token.matches("\\+|-|/|\\*")) {

                // First, get operands
                if (sp.isEmpty())
                    throw new RuntimeException("Invalid expression");
                Double a = sp.pop();


                if (sp.isEmpty())
                    throw new RuntimeException("Invalid expression");
                Double b = sp.pop();

                switch (token) {
                    case "+":
                        sp.push(a + b);
                        break;
                
                    case "-":
                        sp.push(b - a);   // Not conmutative like sum
                        break;
                
                    case "*":
                        sp.push(a * b);
                        break;
                
                    case "/":
                        sp.push(b / a); 
                        break;
                }
            } else if (token.matches("-{0,1}[0-9]+")) {
                Double num = Double.parseDouble(token);
                sp.push(num);
            }
            else
                throw new RuntimeException("Invalid token");
            
        }

        if (sp.isEmpty())
            throw new RuntimeException("Invalid expression");

        return sp.pop();
    }

    /*
     * Recieves espression in infix notation and returns it in postfix notation.
     * Uses Option 1 from TP
     */
    private String infixoPostfix(String str) {
        Stack<String> sp = new Stack<>();
        String s = "";

        Scanner lineScanner = new Scanner(str).useDelimiter("\\s+");    // I would prefer to use string.split

        while(lineScanner.hasNext() )
        {
            String token = lineScanner.next();
            
            if (token.matches("\\+|-|/|\\*")) {
                if (sp.isEmpty()) {
                    sp.push(token);
                } else if (getPrecedence(sp.peek(), token)){
                    s += sp.pop().toString();
                    sp.push(token);
                } else {
                    sp.push(token);
                }
            } else if (token.matches("\\d+")) {
                s += token.toString();
            }
            else {
                throw new RuntimeException("Invalid token");
            }
         
            s += " ";
        }

        // Append the remaining ones
        while(!sp.isEmpty()) {
            s += sp.pop().toString();
            s += " ";
        }

        lineScanner.close();

        return s.toString();
    }

	private boolean getPrecedence(String tope, String current)
	{
		Integer topeIndex;
		Integer currentIndex;
	
		if ((topeIndex= mapping.get(tope))== null)
			throw new RuntimeException(String.format("Top operator %s not found", tope));
		
		if ((currentIndex= mapping.get(current)) == null)
			throw new RuntimeException(String.format("Current operator %s not found", current));

		return precedenceMatriz[topeIndex][currentIndex];
	}
}
