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
        int toReturn = 0;
        for (int i = 0; i < elemCount; i++) {
            if (((T)array[i]).compareTo(key) == 0)
                toReturn++;
        }

        return toReturn;
    }

    // Si no existe la key, devuelve donde insertarla
    // Si existe, devuelve la posicion de alguna repeticion
    @SuppressWarnings("unchecked")
    private int getClosestPositionAlt(T key) {
        int closest = 0;

        while (closest < elemCount && ((T)array[closest]).compareTo(key) < 0) {
            closest++;
        }

        return closest;
    }
    
    private int getClosestPosition(T key) {
        return getClosestPositionRec(key, 0, elemCount - 1);
    }

    @SuppressWarnings("unchecked")
    private int getClosestPositionRec(T key, int low, int high) {
        if (low > high) {
            return low;
        }

        int mid = (high + low) / 2;

        // if (array[mid].compareTo(key) == 0)  // I need to return the most left value
        //     return mid;

        if (key.compareTo(((T)array[mid])) <= 0)
            return getClosestPositionRec(key, low, mid - 1);

        return getClosestPositionRec(key, mid + 1, high);
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
        while (i < elemCount && ((T)array[i]).compareTo(key) <= 0);
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

        if (leftKey.compareTo(rightKey) > 0 || rightKey.compareTo(getMin()) < 0 || rightKey.compareTo(getMax()) > 0) {
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
