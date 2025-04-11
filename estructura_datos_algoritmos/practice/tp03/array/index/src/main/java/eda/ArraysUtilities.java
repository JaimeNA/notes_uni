package eda;

import java.util.Arrays;

public class ArraysUtilities {
	
	
	public static void main(String[] args) {
		int[] unsorted = new int[] {34, 10, 8, 60, 21, 17, 28, 30, 2, 70, 50, 15, 62, 40};
		mergesort(unsorted) ;	// 2, 8, 10, 15, 17, 21, 38, 30, 34, 40, 50, 60, 70
		
		for (int i : unsorted) {
			System.out.print(i + " ");
		}
	}
	

	/*
	 * ORDENACION POR QUICKSORT
	 */
	public static void quicksort(int[] unsorted) {
		quicksort (unsorted, unsorted.length-1);
	}
	
	
	public static void quicksort(int[] unsorted, int cantElements) {
		quicksortHelper (unsorted, 0, cantElements);
	}
	
	private static void quicksortHelper (int[] unsorted, int leftPos, int rightPos) {
		if (rightPos <= leftPos )
			return; 
		
		// tomamos como pivot el primero. Podria ser otro elemento
		int pivotValue= unsorted[leftPos];
		
		// excluimos el pivot del cjto.
		swap(unsorted, leftPos, rightPos);

		// particionar el cjto sin el pivot
		int pivotPosCalculated = partition(unsorted, leftPos, rightPos-1, pivotValue);
		
		
		// el pivot en el lugar correcto
		swap(unsorted, pivotPosCalculated, rightPos);
		
		
		// salvo unsorted[middle] todo puede estar mal
		// pero cada particion es autonoma
		quicksortHelper(unsorted, leftPos, pivotPosCalculated - 1);
		quicksortHelper(unsorted, pivotPosCalculated + 1, rightPos );

	}
	


	static private int partition(int[] unsorted, int leftPos, int rightPos, int pivotValue) {
	
		while (leftPos <= rightPos) {
			if (unsorted[leftPos] > pivotValue && unsorted[rightPos] < pivotValue)
				swap(unsorted, leftPos, rightPos);

			if (unsorted[leftPos] < pivotValue)
				leftPos++;
				
			if (unsorted[rightPos] > pivotValue)
				rightPos--;
		}

		return leftPos;
	}

	/* 
	 * FIN ORDENACION QUICKSORT
	 * 	
	 */
	
	/*
	 * ORDENACION POR MERGESORT
	 */

	// Extra array version
	public static int[] mergesortAlt(int[] unsorted) {
		
		if (unsorted.length <= 1)
			return unsorted;

		int mid = unsorted.length / 2;

		int[] low = mergesortAlt(Arrays.copyOf(unsorted, mid));
		int[] high = mergesortAlt(Arrays.copyOfRange(unsorted, mid, unsorted.length));

		int indexLow = 0;
		int indexHigh = 0;

		// Merge both
		for(int i = 0; i < unsorted.length-1; i++) {
			if (indexLow < mid && low[indexLow] < high[indexHigh]) {
				unsorted[i] = low[indexLow++];
			} else {
				unsorted[i] = high[indexHigh++];
			}
		}
		
		return unsorted;
	}

	 // Single array version
	 
	public static void mergesort(int[] unsorted) {
		mergesort(unsorted, 0, unsorted.length-1);
	}
	public static void mergesort(int[] unsorted, int low, int high) {
		
		if (high <= low)
			return ;

		int mid = (low + high) / 2;

		mergesort(unsorted, low, mid);
		mergesort(unsorted, mid+1, high);

		int indexLow = 0;
		int indexHigh = mid+1;

		int[] temp = Arrays.copyOfRange(unsorted, low, mid+1);

		int i = low;

		while (indexLow < temp.length && indexHigh <= high) {
            if (temp[indexLow] <= unsorted[indexHigh]) {
                unsorted[i++] = temp[indexLow++];
            } else {
                unsorted[i++] = unsorted[indexHigh++];
            }
        }

        while (indexLow < temp.length) { // Copy remaining left half
            unsorted[i++] = temp[indexLow++];
        }

	}
	/*
	 * FIN MERGESORT
	 */
	static private void swap(int[] unsorted, int pos1, int pos2) {
		int auxi= unsorted[pos1];
		unsorted[pos1]= unsorted[pos2];
		unsorted[pos2]= auxi;
	}
	
	

}
