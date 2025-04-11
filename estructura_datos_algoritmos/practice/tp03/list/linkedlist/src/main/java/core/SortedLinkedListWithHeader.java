package core;

public class SortedLinkedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T>{

	private Node root;
	private Node last;
    private int size;

    public SortedLinkedListWithHeader() {
        size = 0;
    }

	@Override
	public boolean insert(T data) {
        boolean inserted = insert2(data);

        size += inserted ? 1 : 0;

		return inserted;
	}

	// insert resuelto todo en la clase SortedLinkedList, iterativo
	private boolean insert1(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = root;

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
            last = aux;

		// es el lugar para colocarlo
		if (current == root) {
			// el primero es un caso especial: cambia root
			root= aux;
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
		root = insertRec(data, root, rta);
		return rta[0];
	}
	
	
	private Node insertRec(T data, Node current, boolean[] rta) {

		if (current == null || current.data.compareTo(data) > 0) {
			rta[0] = true;
            Node toReturn = new Node(data, current);

            if (current == null)
                last = toReturn;

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

		if (this.root == null) {
			this.root = new Node(data, null);
			return true;
		}

		boolean[] rta = new boolean[1];
		root = root.insert(data, rta);

		return rta[0];
	}
	
	@Override
	public boolean find(T data) {
		return getPos(data) != -1;
	}
	
	@Override
	public boolean remove(T data) {
        boolean removed = remove3(data);

        size -= removed ? 1 : 0;

		return removed;
	}
	// delete resuelto todo en la clase SortedLinkedList, iterativo
	public boolean remove1(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = root;

		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzocurrent.data == data
			prev= current;
			current= current.next;
		}

		if (current!=null && current.data.compareTo(data) == 0) {
			if (current == root)
				root = current.next;
			else
				prev.next = current.next;

            if (prev.next == null)
                last = prev;

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
		root = removeRec(data, root, rta);
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
            last = current;

		return current;

	}


	// delete resuelto delegando al nodo
//	@Override
	public boolean remove3(T data) {
		if (this.root == null) {
			this.root = new Node(data, null);
			return true;
		}

		boolean[] rta = new boolean[1];
		root = root.remove(data, rta);

		return rta[0];
	}
	
	
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return size;
	}

	
	@Override
	public void dump() {
		Node current = root;

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
		
		Node current = root;
		Node currentOther= auxi.root;
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
		Node current = root;
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
		if (root == null)
			return null;
		
		return root.data;
	}


	@Override
	public T getMax() {
		
		if (last == null)
			return null;
		
		return last.data;
	}

	private final class Node {
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
     
                 last = aux;

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
                last = this;

			return this;
		}

	}
	

	

	
	public static void main(String[] args) {
		SortedLinkedList<String> l = new SortedLinkedList<>();
	
		System.out.println(l.insert("hola"));
		System.out.println(l.insert("tal"));
		System.out.println(l.insert("ah"));
		System.out.println(l.insert("veo"));
		l.dump();


		l.remove("veo");
		l.find("veo");
        l.getMax();
	}
}