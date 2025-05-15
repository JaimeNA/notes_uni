package core;

public interface NodeTreeInterface<T extends Comparable<? super T>> {

	T getData();

	NodeTreeInterface<T> getLeft();

	NodeTreeInterface<T> getRight();

	void setData(T data);

	boolean isLeaf();
	boolean hasChild();

}