package eda;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

public class IndexWithDuplicates implements IndexService{

    private final static int CHUNK_DIM = 20;
    private int elemCount;
    private int[] array;

    public IndexWithDuplicates() {
        array = new int[CHUNK_DIM];
        elemCount = 0;
    }

    @Override
    public void initialize(int[] elements) {

        if (elements == null)
        {
            throw new RuntimeException("Invalid data");
        }

        // elemCount = elements.length;
        // Arrays.sort(elements);

        // array = Arrays.copyOf(elements, elemCount);

        for (int i = 0; i < elements.length; i++) {
            this.insert(elements[i]);
        }
    }

    @Override
    public boolean search(int key) {
        int pos = this.getClosestPosition(key);
        return array[pos] == key;
    }

    @Override
    public void insert(int key) {
        
        if (elemCount == array.length) {
            this.array = Arrays.copyOf(this.array, array.length + CHUNK_DIM);
        }

        int pos = this.getClosestPosition(key);
        
        for (int i = elemCount-1; i > pos; i--)
            array[i] = array[i-1];

        elemCount++;
        array[pos] = key;

    }

    // If have time, decrease the size by chunks
    @Override
    public void delete(int key) {
        int pos = this.getClosestPosition(key);
        if (search(key))
        {
            elemCount--;
            for (int i = pos; i < elemCount; i--)
                array[i] = array[i+1];
        }
    }

    @Override
    public int occurrences(int key) {
        int toReturn = 0;
        for (int i = 0; i < elemCount; i++) {
            if (this.array[i] == key)
                toReturn++;
        }

        return toReturn;
    }

    // Si no existe la key, devuelve donde insertarla
    // Si existe, devuelve la posicion de alguna repeticion
    private int getClosestPositionAlt(int key) {
        int closest = 0;

        while (closest < elemCount && array[closest] < key) {
            closest++;
        }

        return closest;
    }
    
    private int getClosestPosition(int key) {
        return getClosestPositionRec(key, 0, elemCount - 1);
    }

    private int getClosestPositionRec(int key, int low, int high) {
        
        if (high <= 0)
            return 0;

        int mid = (high - low) / 2;

        if (low > high || array[mid] == key)
            return mid;

        if (key < array[mid])
            return getClosestPositionRec(key, low, mid - 1);

        return getClosestPositionRec(key, mid + 1, high);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");

        for (int i : array)
            s.append(String.format("%d ", i));

        s.append("]");
        return s.toString();
    }

    // NOTE: Use getClosestPositions
    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {
        int[] toReturn;
        int i = 0;

        if (leftIncluded && rightIncluded)
            toReturn = new int[elemCount];
        else if (rightIncluded) {
            toReturn = new int[elemCount-1];
            i = 1;
        } else if (leftIncluded) {
                toReturn = new int[elemCount-1];
        } else {
            toReturn = new int[elemCount-2];
        }
    }

    @Override
    public void sortedPrint() {
        System.out.println(this);
    }

    @Override
    public int getMax() {
        if (elemCount < 1)
            throw new RuntimeException("No elements");
        return array[elemCount-1];
    }

    @Override
    public int getMin() {
        if (elemCount < 1)
            throw new RuntimeException("No elements");
        return array[0];
    }
}
