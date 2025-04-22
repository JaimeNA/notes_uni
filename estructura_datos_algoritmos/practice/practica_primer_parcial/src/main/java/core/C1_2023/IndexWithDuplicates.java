package core.C1_2023;

import java.util.Arrays;

public class IndexWithDuplicates<E extends Comparable<E> > {

    private final int chunk_size = 5;
    private E [] m_idx;
    private int m_size;
    @SuppressWarnings("unchecked")
    public IndexWithDuplicates(){
        m_idx = (E[]) new Comparable [chunk_size];
    }

    public void initialize(E[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements cannot be null");
        }
        for ( E e : elements )
            insert(e);
    }

    private void grow(){
        if (m_size < m_idx.length)
            return;
        m_idx = Arrays.copyOf(m_idx, m_idx.length + chunk_size );
    }

    public void insert(E key) {
        grow();

        int position = 0;
        for ( position = 0; position < m_size && m_idx[position].compareTo( key ) < 0; ++position);

        for (int i = m_size; i > position; --i)
            m_idx[i] = m_idx[i - 1];
        m_idx[position] = key;
        ++m_size;
    }

    void repeatedValues( E[] values, SimpleLinkedList<E> repeatedLst, SimpleLinkedList<E> singleLst, SimpleLinkedList<E> notIndexedLst )
    {
        if (values == null || repeatedLst == null || singleLst == null || notIndexedLst == null)
            throw new RuntimeException("Lists must not be null");

        int current_idx = -1;

        for (int i = 0; i < values.length; i++) {

            current_idx = binarySearch(values[i]);

            if (current_idx == -1)  {
                notIndexedLst.add(values[i]);
            } else {
                if (current_idx != 0 && current_idx != m_size-1 && (m_idx[current_idx] == m_idx[current_idx + 1] || m_idx[current_idx] == m_idx[current_idx - 1]))
                    repeatedLst.add(values[i]);
                else
                    singleLst.add(values[i]);
            }

        }
    }

    // Returns the index if found and -1 if not, returns first found
    private int binarySearch(E elem) {
        return binarySearchRec(0, m_size-1, elem);
    }

    private int binarySearchRec(int left, int right, E elem) {
        if (left > right)
            return -1;

        int mid = (right+left) / 2;

        if(m_idx[mid].compareTo(elem) == 0) 
            return mid;
            

        if (m_idx[mid].compareTo(elem) > 0)
            return binarySearchRec(left, mid-1, elem);

        return binarySearchRec(mid+1, right, elem);
    }

    public static void main(String[] args) {
        IndexWithDuplicates<Integer> idx = new IndexWithDuplicates<>();
        idx.initialize(  new Integer[] {100, 50, 30, 50, 80, 10, 100, 30, 20, 138} );

        SimpleLinkedList<Integer> repeatedLst = new SimpleLinkedList();
        SimpleLinkedList<Integer> singleLst  = new SimpleLinkedList();
        SimpleLinkedList<Integer> notIndexedLst  = new SimpleLinkedList();
        idx.repeatedValues( new Integer[] { 100, 70, 40, 120, 33, 80, 10, 50 }, repeatedLst, singleLst, notIndexedLst );

        System.out.println("Repeated Values");
        repeatedLst.dump();

        System.out.println("Single Values");
        singleLst.dump();

        System.out.println("Non Indexed Values");
        notIndexedLst.dump();
    }


}
