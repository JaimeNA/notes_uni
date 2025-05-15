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

public class BinaryTreeParametric<T> implements BinaryTreeParametricService<T> {
	
	private Node<T> root;

	private Scanner inputScanner;

	public BinaryTreeParametric(String fileName, Class<T> theClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException {
		 InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

		 if (is == null)
			 throw new FileNotFoundException(fileName);
		 
		 inputScanner = new Scanner(is);
		 inputScanner.useDelimiter("\\s+");

		 buildTree(theClass);
		
		 inputScanner.close();
	}
	
    private void buildTree(Class<T> theClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	
	
		Queue<NodeHelper<T>> pendingOps= new LinkedList<>();
		String token;
		 
		root= new Node<>();
		pendingOps.add(new NodeHelper<T>(root, n -> n));
		 
		while(inputScanner.hasNext()) {
			 
			token= inputScanner.next();

			NodeHelper<T> aPendingOp = pendingOps.remove();
			Node<T> currentNode = aPendingOp.getNode();
			 
			if ( token.equals("?") ) {
				// no hace falta poner en null al L o R porque ya esta con null
				 
			// reservar el espacio en Queue aunque NULL no tiene hijos para aparear
				pendingOps.add( new NodeHelper<>(null, n -> n) );  // como si hubiera izq
				pendingOps.add( new NodeHelper<>(null, n -> n) );  // como si hubiera der
			}
			else {
				currentNode = aPendingOp.getAction().apply(currentNode);
			 
				// armo la info del izq, der o el root
				currentNode.data= theClass.getConstructor(String.class).newInstance(token);

				// hijos se postergan
				pendingOps.add(new NodeHelper<>(currentNode, node -> node.setLeftTree(new Node<T>())));
				pendingOps.add(new NodeHelper<>(currentNode, node -> node.setRightTree(new Node<T>())));
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

    private String preorderRec(Node<T> current) {  // This one is no good, need to be delegated to node. Same with others
        
        String toReturn = new String();

        toReturn += current.data + " ";
        
        if (current.left != null)
            toReturn += preorderRec(current.left) + " ";

        if (current.right != null)
            toReturn += preorderRec(current.right) + " ";

        return toReturn;
    }

    private String postorderRec(Node<T> current) {
        
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

	private String printHierarchyRec(Node<T> current, String padding) {

		String toReturn = new String();

		if (current == null) {
			return padding + "└── null\n";
		}
			
		toReturn += padding + "└── " + current.data.toString() + "\n";

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

		Queue<Node<T>> prevLevel = new LinkedList<>();	// Can be done with just two queue
		prevLevel.add(root);

		// First one
		Queue<Node<T>> level = new LinkedList<>();

		String toReturn = new String();

		while (!isEmptyLevel(prevLevel)) {
			
			while (!prevLevel.isEmpty()) {
				Node<T> aux = prevLevel.remove();

				if (aux != null) {
					toReturn += aux.data.toString();
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

	private boolean isEmptyLevel(Queue<Node<T>> level) {
		for (Node<T> n : level) {
			if (n != null)
				return false;
		}

		return true;
	}

	@SuppressWarnings("unchecked")
    @Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o instanceof BinaryTreeParametric other) {
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

	public int getHeightRec(Node<T> current) {
		if (current == null)
			return 0;

		int leftHeight = getHeightRec(current.left);
		int rightHeight = getHeightRec(current.right);

		if (leftHeight > rightHeight)
			return getHeightRec(current.left) + 1;

		return getHeightRec(current.right) + 1;
	}

	// hasta el get() no se evalua
	class Node<E> {
		private E data;
		private Node<E> left;
		private Node<E> right;
		
		public Node<E> setLeftTree(Node<E> aNode) {
			left= aNode;
			return left;
		}
		
		
		public Node<E> setRightTree(Node<E> aNode) {
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

	
	class NodeHelper<E> {
		
		private Node<E> aNode;
		Function<Node<E>, Node<E>> anAction;
		
		public NodeHelper(Node<E> aNode, Function<Node<E>, Node<E>> anAction) {
			this.aNode= aNode;
			this.anAction= anAction;
		}

        public Node<E> getNode() {
			return aNode;
		}
		
		public Function<Node<E>, Node<E>> getAction() {
			return anAction;
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BinaryTreeParametricService<String> rta = new BinaryTreeParametric<>("data0_3", String.class);
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

		BinaryTreeParametricService<String> rtaReconstructed = new BinaryTreeParametric<>("test", String.class);

		rta.printHierarchy();
		rtaReconstructed.printHierarchy();

		System.out.println(String.format("Los arboles son %s\n", rta.equals(rtaReconstructed) ? "iguales" : "diferentes"));
	}

}