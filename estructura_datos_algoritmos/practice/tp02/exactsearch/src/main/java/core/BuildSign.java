package core;

import java.util.ArrayList;

public class BuildSign {
    
	private static int[] nextComputation1(int[] query) {
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
	     
    
	public static ArrayList<Integer> findAll(int[] query, int[] target) {

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
