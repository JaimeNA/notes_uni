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

// 		---------------- BinaryTreeParametric is more complete ------------------
// This one is not parametric and the equals implementation doesnt work
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
		System.out.println(printHierarchyRec(root, ""));
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

				toReturn += " ";
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o instanceof BinaryTreeAlt other) {

			if (this.root == null || other.root == null)
				return this.root == other.root;

			return this.root.equals(other.root);
		}

		return false;
	}


	@Override
	public int getHeight() {
		return getHeightRec(root) - 1; // Dont count the root node
	}

	public int getHeightRec(Node current) {
		if (current == null)
			return 0;

		int leftHeight = getHeightRec(current.left);
		int rightHeight = getHeightRec(current.right);

		if (leftHeight > rightHeight)
			return getHeightRec(current.left) + 1;

		return getHeightRec(current.right) + 1;
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

		@Override
		public boolean equals(Object o){
			
			if (o instanceof Node other) {

				boolean dataEquals, leftEquals, rightEquals;

				dataEquals = other.data.equals(this.data);

				if (other.left == null || this.left == null) {
					leftEquals = other.left == this.left;
				} else {
					leftEquals = other.left.equals(this.left);
				}

				if (other.right == null || this.right == null) {
					rightEquals = other.right == this.right;
				} else {
					rightEquals = other.right.equals(this.right);
				}

				return dataEquals && leftEquals && rightEquals;
			}

			return false;
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
		System.out.println(rta.getHeight());

		// try {
		// 	rta.toFile("tp05/binarytree/src/main/resources/test");
		// } catch (IOException e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

		BinaryTreeService rtaReconstructed = new BinaryTreeAlt("test");

		rta.printHierarchy();
		rtaReconstructed.printHierarchy();

		System.out.println(String.format("Los arboles son %s\n", rta.equals(rtaReconstructed) ? "iguales" : "diferentes"));
	}

}