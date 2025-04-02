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

        elemCount = elements.length;
        Arrays.sort(elements);

        array = Arrays.copyOf(elements, elemCount);
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
        
        for (int i = elemCount; i > pos; i--)
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
            for (int i = pos; i < elemCount; i++)
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
        if (low > high) {
            if (high < 0)
                return 0;
                
            return low;
        }

        int mid = (high + low) / 2;

        if (array[mid] == key)
            return mid;

        if (key <= array[mid])
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

    private int lastOcurrence(int key) {
        for (int i = elemCount; i >= 0; i--) {
            if (array[i] == key)
                return i;
        }       
        return -1;
    }
    
    private int firstOcurrence(int key) {
        for (int i = 0; i <= elemCount; i++) {
            if (array[i] == key)
                return i;
        }       
        return -1;
    }

    // Ot(N), Oe()
    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded) {

        if (leftKey > rightKey) {
            return new int[0];  // Return empty array
        }

        int[] toReturn;

        int low = firstOcurrence(leftKey);
        int high = lastOcurrence(rightKey);

        // Include or not if found
        if (!rightIncluded && high != -1) {
            high--;
        }
        if (!leftIncluded && low != -1) {
            low++;
        }

        // If number not found, go from the closest position
        if(low == -1)
            low = getClosestPosition(leftKey);

        if(high == -1)
            high = getClosestPosition(rightKey);
        
        toReturn = new int[high - low + 1];
        int index = 0;
        for (int i = low; i <= high; i++) {
            toReturn[index++] = array[i]; 
        }

        return toReturn;
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
