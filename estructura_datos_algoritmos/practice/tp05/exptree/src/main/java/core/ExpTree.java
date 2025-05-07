package core;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.BinaryOperator;


public class ExpTree implements ExpressionService {

	private Node root;
	public ExpTree() {
	    System.out.print("Introduzca la expresi�n en notaci�n infija con todos los par�ntesis y blancos: ");

		// token analyzer
	    Scanner inputScanner = new Scanner(System.in).useDelimiter("\\n");
	    String line= inputScanner.nextLine();
	    inputScanner.close();

	    buildTree(line);
	}
	
	private void buildTree(String line) {	
		  // space separator among tokens
		  Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
		  root= new Node(lineScanner);
		  lineScanner.close();
	}
    
    @Override
    public double eval() {
       return evalRec(root);
    }

    private double evalRec(Node current) {
        if (current.left == null || current.right == null)
            return Double.parseDouble(current.data);

        Double a = evalRec(current.right);
        Double b = evalRec(current.left);

        switch (current.data) {
            case "+":
                return a + b;
            
            case "-":
                return a - b;
            
            case "*":
                return a * b;
            
            case "/":
                return a / b;
            
            case "^":
                return Math.pow(a, b);
        }
    
        throw new RuntimeException("Invalid symbol");
    }

    @Override
    public void preorder() {
        System.out.println(root.preorderNode());
    }

    @Override
    public void inorder() {
        System.out.println(root.inorderNode());
    }


    @Override
    public void postorder() {
        System.out.println(root.postorderNode());
    }
	
    private String preorderRec(Node current) {  // This one is no good, need to be delegated to node. Same with others
        
        String toReturn = new String();

        toReturn += current.data + " ";
        
        if (current.left != null)
            toReturn += preorderRec(current.left) + " ";

        if (current.right != null)
            toReturn += preorderRec(current.right) + " ";

        return toReturn;
    }

    private String inorderRec(Node current) {

        String toReturn = new String();

        if (Utils.isOperator(current.data))
            toReturn += "( ";

        if (current.left != null)
            toReturn += inorderRec(current.left) + " ";

        toReturn += current.data + " ";
        
        
        if (current.right != null)
            toReturn += inorderRec(current.right) + " ";

            
        if (Utils.isOperator(current.data))
            toReturn += ") ";

        return toReturn;
    }

    private String postorderRec(Node current) {
        
        String toReturn = new String();

        if (current.left != null)
            toReturn += postorderRec(current.left) + " ";

        if (current.right != null)
            toReturn += postorderRec(current.right) + " ";

        toReturn += current.data + " ";
        
        return toReturn;
    }

	static final class Node {
		private String data;
		private Node left, right;
		
		private Scanner lineScanner;

		public Node(Scanner theLineScanner) {
			lineScanner= theLineScanner;
			
			Node auxi = buildExpression();
			data= auxi.data;
			left= auxi.left;
			right= auxi.right;
			
			if (lineScanner.hasNext() ) 
				throw new RuntimeException("Bad expression");
		}
		
		private Node() 	{
		}

        private String preorderNode() {
        
            String toReturn = new String();
    
            toReturn += this.data + " ";
            
            if (this.left != null)
                toReturn += this.left.preorderNode() + " ";
    
            if (this.right != null)
                toReturn += this.right.preorderNode() + " ";
    
            return toReturn;
        }

        private String inorderNode() {
        
            String toReturn = new String();
    
            if (this.left != null)
                toReturn += "( " + this.left.inorderNode() + " ";
    
            toReturn += this.data + " ";
            
            
            if (this.right != null)
                toReturn += this.right.inorderNode() + " ) ";
    
            return toReturn;
        }

        private String postorderNode() {
            
            String toReturn = new String();

            if (this.left != null)
                toReturn += this.left.postorderNode() + " ";

            if (this.right != null)
                toReturn += this.right.postorderNode() + " ";

            toReturn += this.data + " ";
            
            return toReturn;
        }

        private Node buildExpression() {
			
            Node toReturn = new Node();

            if (lineScanner.hasNext("\\(")) {
                lineScanner.next(); // Consume it

                toReturn.left = buildExpression();

                // Operator
                if (!lineScanner.hasNext()) 
                    throw new RuntimeException("Missing or invalid operator");

                toReturn.data = lineScanner.next();

                if (!Utils.isOperator(toReturn.data))
                    throw new RuntimeException("Missing or invalid operator");

                // Subexpression
                toReturn.right = buildExpression();

                // ) expected
                if (lineScanner.hasNext("\\)")) {
                    // Consume it
                    lineScanner.next();
                } else {
                    throw new RuntimeException("Missing )");
                }

                return toReturn;
            }

            // Constant
            if (!lineScanner.hasNext()) 
                throw new RuntimeException("Missing expression");

            toReturn.data = lineScanner.next();

            if (!Utils.isConstant(toReturn.data))
                throw new RuntimeException(String.format("illegal token %s\n", toReturn.data));

			return toReturn;
		}

	}  // end Node class

	
	
	// hasta que armen los testeos
	public static void main(String[] args) {
		ExpressionService myExp = new ExpTree();
        System.out.println(myExp.eval());
	}

}  // end ExpTree class
