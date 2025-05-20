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
    public NodeInner<T> getLeft() {
        return left;
    }

    @Override
    public NodeInner<T> getRight() {
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

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public boolean hasChild() {
        return left != null || right != null;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setRight(NodeInner<T> right) {
        this.right = right;
    }

    public void setLeft(NodeInner<T> left) {
        this.left = left;
    }

    // ------ Version not mine ------ 
    // Delete delegated to the node
    public NodeInner<T> delete(T myData){
        int c = myData.compareTo(this.data);
        if(c < 0){
            if(left != null)
                left = left.delete(myData);
            return this;
        }
        if(c > 0){
            if(right != null)
                right = right.delete(myData);
            return this;
        }
        // Found it!

        if(left == null)
            return right;
        if(right == null)
            return left;

        // Get inorder predecessor
        this.data = (T) getPredec(left);
        //Ahora borramos el Predec de donde estaba
        left = left.delete(this.data);
        return this;
    }

	private T getPredec(NodeInner<T> current){
        NodeInner<T> aux = current;
        while(aux.right != null)
            aux = aux.right;
        return aux.data;
    }
}
