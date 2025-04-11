package core;

/*
 * Bounded queue implementation, implemented in a loop for better efficiency.
 */
public class BoundedQueue<T> {

    private T[] elements;
    private int first;
    private int last;
    private int qty= 0;

    @SuppressWarnings("unchecked")
    public BoundedQueue(int limit) { 
        elements = (T[]) new Object[limit];
        first = last = 0;
    }

    public boolean isEmpty() {
        return qty==0;
    }

    public boolean isFull() {
        return qty==elements.length;
    }

    public void enqueue(T element) {
        if (isFull()) 
            throw new RuntimeException("Stack full");

        if (!isEmpty())
            last = getNextIdx(last);

        elements[last] = element;
        qty++;
    }

    public T dequeue() { 
        if (qty == 0) 
            throw new RuntimeException("Stack is empty");

        T toReturn = elements[first];

        first = getNextIdx(first);
        qty--;

        return toReturn;
    }

    public void dump() {
        int aux = first;
        for (int i = 0; i < qty; i++) {
            System.out.println(elements[aux]);
            aux = getNextIdx(aux);
        }
    }

    private int getNextIdx(int idx) {
        return (idx + 1) % elements.length;
    }

}
