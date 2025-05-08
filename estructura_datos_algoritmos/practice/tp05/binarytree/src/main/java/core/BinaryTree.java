package core;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


// 		---------------- BinaryTreeAlt is more complete ------------------
// This one has the enum implementation on buildTree
public class BinaryTree implements BinaryTreeService {
	
	private Node root;

	private Scanner inputScanner;

	public BinaryTree(String fileName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, FileNotFoundException {
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
		 pendingOps.add(new NodeHelper(root, NodeHelper.Action.CONSUMIR));
		 
		 while(inputScanner.hasNext()) {
			 
			 token= inputScanner.next();

			 NodeHelper aPendingOp = pendingOps.remove();
			 Node currentNode = aPendingOp.getNode();
			 
			 if ( token.equals("?") ) {
				 // no hace falta poner en null al L o R porque ya esta con null
				 
			 // reservar el espacio en Queue aunque NULL no tiene hijos para aparear
				pendingOps.add( new NodeHelper(null, NodeHelper.Action.CONSUMIR) );  // como si hubiera izq
				pendingOps.add( new NodeHelper(null, NodeHelper.Action.CONSUMIR) );  // como si hubiera der
			 }
			 else {
				 switch (aPendingOp.getAction()) 
				 {
				 	case LEFT: 
				 		currentNode = currentNode.setLeftTree(new Node() );
				 		break;
				 	case RIGHT:
				 		currentNode = currentNode.setRightTree(new Node());
				 		break;
				 }
				 
			 
				 // armo la info del izq, der o el root
				 currentNode.data= token;

				 
				 // hijos se postergan
				 pendingOps.add(new NodeHelper(currentNode, NodeHelper.Action.LEFT));
				 pendingOps.add(new NodeHelper(currentNode, NodeHelper.Action.RIGHT));
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

	private String printHierarchyRec(Node current, String padding) {	// Correct version in BinaryTreeAlt, BinaryTreeAlt is more complete

		String toReturn = new String();

		toReturn += padding + "└── " + current.data + "\n";

		if (current.left != null)
			toReturn += padding + printHierarchyRec(current.left, padding + "  ");
		
		if (current.right != null)
			toReturn += padding + printHierarchyRec(current.right, padding + "  ");

		return toReturn;
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
		
		static enum Action { LEFT, RIGHT, CONSUMIR };
		
		
		private Node aNode;
		private Action anAction;
		
		public NodeHelper(Node aNode, Action anAction ) {
			this.aNode= aNode;
			this.anAction= anAction;
		}
		
		
		public Node getNode() {
			return aNode;
		}
		
		public Action getAction() {
			return anAction;
		}
		
	}
	

	
	public static void main(String[] args) throws FileNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		BinaryTreeService rta = new BinaryTree("data0_3");
		rta.preorder();
		rta.postorder();
		
		rta.printHierarchy();
	}

	@Override
	public void toFile(String filename) throws IOException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toFile'");
	}

}  