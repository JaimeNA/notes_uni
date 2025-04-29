package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

// Busca combinando todos los algoritmos, devuelve las 5 palabras mas parecidas
public class Main {
    public static void main(String[] args) {
        
        System.out.println(QGrams.qGrams("JOHN", "JOE", 3));

        // String[] words = new String[100]; // Bad
        // try {   // Really bad
        //     words = getWordsLowerCase("/home/jasha/personal_repos/notes_uni/estructura_datos_algoritmos/practice/tp02/phonicalgorithms/product.txt");
        // } catch(Exception e) {
        //     System.out.println("File not found or invalid data");
        // }

        // System.out.println("Found words: ");

        // for (String str : words) {
        //     System.out.println(str);
        // }

        // String[] results = search(words, "seca plato");

        // System.out.println("Resultados de la busqueda:");

        // for (String str : results) {
        //     System.out.println(str);
        // }
    }

    public static String[] search(String[] words, String query)
    {
        TreeMap<Double, String> results = new TreeMap<>(Comparator.reverseOrder());  // Order
        for (String str : words) {
            double similarity = Levenshtein.getDistanceNormalized(str, query) * 0.3 + Soundex.getLikeness(str, query) * 0.6 + QGrams.qGrams(str, query, 3) * 0.1;

            results.put(similarity, str);
        }

        String[] toReturn = new String[5];

        int i = 0;
        for (String str : results.values()) {   // Magic number bad, but i have no time
            if (i < 5)
                toReturn[i++] = str;
        }

        return toReturn;
    }

    public static String[] getWordsLowerCase(String filename) throws Exception{
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line.toLowerCase());
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

}