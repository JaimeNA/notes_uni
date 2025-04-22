package core.C1_2023;

public class SimpleLinkedList <T>{
    private Node root = null;
    private Node last = null;

    public void dump() {
        Node current = root;

        while (current!=null ) {
            // avanzo
            System.out.println(current.data);
            current= current.next;
        }
    }

    public void add(T elem) {
        if (root == null) {
            root = new Node(elem, null);
            last = root;
        } else {
            Node aux = new Node(elem, null);
            last.next = aux;
            last = aux;
        }
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
