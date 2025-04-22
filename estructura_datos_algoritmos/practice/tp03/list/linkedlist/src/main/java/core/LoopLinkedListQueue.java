package core;

import java.util.Iterator;

import org.w3c.dom.Node;

public class LoopLinkedListQueue <T extends Comparable<? super T>>{

    private class Node {
		private T data;
		private Node next;
	
		private Node(T data, Node next) {
			this.data= data;
			this.next= next;
		}
		
	}
	
    private Node last;

    public boolean isEmpty() {
        return last == null;
    }

    public void enqueue(T data) {
        if(data == null)
            throw new IllegalArgumentException("Data cannot be null");

        if (isEmpty()) {
            Node aux = new Node(data, null);
            aux.next = aux;

            last = aux;
        } else {
            Node aux = new Node(data, last.next);
            last.next = aux;
            last = aux;
        }

    } 

    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");

        Node first = last.next;
        
        T toReturn = first.data;

        if (first != last) {
            first = first.next;
            last.next = first;
        } else {
            last = null;
        }

        return toReturn;
    }

	public void dump() {
		Node current = last.next;

		while (current!=last ) {
			// avanzo
			System.out.println(current.data);
			current= current.next;
		}
        System.out.println(current.data);
	}
    public static void main(String[] args) {
		
		LoopLinkedListQueue<String> l = new LoopLinkedListQueue<>();
	
		l.enqueue("hola");
		l.enqueue("tal");
		l.enqueue("ah");
		l.enqueue("veo");


        l.dequeue();
        l.dequeue();
        l.dequeue();
        l.dequeue();

		l.enqueue("hola");
		l.enqueue("tal");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");

        l.dump();
	}
}
