package core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements BSTreeInterface<T> {

    private NodeInner<T> root;
    private Traversal traversal;

    class BSTInOrderIterator implements Iterator<T> {
        Stack<NodeTreeInterface<T>> stack;
        NodeTreeInterface<T> current;

        public BSTInOrderIterator() {
            stack= new Stack<>();
            current= root;
        }

        @Override
        public boolean hasNext() {
            return ! stack.isEmpty() || current != null;
        }

        @Override
        public T next() {
            while(current != null) {
                stack.push(current);
                current= current.getLeft();
            }

            NodeTreeInterface<T> elementToProcess= stack.pop();
            current= elementToProcess.getRight();
            return elementToProcess.getData();
        }

    }

    class BSTByLevelIterator implements Iterator<T> {
        NodeInner<T> current = root;
        Queue<NodeInner<T>> queue;

        @Override
        public boolean hasNext() {
            if (queue == null) {
                queue = new LinkedList<>();

                if (root != null)
                    queue.add(root);
            }

            return queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new IllegalStateException();

            current = queue.remove();

            queue.add(current.getLeft());
            queue.add(current.getRight());

            T toReturn = current.getData();

            return toReturn;
        }
    }

    // version iterativa
    public void inOrderIter() {
        Stack<NodeTreeInterface<T>> stack= new Stack<>();
        NodeTreeInterface<T> current = root;
        while ( ! stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current= current.getLeft();
            } else {
                NodeTreeInterface<T> elementToProcess = stack.pop();
                System.out.print(elementToProcess.getData() + "\t");
                current= elementToProcess.getRight();
            }
        }
    }

    @Override
    public Iterator<T> iterator() {

        switch (traversal) {
            case INORDER:
                return new BSTInOrderIterator();
                
            case BYLEVELS:
                return new BSTByLevelIterator();
        }

        throw new IllegalArgumentException("Invalid traversal type");
    }

    @Override
    public void setTraversal(Traversal traversal) {
        this.traversal = traversal;
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

    private String preorderRec(NodeInner<T> current) {  // This one is no good, need to be delegated to NodeInner. Same with others
        
        String toReturn = new String();

        toReturn += current.getData() + " ";
        
        if (current.getLeft() != null)
            toReturn += preorderRec(current.getLeft()) + " ";

        if (current.getRight() != null)
            toReturn += preorderRec(current.getRight()) + " ";

        return toReturn;
    }

    private String inorderRec(NodeInner<T> current) {

        String toReturn = new String();

        if (current.getLeft() != null)
            toReturn += inorderRec(current.getLeft());

        toReturn += current.getData() + " ";
        
        if (current.getRight() != null)
            toReturn += inorderRec(current.getRight());

        return toReturn;
    }

    private String postorderRec(NodeInner<T> current) {
        
        String toReturn = new String();

        if (current.getLeft() != null)
            toReturn += postorderRec(current.getLeft()) + " ";

        if (current.getRight() != null)
            toReturn += postorderRec(current.getRight()) + " ";

        toReturn += current.getData() + " ";
        
        return toReturn;
    }

    @Override
    public NodeInner<T> getRoot() {
        return root;
    }

    @Override
    public int getHeight() {
		return getHeightRec(root) - 1; // Dont count the root node
	}

	public int getHeightRec(NodeInner<T> current) {
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

    private boolean containsRec(NodeInner<T> current, T myData) {

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

    private T getMaxRec(NodeInner<T> current) {

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

    private T getMinRec(NodeInner<T> current) {

        if (current.getLeft() == null)
            return current.getData();

        return getMinRec(current.getLeft());
    }

    private boolean isEmpty() {
        return root == null;
    }

    @Override
    public void delete(T myData) {
        if (myData == null)
            throw new IllegalArgumentException("Argument cannot be null");

        if (root != null)
            root = deleteRec(root, myData);
    }

    private NodeInner<T> deleteRec(NodeInner<T> current, T myData) {
        if (current == null)
            return null;

        int cmp = current.getData().compareTo(myData);

        NodeInner<T> next = current.getRight();

        if (cmp < 0)
            next = current.getRight();
        else if (cmp > 0)
            next = current.getLeft();
        else {
            if (current.isLeaf())
                return null;

            if (current.getLeft() == null)
                return current.getLeft();

            if (current.getRight() == null)
                return current.getRight();
                
            // Has both childs
            T toReplace = getAdyacentLexi(current).getData();

            current = deleteRec(current, toReplace);

            current.setData(toReplace);
        }

        return deleteRec(next, myData);
    }

    private NodeInner<T> getAdyacentLexi(NodeInner<T> toDelete) {

        NodeInner<T> current = toDelete.getLeft();

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current;
    }

    @Override
    public void printByLevels() {
        if (root == null)
            return;
        // create an empty queue and enqueue the root node
        Queue<NodeInner<T>> queue = new LinkedList<>();
        queue.add (root);
        NodeInner<T> currentNode;
        
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
