package core.parcial_1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

/*
 * Divide frases en grupos de N palabras consecutivas
 */
public class NWordsQGrams {
    private final String myString;
    private final int n;

    public NWordsQGrams(String sentence, int N){

        if (N <= 0)
            throw new RuntimeException("Debe ser mayor que 0");

        myString = sentence.toLowerCase();
        n = N;
    }

    public double similitud(String other){
    
        int sum = myString.length() + 2*(n-1) + other.length();
        int shared = shared(other.toLowerCase()); 

        return (double)(shared*2) / sum;
    }

    private HashMap<String, Integer> nWordsGrams(String str) { 

        StringBuilder s = new StringBuilder();
        s.append("# ".repeat(n-1)).append(str).append(" #".repeat(n-1));

        HashMap<String, Integer> toReturn = new HashMap<>();

        Scanner splitedStr = new Scanner(s.toString()).useDelimiter("[:.,;\\s]+"); // + means one or more times

        LinkedList<String> window = new LinkedList<>();

        while(splitedStr.hasNext()) {
            String word = splitedStr.next();
            window.add(word);
    
            if (window.size() == n) {
                String ngram = String.join(" ", window);
                toReturn.merge(ngram, 1, Integer::sum);
                window.removeFirst(); // Shift window
            }
        }

        splitedStr.close();

        return toReturn;
    }

    private int shared(String otherStr){

        HashMap<String, Integer> current = nWordsGrams(myString);
        HashMap<String, Integer> other = nWordsGrams(otherStr);

        int count = 0;

        for (Entry<String, Integer> entry: current.entrySet()) {
            while (entry.getValue() != 0 && other.containsKey(entry.getKey()))
            {
                other.merge(entry.getKey(), 1, (n, o) -> o - n);
                entry.setValue(entry.getValue()-1);
                count++;
            }
        }
        
        return count;
    }
    public static void main(String[] args) {
        
        NWordsQGrams gr = new NWordsQGrams("hola que tal", 3);
        HashMap<String, Integer> current = gr.nWordsGrams("voy a ir de compras");

        for (String str : current.keySet())
            System.out.println(str);

    }
}
