package core;

import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class KMP {

	private static int[] nextComputation1(char[] query) {
		int[] next = new int[query.length];

	    int border=0;  // Length of the current border

	    int rec=1;  
	    while(rec < query.length){
	        if(query[rec]!=query[border]){  
	           if(border!=0)  
	                border=next[border-1]; 
               else  
                    next[rec++]=0;  
           }
           else{ 
          	    border++;  
                next[rec]=border;  
	            rec++;  
	        }
	    }
	    return next;
	}
	     
	     
	private static int[] nextComputation2(char[] query) {
		int[] next = new int[query.length];
		next[0] = 0;     // Always. There's no proper border.
		int border = 0;  // Length of the current border
		for (int rec = 1; rec < query.length; rec++) {
			while ((border > 0) && (query[border] != query[rec]))
				border = next[border - 1];     // Improving previous computation
			if (query[border] == query[rec])
				border++;
			// else border = 0;  // redundant
			next[rec] = border;
		}
		return next;
	}

	
	public static int indexOf(char[] query, char[] target) {
		
        if (query == null || query.length == 0)
			throw new RuntimeException("Bad query string");
		if (target == null || target.length == 0)
			throw new RuntimeException("Bad target string");

	 	int[] next = nextComputation1(query);

		int rec = 0;
		int pquery = 0;

		while (rec < target.length) {
			if (target[rec] == query[pquery]) {
				rec++;
				pquery++;
			}

			if (pquery == query.length) {	// Found
				break;
			} else if(rec < target.length && target[rec] != query[pquery]) {
				if (pquery != 0)
					pquery = next[pquery-1];
				else
					rec++;
				
			}
		}

		if (pquery == query.length)
        	return rec - pquery;
		
		return -1;
	}

	public static ArrayList<Integer> findAll(char[] query, char[] target) {

        if (query == null || query.length == 0)
			throw new RuntimeException("Bad query string");
		if (target == null || target.length == 0)
			throw new RuntimeException("Bad target string");

	 	int[] next = nextComputation1(query);

		 ArrayList<Integer> toReturn = new ArrayList<>();

		int rec = 0;
		int pquery = 0;

		while (rec < target.length) {
			if (target[rec] == query[pquery]) {
				rec++;
				pquery++;
			}

			if (pquery == query.length) {	// Found
				toReturn.add(rec - pquery);
				
				pquery = next[pquery-1];
				
			} else if(rec < target.length && target[rec] != query[pquery]) {
				if (pquery != 0)
					pquery = next[pquery-1];
				else
					rec++;
				
			}
		}

		return toReturn;
	}

}
