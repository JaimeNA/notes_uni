package core.C1_2024;

import java.util.Arrays;

/**
 * @author dpenaloza
 *
 */
public class IndexWithDuplicates  {

	final static private int chunksize= 5;

	private int[] indexedData;
	private int cantElems;
	
	
	public IndexWithDuplicates() {
		indexedData= new int[chunksize];
		cantElems= 0;
	}

	public void initialize(int[] unsortedElements) {

		if (unsortedElements == null)
			throw new RuntimeException("Problem: null data collection");

		indexedData= unsortedElements;
		Arrays.sort(indexedData);
		cantElems= indexedData.length;
	}


	public int[] getIndexedData() {
		return indexedData;
	}

	public void print() {
		System.out.print("[");
		for (int i : indexedData)
			System.out.print(i + " ") ;
		System.out.println("]");
		
	}

	public void merge(IndexWithDuplicates other) {

		if (other == null || other.indexedData == null)
			return ;

		indexedData = Arrays.copyOf(indexedData, cantElems + other.cantElems);
		
		int i = cantElems-1;
		int j = other.cantElems-1;
		int k = i + j + 1;

		while (j >= 0 && i >= 0) {	// Truco: Ir de atras a adelante

			if (indexedData[i] > other.indexedData[j]) {
				indexedData[k--] = indexedData[i--];
			} else {
				indexedData[k--] = other.indexedData[j--];
			}
		}

		cantElems += other.cantElems;
	}
}
