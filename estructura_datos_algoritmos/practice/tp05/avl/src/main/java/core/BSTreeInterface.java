package core;

public interface BSTreeInterface<T extends Comparable<? super T>> {

	void insert(T myData);

	NodeTreeInterface<T> getRoot();
	
	int getHeight();

	boolean contains(T myData);

	T getMax();

	T getMin();

	void delete(T myData);

	void printByLevels();
}
