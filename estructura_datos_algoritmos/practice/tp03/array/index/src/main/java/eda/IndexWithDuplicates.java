package eda;

import java.lang.reflect.Array;
import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class IndexWithDuplicates<T extends Comparable<T>> implements IndexService<T>{

    private final static int CHUNK_DIM = 20;
    private int elemCount;
    private Object[] array;

    public IndexWithDuplicates() {
        array = new Object[CHUNK_DIM]; // Option 3 to manage Generics arrays
        elemCount = 0;
    }

    @Override
    public void initialize(T[] elements) {

        if (elements == null)
        {
            throw new RuntimeException("Invalid data");
        }

        elemCount = elements.length;
        Arrays.sort(elements);

        array = Arrays.copyOf(elements, elemCount);
    }

    @Override
    public boolean search(T key) {
        int pos = this.getClosestPosition(key);
        return array[pos] == key;
    }

    @Override
    public void insert(T key) {
        
        if (elemCount == array.length) {
            this.array = Arrays.copyOf(this.array, array.length + CHUNK_DIM);
        }

        int pos = this.getClosestPosition(key);
        
        for (int i = elemCount; i > pos; i--)
            array[i] = array[i-1];

        elemCount++;
        array[pos] = key;

    }

    // If have time, decrease the size by chunks
    @Override
    public void delete(T key) {
        int pos = this.getClosestPosition(key);
        if (search(key))
        {
            elemCount--;
            for (int i = pos; i < elemCount; i++)
                array[i] = array[i+1];
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public int occurrences(T key) {
        int first = firstOcurrence(key);
        int last = lastOcurrence(key);

        if(first >= 0 && first < elemCount && ((T)array[first]).equals(key))
            return first - last;

        return 0;
    }

    // Si no existe la key, devuelve donde insertarla
    // Si existe, devuelve la posicion de alguna repeticion
    @SuppressWarnings("unchecked")
    private int getClosestPositionIt(T key, int left, int right) {

        while (left <= right) {

            int mid = (left + right) / 2;

            // Element is smaller or equal than mid(if its equal, go check if there are any earlier occurrences)
            if (((T)array[mid]).compareTo(key) >= 0) {
                right = mid - 1;

            // Else the element can only be greater
            } else {
                left = mid + 1;
            }  
        }

        // No Element Found
        return left;
    }
    
    private int getClosestPosition(T key) {
        return getClosestPositionRec(key, 0, elemCount - 1);
    }

    // Returns first occurrence
    @SuppressWarnings("unchecked")
    private int getClosestPositionRec(T key, int left, int right) {
        if (left > right) {
            return left;
        }

        int mid = (right + left) / 2;

        if (((T)array[mid]).compareTo(key) >= 0)
            return getClosestPositionRec(key, left, mid - 1);

        return getClosestPositionRec(key, mid + 1, right);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (T i : (T[])array)
            s.append(String.format("%d ", i));

        s.append("]");
        return s.toString();
    }

    @SuppressWarnings("unchecked")
    private int lastOcurrence(T key) {
        int i = getClosestPosition(key);
        while (i < elemCount && ((T)array[i]).compareTo(key) <= 0)
            i++;

        return i-1;
    }
    
    private int firstOcurrence(T key) {
        return getClosestPosition(key);
    }

    // Ot(N), Oe()
    @Override
    @SuppressWarnings("unchecked")
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded) {

        if (leftKey.compareTo(rightKey) > 0 || rightKey.compareTo(getMin()) < 0 || leftKey.compareTo(getMax()) > 0) {
            return (T[]) Array.newInstance(leftKey.getClass(), 0);  // Return empty array
        }

        T[] toReturn;

        int low = -1;
        int high = -1;

        // Include or not
        if (leftIncluded) {
            low = firstOcurrence(leftKey);
        } else {
            low = lastOcurrence(leftKey) + 1;
        }

        if (rightIncluded) {
            high = lastOcurrence(rightKey);
        } else {
            high = firstOcurrence(rightKey) - 1;
        }

        toReturn = (T[]) Array.newInstance(leftKey.getClass(), high - low + 1);
        int index = 0;
        for (int i = low; i <= high; i++) {
            toReturn[index++] = (T)array[i];
        }

        return (T[])toReturn;
    }

    @Override
    public void sortedPrint() {
        System.out.println(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getMax() {
        if (elemCount < 1)
            throw new RuntimeException("No elements");
        return (T)array[elemCount-1];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getMin() {
        if (elemCount < 1)
            throw new RuntimeException("No elements");
        return (T)array[0];
    }
}
