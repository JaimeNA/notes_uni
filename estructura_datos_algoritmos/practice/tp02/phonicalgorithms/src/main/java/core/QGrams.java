package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class QGrams {

    public static float qGrams(String str1, String str2, int q) {

        int sum = str1.length() + 2*(q-1) + str2.length();
        int shared = shared(str1, str2, q); 

        return (float)(shared*2) / sum;
    }

    public static void printTokens(String str, int q) {
        HashMap<String, Integer> res = nGrams(str, q);

        for(Entry<String, Integer> e : res.entrySet()) {
            System.out.println(String.format(" [\"%s\", %d]", e.getKey(), e.getValue()));
        }
    }

    public static HashMap<String, Integer> nGrams(String str, int n) { 

        int q = n;
        StringBuilder s = new StringBuilder();
        s.append("#".repeat(q-1)).append(str).append("#".repeat(q-1));

        HashMap<String, Integer> toReturn = new HashMap<>();

        for (int j = q; j <= s.length(); j++) { 
            toReturn.merge(s.subSequence(j-q, j).toString(), 1, (oldValue, newValue) -> oldValue+newValue);
        }

        return toReturn;
    }

    private static int shared(String str1, String str2, int q){

        HashMap<String, Integer> s1 = nGrams(str1, q);
        HashMap<String, Integer> s2 = nGrams(str2, q);

        int count = 0;

        for (Entry<String, Integer> entry: s1.entrySet()) {
            while (entry.getValue() != 0 && s2.containsKey(entry.getKey()))
            {
                s2.merge(entry.getKey(), 1, (n, o) -> o - n);
                entry.setValue(entry.getValue()-1);
                count++;
            }
        }
        
        return count;
    }

}
