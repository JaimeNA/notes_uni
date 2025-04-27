package core.C1_2022;

public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        
        for(int rec= 0; rec < elements.length-1; rec++) {
        	if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no est� ordenado");
        }
        
        this.elements = elements;
        this.size = elements.length;

     }

    private int binarySearch(String key) {
        int left = 0;
        int right = size-1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (elements[mid].equals(key)) {
                return mid;    
            } else if (elements[mid].compareTo(key) > 0) {  // Element is smaller or equal than mid(if its equal, go check if there are any earlier occurrences)
                right = mid - 1;
            } else {    // Else the element can only be greater
                left = mid + 1;
            }  
        }

        // No Element Found
        return -1;
    }

    public String search(String element, int distance) {
        int idx = binarySearch(element);

        if (idx == -1)  
            return null;

        int idxAtDistance = (idx + distance) % size;
        if (idxAtDistance < 0)
            idxAtDistance += size;

        return elements[idxAtDistance];
    }

   
}
