package core;

public class Levenshtein {

    public static float getDistanceNormalized(String str1, String str2) {

        int max = str1.length();
        if (max < str2.length())
            max = str2.length();

        return 1.0f - (float)getDistance(str1, str2) / max;
    }

    public static int getDistance(String str1, String str2) {

        char s1[] = str1.toUpperCase().toCharArray();
        char s2[] = str2.toUpperCase().toCharArray();

        int height = s1.length;
        int width = s2.length;

        int prevRow[] = new int[width];
        int currentRow[] = new int [width];

        // Initialize first row
        for (int i = 0; i < width; i++) {
            prevRow[i] = i;
        }

        // Assign values to each cell
        for (int i = 1; i < height; i++) {
            currentRow[0] = i;

            for (int j = 1; j < width; j++) {
               
                int value = prevRow[j-1];

                // Diagonal
                if (s1[i] != s2[j]) {
                    value++;
                }

                // Vertical
                int newValue = prevRow[j] + 1;
                if (value > newValue) 
                    value = newValue;
                
                
                // Horizontal
                newValue = currentRow[j-1] + 1;
                if (value > newValue) 
                    value = newValue;

                currentRow[j] = value; 
            }

            // Swap arrays
            int aux[] = currentRow;
            currentRow = prevRow;
            prevRow = aux;
        }

        return prevRow[width-1];
    }

    public static int getDistanceFull(String str1, String str2) {

        char s1[] = str1.toUpperCase().toCharArray();
        char s2[] = str2.toUpperCase().toCharArray();

        int height = s1.length;
        int width = s2.length;

        int matrix[][] = new int[height][width];

        // Initialize matrix
        for (int i = 0; i < height; i++) {
            matrix[i][0] = i;
        }

        for (int i = 0; i < width; i++) {
            matrix[0][i] = i;
        }

        // Assign values to each cell
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
               
                int value = matrix[i-1][j-1];

                // Diagonal
                if (s1[i] != s2[j]) {
                    value++;
                }

                // Vertical
                int newValue = matrix[i-1][j] + 1;
                if (value > newValue) 
                    value = newValue;
                
                
                // Horizontal
                newValue = matrix[i][j-1] + 1;
                if (value > newValue) 
                    value = newValue;

                matrix[i][j] = value; 

            }
        }

        printMatrix(matrix);

        return matrix[height-1][width-1];
    }

    private static void printMatrix(int m[][]) {

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                s.append(m[i][j]);
            }
            s.append("\n");
        }

        System.out.println(s.toString());
    }

}
