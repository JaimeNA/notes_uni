package core.C1_2023;

public class MinPathFinder {

    public static void main(String[] args) {
        int[][] v = new int [][] {
                {2, 8, 32, 30},
                {12, 6, 18, 19},
                {1, 2, 4, 8},
                {1, 31, 1, 16}
        };
        MinPathFinder minPathFinder = new MinPathFinder();
        int ans = minPathFinder.getMinPath(v);
        System.out.println(ans);

        int [][] v2 = new int [][] {
                {2, 8, 32, 30},
                {12, 6, 18, 19},
                {1, 2, 4, 8}
        };
        MinPathFinder minPathFinder2 = new MinPathFinder();
        int ans2 = minPathFinder2.getMinPath(v2);
        System.out.println(ans2);

        int[][] v3 = new int [][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        MinPathFinder minPathFinder3 = new MinPathFinder();
        int ans3 = minPathFinder3.getMinPath(v3);
        System.out.println(ans3);
    }

    // NOTE: Its a levenshtein analogy
    public int getMinPathLessSpaceEfficient(int[][] weightMatrix){
        int height = weightMatrix.length;
        int width = weightMatrix[0].length;
        int[][] path = new int[height][width];

        // Init starting point
        path[0][0] = weightMatrix[0][0];

        // Init first row
        for (int i = 1; i < width; i++) {
            path[0][i] = path[0][i-1] + weightMatrix[0][i]; 
        }

        // Init first column
        for (int i = 1; i < height; i++) {
            path[i][0] = path[i-1][0] + weightMatrix[i][0]; 
        }

        // Complete the matrix
        for (int i = 1; i < height; i++){
            for (int j = 1; j < width; j++) {
                int fromPrevCol = path[i][j-1] + weightMatrix[i][j]; 
                int fromPrevRow = path[i-1][j] + weightMatrix[i][j]; 

                path[i][j] = Math.min(fromPrevCol, fromPrevRow);
            }
        }

        return path[height-1][width-1];
    }

    public int getMinPath(int[][] weightMatrix){
        int height = weightMatrix.length;
        int width = weightMatrix[0].length;
        int[] currRow = new int[width];

        // Init starting point
        currRow[0] = weightMatrix[0][0];

        // Init first row
        for (int i = 1; i < width; i++) {
            currRow[i] = currRow[i-1] + weightMatrix[0][i]; 
        }


        // Complete the matrix
        for (int i = 1; i < height; i++){
            currRow[0] +=  weightMatrix[i][0];

            for (int j = 1; j < width; j++) {

                int fromAbove = currRow[j] + weightMatrix[i][j]; 
                int fromLeft = currRow[j-1] + weightMatrix[i][j]; 

                currRow[j] = Math.min(fromAbove, fromLeft);
            }
        }

        return currRow[width-1];
    }

    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println(); // Nueva línea después de imprimir cada fila
        }
    }
}


