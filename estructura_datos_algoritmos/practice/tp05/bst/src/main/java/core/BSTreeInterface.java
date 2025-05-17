package core;

public interface BSTreeInterface<T extends Comparable<? super T>> extends Iterable<T> {

	enum Traversal { BYLEVELS, INORDER}

	void setTraversal(Traversal traversal);

	void insert(T myData);

	void preOrder();

	void postOrder();

	void inOrder();

	NodeTreeInterface<T> getRoot();
	
	int getHeight();

	boolean contains(T myData);

	T getMax();

	T getMin();

	void delete(T myData);

	void printByLevels();

	int getOcurrences(T element);

	public T Kesimo(int k);

	T getCommonNode(T element1, T element2);
}
