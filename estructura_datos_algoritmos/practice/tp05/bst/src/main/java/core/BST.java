package core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    private NodeInner<T> root;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
            }

            @Override
            public T next() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'next'");
            }
            
        };
    }

    @Override
    public void setTraversal(Traversal traversal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTraversal'");
    }

    @Override
    public void insert(T myData) {
        if (root == null)
            root = new NodeInner<>(myData);     // TODO: Check if this is the best way to do it
        else
            root.insert(myData);
    }

    @Override
    public void preOrder() {
        System.out.println(preorderRec(root));
    }

    @Override
    public void postOrder() {
        System.out.println(postorderRec(root));
    }

    @Override
    public void inOrder() {
        System.out.println(inorderRec(root));
    }

    private String preorderRec(NodeTreeInterface<T> current) {  // This one is no good, need to be delegated to NodeInner. Same with others
        
        String toReturn = new String();

        toReturn += current.getData() + " ";
        
        if (current.getLeft() != null)
            toReturn += preorderRec(current.getLeft()) + " ";

        if (current.getRight() != null)
            toReturn += preorderRec(current.getRight()) + " ";

        return toReturn;
    }

    private String inorderRec(NodeTreeInterface<T> current) {

        String toReturn = new String();

        if (current.getLeft() != null)
            toReturn += inorderRec(current.getLeft());

        toReturn += current.getData() + " ";
        
        if (current.getRight() != null)
            toReturn += inorderRec(current.getRight());

        return toReturn;
    }

    private String postorderRec(NodeTreeInterface<T> current) {
        
        String toReturn = new String();

        if (current.getLeft() != null)
            toReturn += postorderRec(current.getLeft()) + " ";

        if (current.getRight() != null)
            toReturn += postorderRec(current.getRight()) + " ";

        toReturn += current.getData() + " ";
        
        return toReturn;
    }

    @Override
    public NodeTreeInterface<T> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
		return getHeightRec(root) - 1; // Dont count the root node
	}

	public int getHeightRec(NodeTreeInterface<T> current) {
		if (current == null)
			return 0;

		int leftHeight = getHeightRec(current.getLeft());
		int rightHeight = getHeightRec(current.getRight());

		if (leftHeight > rightHeight)
			return getHeightRec(current.getLeft()) + 1;

		return getHeightRec(current.getRight()) + 1;
	}

    @Override
    public boolean contains(T myData) {
        return containsRec(root, myData);
    }

    private boolean containsRec(NodeTreeInterface<T> current, T myData) {

        if (current == null)
            return false;

        int cmp = current.getData().compareTo(myData);

        if (cmp == 0)
            return true;

        if (cmp < 0) {  // myData > current data
            return containsRec(current.getRight(), myData);
        }

        return containsRec(current.getLeft(), myData);
    }

    @Override
    public T getMax() {
        if (isEmpty())
            return null;

        return getMaxRec(root);
    }

    private T getMaxRec(NodeTreeInterface<T> current) {

        if (current.getRight() == null)
            return current.getData();

        return getMaxRec(current.getRight());
    }

    @Override
    public T getMin() {
        if (isEmpty())
            return null;

        return getMinRec(root);
    }

    private T getMinRec(NodeTreeInterface<T> current) {

        if (current.getLeft() == null)
            return current.getData();

        return getMinRec(current.getLeft());
    }

    private boolean isEmpty() {
        return root == null;
    }

    @Override
    public void delete(T myData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void printByLevels() {
        if (root == null)
            return;
        // create an empty queue and enqueue the root node
        Queue<NodeTreeInterface<T>> queue = new LinkedList<>();
        queue.add (root);
        NodeTreeInterface<T> currentNode;
        
        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            System.out.print(currentNode.getData() + " ");

            if (currentNode.getLeft() != null)
                queue.add(currentNode.getLeft());
            if (currentNode.getRight() != null)
                queue.add(currentNode.getRight());
        }
        System.out.println();
    }

}
