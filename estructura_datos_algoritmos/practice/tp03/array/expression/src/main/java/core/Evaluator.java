package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class Evaluator {

    private HashMap<String, Double> variables;

	private static Map<String, Integer> mapping = new HashMap<String, Integer>()
	{   { put("+", 0); put("-", 1); put("*", 2); put("/", 3); put("^", 4); put("(", 5); put(")", 6); } };
	 
	private static boolean[][] precedenceMatriz=
	{   { true,  true,  false, false, false, false, true },
		{ true,  true,  false, false, false, false, true },
		{ true,  true,  true,  true, false, false, true },
		{ true,  true,  true,  true, false, false, true },
		{ true,  true,  true,  true, false, false, true },
		{ false, false, false, false, false, false, false },
	};

    private Scanner scannerLine;

    @SuppressWarnings("resource")   // Horrible code provided by teachers
    public Evaluator()
    {
        variables = new HashMap<>();

        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notacion postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();
        String parsedLine = infixToPostfix(line);

        scannerLine = new Scanner(parsedLine).useDelimiter("\\s+"); // Parse to postfix then store it

        inputScanner.close();

    }
    @SuppressWarnings("resource")   // Horrible code provided by teachers
    public Evaluator(HashMap<String, Double> vars)
    {
        this.variables = vars;

        Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
        System.out.print("Introduzca la expresiOn en notacion postfija: ");
        inputScanner.hasNextLine();

        String line = inputScanner.nextLine();
        String parsedLine = infixToPostfix(line);

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
            if (token.matches("\\+|-|/|\\*|\\^")) {

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
                
                    case "^":
                        sp.push(Math.pow(b, a)); 
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
    public String infixToPostfix(String str) {
        Stack<String> sp = new Stack<>();
        String s = "";

        Scanner lineScanner = new Scanner(str).useDelimiter("\\s+");    // I would prefer to use string.split

        while(lineScanner.hasNext() )
        {
            String token = lineScanner.next();
            
            if (isVar(token)) {
                s += variables.get(token).toString();
                s += " ";
            } else if (isOperand(token)) {
                s += token.toString();
                s += " ";
            }
            else {
                while(!sp.isEmpty() && getPrecedence(sp.peek(), token)) {
                    s += sp.pop();
                    s += " ";
                }
                
                if (token.equals(")")) {
                    if(!sp.isEmpty() && sp.peek().equals("("))
                        sp.pop();   // Remove the (
                    else
                        throw new RuntimeException("Mission closing parenthesis");
                } else {
                    sp.push(token);
                }
            }
        }

        // Append the remaining ones
        while(!sp.isEmpty()) {
            if (sp.peek() == "(" || sp.peek() == ")")
                throw new RuntimeException("Stray parentesis found");

            s += sp.pop().toString();
            s += " ";
        }

        lineScanner.close();

        return s.toString();
    }

    private boolean isVar(String str) {
        return variables.containsKey(str);
    }

    private boolean isOperand(String str) {
        try {
            Double.parseDouble(str);
        } catch(NumberFormatException e) {
            return false;
        }

        return true;
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
