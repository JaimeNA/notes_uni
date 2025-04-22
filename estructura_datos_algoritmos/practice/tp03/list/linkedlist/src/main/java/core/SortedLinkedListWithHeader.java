package core;

import java.util.Iterator;

import org.w3c.dom.Node;

public class SortedLinkedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T>{

	private Header header;

	public SortedLinkedListWithHeader() {
		super();
		header = new Header();
	}

	@Override
	public boolean insert(T data) {
        boolean inserted = insert2(data);

       header.size += (inserted ? 1 : 0);

		return inserted;
	}

	// insert resuelto todo en la clase SortedLinkedList, iterativo
	private boolean insert1(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = header.first;

		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzocurrent.data == data
			prev= current;
			current= current.next;
		}

		// repetido?
		if (current!=null && current.data.compareTo(data) == 0) {
			System.err.println(String.format("Insertion failed. %s repeated", data));
			return false;
		}

		Node aux= new Node(data, current);

        if (aux.next == null)
            header.last = aux;

		// es el lugar para colocarlo
		if (current == header.first) {
			// el primero es un caso especial: cambia root
			header.last= aux;
		}
		else {
			// nodo interno
			prev.next= aux;
		}
		
		return true;
	}

	
	// insert resuelto todo en la clase SortedLinkedList, recursivo
	private boolean insert2(T data) {
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");
		
		boolean[] rta = new boolean[1];
		header.first = insertRec(data, header.first, rta);
		return rta[0];
	}
	
	
	private Node insertRec(T data, Node current, boolean[] rta) {

		if (current == null || current.data.compareTo(data) > 0) {
			rta[0] = true;
            Node toReturn = new Node(data, current);

            if (current == null)
			header.last = toReturn;

			return toReturn;
		}
		
		if (current.data.equals(data)) {
			System.err.println(String.format("Insertion failed. %s repeated", data));
			rta[0] = false;
			return current;
		}

		current.next = insertRec(data, current.next, rta);

		return current;

	}
	
	// insert resuelto delegando al nodo
	private boolean insert3(T data) {

		if (this.header.first == null) {
			this.header.first = new Node(data, null);
			return true;
		}

		boolean[] rta = new boolean[1];
		header.first = header.first.insert(data, rta);

		return rta[0];
	}
	
	@Override
	public boolean find(T data) {
		return getPos(data) != -1;
	}
	
	@Override
	public boolean remove(T data) {
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");
			
        boolean removed = remove3(data);

        header.size -= removed ? 1 : 0;

		return removed;
	}
	// delete resuelto todo en la clase SortedLinkedList, iterativo
	public boolean remove1(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = header.first;

		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzocurrent.data == data
			prev= current;
			current= current.next;
		}

		if (current!=null && current.data.compareTo(data) == 0) {
			if (current == header.first)
				header.first = current.next;
			else
				prev.next = current.next;

            if (prev.next == null)
				header.last = prev;

			return true;
		}

		return false;
	}
	
	
	// delete resuelto todo en la clase SortedLinkedList, recursivo
//	@Override
	public boolean remove2(T data) {
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");
		
		boolean[] rta = new boolean[1];
		header.first = removeRec(data, header.first, rta);
		return rta[0];
	}
	

	public Node removeRec(T data, Node current, boolean[] rta) {
		
		if (current == null || current.data.compareTo(data) > 0) {
			rta[0] = false;
			return current;
		}
		
		if (current.data.equals(data)) {
			rta[0] = true;
			return current.next;
		}

		current.next = removeRec(data, current.next, rta);

        if (current.next == null)
			header.last = current;

		return current;

	}


	// delete resuelto delegando al nodo
