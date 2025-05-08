package core;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Function;



public class BinaryTreeAlt implements BinaryTreeService {
	
	private Node root;

	private Scanner inputScanner;

	public BinaryTreeAlt(String fileName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException {
		 InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

		 if (is == null)
			 throw new FileNotFoundException(fileName);
		 
		 inputScanner = new Scanner(is);
		 inputScanner.useDelimiter("\\s+");

		 buildTree();
		
		 inputScanner.close();
	}
	

	
	private void buildTree() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	
	
		Queue<NodeHelper> pendingOps= new LinkedList<>();
		String token;
		 
		root= new Node();
		pendingOps.add(new NodeHelper(root, n -> n));
		 
		while(inputScanner.hasNext()) {
			 
			token= inputScanner.next();

			NodeHelper aPendingOp = pendingOps.remove();
			Node currentNode = aPendingOp.getNode();
			 
			if ( token.equals("?") ) {
				// no hace falta poner en null al L o R porque ya esta con null
				 
			// reservar el espacio en Queue aunque NULL no tiene hijos para aparear
				pendingOps.add( new NodeHelper(null, n -> n) );  // como si hubiera izq
				pendingOps.add( new NodeHelper(null, n -> n) );  // como si hubiera der
			}
			else {
				currentNode = aPendingOp.getAction().apply(currentNode);
			 
				// armo la info del izq, der o el root
				currentNode.data= token;

				 
				// hijos se postergan
				pendingOps.add(new NodeHelper(currentNode, node -> node.setLeftTree(new Node())));
				pendingOps.add(new NodeHelper(currentNode, node -> node.setRightTree(new Node())));
			}
	
				 
		}
			
		if (root.data == null)  // no entre al ciclo jamas 
		    root= null;
		 
	 }
	
    
	@Override
	public void preorder() {
        System.out.println(preorderRec(root));
	}
	
	@Override
	public void postorder() {
        System.out.println(postorderRec(root));
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

    private String postorderRec(Node current) {
        
        String toReturn = new String();

        if (current.left != null)
            toReturn += postorderRec(current.left) + " ";

        if (current.right != null)
            toReturn += postorderRec(current.right) + " ";

        toReturn += current.data + " ";
        
        return toReturn;
    }

	// Uses ├── , │  and └── 
	public void printHierarchy() {
		//System.out.println(printHierarchyRec(root, ""));
		System.out.println(getTree());
	}

	private String printHierarchyRec(Node current, String padding) {

		String toReturn = new String();

		if (current == null) {
			return padding + "└── null\n";
		}
			
		toReturn += padding + "└── " + current.data + "\n";

		toReturn += padding + printHierarchyRec(current.left, padding + "  ");
		toReturn += padding + printHierarchyRec(current.right, padding + "  ");

		return toReturn;
	}

	@Override
	public void toFile(String filename) throws IOException {
			FileWriter myWriter = new FileWriter(filename);
			
			myWriter.write(getTree());
			
			myWriter.close();
	}

	public String getTree() {

		Queue<Node> prevLevel = new LinkedList<>();	// Can be done with just two queue
		prevLevel.add(root);

		// First one
		Queue<Node> level = new LinkedList<>();

		String toReturn = new String();

		while (!isEmptyLevel(prevLevel)) {
			
			while (!prevLevel.isEmpty()) {
				Node aux = prevLevel.remove();

				if (aux != null) {
					toReturn += aux.data;
					level.add(aux.left);
					level.add(aux.right);
				} else {
					toReturn += "?";
					level.add(null);
					level.add(null);
				}

			}

			//toReturn += "\n"; 	// For debugging
			prevLevel = level;
			level = new LinkedList<>();
		}

		return toReturn;
	}

	private boolean isEmptyLevel(Queue<Node> level) {
		for (Node n : level) {
			if (n != null)
				return false;
		}

		return true;
	}

	// hasta el get() no se evalua
	class Node {
		private String data;
		private Node left;
		private Node right;
		
		public Node setLeftTree(Node aNode) {
			left= aNode;
			return left;
		}
		
		
		public Node setRightTree(Node aNode) {
			right= aNode;
			return right;
		}
		
		
		
		public Node() {
			// TODO Auto-generated constructor stub
		}

		private boolean isLeaf() {
			return left == null && right == null;
		}


	}  // end Node class

	
	static class NodeHelper {
		
		private Node aNode;
		Function<Node, Node> anAction;
		
		public NodeHelper(Node aNode, Function<Node, Node> anAction) {
			this.aNode= aNode;
			this.anAction= anAction;
		}
		
		
		public Node getNode() {
			return aNode;
		}
		
		public Function<Node, Node> getAction() {
			return anAction;
		}
		
	}
	

	
	public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BinaryTreeService rta = new BinaryTreeAlt("data0_3");
		rta.preorder();
		rta.postorder();
		
		rta.printHierarchy();
	}

}  