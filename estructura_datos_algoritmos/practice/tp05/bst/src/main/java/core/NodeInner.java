package core;

public class NodeInner<T extends Comparable<? super T>> implements NodeTreeInterface<T> {

    private T data;
    private NodeInner<T> left;
    private NodeInner<T> right;

    public NodeInner(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public NodeTreeInterface<T> getLeft() {
        return left;
    }

    @Override
    public NodeTreeInterface<T> getRight() {
        return right;
    }

    public void insert(T data) {
        int cmp = data.compareTo(this.data);

        if (cmp > 0) {
            if (this.right == null)
                this.right = new NodeInner<>(data);
            else
                this.right.insert(data);
        }
        else {
            if (this.left == null)
                this.left = new NodeInner<>(data);
            else
               this.left.insert(data);
        }
    }

}