//	@Override
	public boolean remove3(T data) {
		if (this.header.first == null) {
			this.header.first = new Node(data, null);
			return true;
		}

		boolean[] rta = new boolean[1];
		header.first = header.first.remove(data, rta);

		return rta[0];
	}
	
	
	
	@Override
	public boolean isEmpty() {
		return header.first == null;
	}

	@Override
	public int size() {
		return header.size;
	}

	
	@Override
	public void dump() {
		Node current = header.first;

		while (current!=null ) {
			// avanzo
			System.out.println(current.data);
			current= current.next;
		}
	}
	
	
	@Override
	public boolean equals(Object other) {
		if (other == null || !  (other instanceof SortedLinkedListWithHeader) )
			return false;
		
		@SuppressWarnings("unchecked")
		SortedLinkedListWithHeader<T> auxi = (SortedLinkedListWithHeader<T>) other;
		
		Node current = header.first;
		Node currentOther= auxi.header.first;
		while (current!=null && currentOther != null ) {
			if (current.data.compareTo(currentOther.data) != 0)
				return false;
			
			// por ahora si, avanzo ambas
			current= current.next;
			currentOther= currentOther.next;
		}
		
		return current == null && currentOther == null;
		
	}
	
	// -1 si no lo encontro
	protected int getPos(T data) {
		Node current = header.first;
		int pos= 0;
		
		while (current!=null ) {
			if (current.data.compareTo(data) == 0)
				return pos;
			
			// avanzo
			current= current.next;
			pos++;
		}
		return -1;
	}
	
	@Override
	public T getMin() {
		if (header.first == null)
			return null;
		
		return header.first.data;
	}


	@Override
	public T getMax() {
		
		if (header.last == null)
			return null;
		
		return header.last.data;
	}

	private class Node {
		private T data;
		private Node next;
	
		private Node(T data, Node next) {
			this.data= data;
			this.next= next;
		}
		
		public Node insert(T data, boolean[] rta) {
			if (data == null)
				throw new IllegalArgumentException("data cannot be null");
			
            if (this.data.compareTo(data) > 0) {
                rta[0] = true;
     
                 Node aux = new Node(data, this);
                 return aux;
             }
     
             if (this.next == null) {
     
                 Node aux = new Node(data, null);
                 this.next = aux;
     
                 header.last = aux;

                 return this;
             }
     
			if (this.data.equals(data)) {
				System.err.println(String.format("Insertion failed. %s repeated", data));
				rta[0] = false;
				return this;
			}
	

			this.next = this.next.insert(data, rta);
	
			return this;
		}
		
		public Node remove(T data, boolean[] rta) {
			if (data == null) 
				throw new IllegalArgumentException("data cannot be null");
			
            if (this.data.compareTo(data) == 0) {
                rta[0] = true;
        
                return this.next;
            }
                    
			if (this.next == null || this.next.data.compareTo(data) > 0) {
				rta[0] = false;

				return this;
			}
	
			this.next = this.next.remove(data, rta);

            if (this.next == null)  // If return value is null, then the last one was deleted
				header.last = this;

			return this;
		}

	}
	
	private final class Header {
        private Node first;
        private Node last;
        private int size;
        public Header(){
            first = last = null;
            size = 0;
        }
    }
	
	@Override
	public Iterator<T> iterator() {
		return new ListWithHeaderIterator();
	}

	class ListWithHeaderIterator implements Iterator<T> {

		Node next = header.first;
		Node current;
		Node prev;

		boolean canRemove = false;

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new RuntimeException("Reached end of list");

			canRemove = true;

			prev = current;
			current = next;

			next = next.next;

			return current.data;
		}

		@Override
		public void remove() {

			if (current != null && canRemove)  {
				header.size -= 1;
				current = current.next;

				if (current == null)	// Reached end, update header pointers
					header.last = prev;
				
				if (prev != null)
					prev.next = current;
				else
					header.first = current;		// Deleted the first element

			} else {
				throw new IllegalStateException();
			}

			canRemove = false;
		}

	}

	public static void main(String[] args) {
		SortedLinkedListWithHeader<String> l = new SortedLinkedListWithHeader<>();
	
		System.out.println(l.insert("hola"));
		System.out.println(l.insert("tal"));
		System.out.println(l.insert("ah"));
		System.out.println(l.insert("veo"));
		l.insert("bio");
		l.insert("tito");
		l.insert("hola");
		l.insert("aca");
		l.dump();

		for (String str : l) {
			System.out.println(str);
		}

        Iterator<String> it = l.iterator();
        String current;

        while(it.hasNext()) {
            current = it.next();
            if (current == "aca" || current == "veo")
                it.remove();
            
        }

		for (String str : l) {
			System.out.println(str);
		}

		System.out.println(l.getMax());
        
	}
}