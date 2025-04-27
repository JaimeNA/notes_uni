package core.sin_fecha;

import java.util.Arrays;

public class CompactIndex implements IndexService {

    private IndexCount[] elements = new IndexCount[0];
    private final int chunkSize = 4;
    private int size = 0;

    // Estructura para guardar la cantidad de ocurrencias de cada indice
    static class IndexCount {
        String index;
        long count;

        public IndexCount(String index) {
            this.index = index;
            this.count = 1;
        }
    }

    @Override
    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        this.elements = new IndexCount[0];
        this.size = 0;

        for (String element : elements) {
            insert(element);
        }
    }

    @Override
    public boolean search(String key) {
        return false;
    }

    @Override
    public void insert(String key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        int idx = getClosestPosition(key);

        if (elements[idx].index.equals(key)) {
            elements[idx].count++;
        } else {
            if (size == elements.length)
                resize();

            for (int i = idx; i < size; i++) {
                elements[i+1] = elements[i];
            }

            elements[idx] = new IndexCount(key);

            size++;
        }
    }

    private void resize() {
        elements = Arrays.copyOf(elements, size + chunkSize);
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public String[] range(String leftKey, String rightKey) {
        return new String[0];
    }

    @Override
    public long occurrences(String key) {
        if (key == null)
        throw new IllegalArgumentException("Key cannot be null");

        int idx = getClosestPosition(key);

        if (elements[idx].index.equals(key))
            return elements[idx].count;
        else 
            return 0;
    }

    
    private int getClosestPosition(String key) {
        return getClosestPositionRec(key, 0, size - 1);
    }

    @SuppressWarnings("unchecked")
    private int getClosestPositionRec(String key, int left, int right) {
        if (left > right) {
            return left;
        }

        int mid = (right + left) / 2;

        if ((elements[mid].index).compareTo(key) == 0)
            return mid;
        
        if ((elements[mid].index).compareTo(key) > 0)
            return getClosestPositionRec(key, left, mid - 1);

        return getClosestPositionRec(key, mid + 1, right);
    }


    @Override
    public String getMax() {
        if (size == 0) {
            return null;
        }
        return elements[size - 1].index;
    }

    @Override
    public String getMin() {
        if (size == 0) {
            return null;
        }
        return elements[size - 1].index;
    }

    private void crecer() {
        IndexCount[] newElements = new IndexCount[size + chunkSize];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}