package core.sin_fecha;

import java.util.Arrays;

public class ConcatenarArrays {

    public static void main(String[] args) {
        int[][] multiplesArrays = new int[][]{
                new int[]{49, 392, 10, -5},
                new int[]{293, 1, 29, 19, 20},
                new int[]{1, 1, 1, -4},
                new int[]{0, -1, 1}
        };

        ConcatenarArrays concatenador = new ConcatenarArrays();

        int[] nuevoArray = concatenador.concatenarMultiples(multiplesArrays);

        System.out.println(Arrays.toString(nuevoArray));
    }

    private int[] concatenarMultiples(int[][] multiplesArrays) {
        int[] nuevoArray = new int[0]; //+1 por asignacion
        int size = 0;

        for (int i=0; i<multiplesArrays.length; i++) { //for de 0 a N-1

            nuevoArray = Arrays.copyOf(nuevoArray, size + multiplesArrays[i].length);
            
            for(int j = 0; j < multiplesArrays[i].length; j++) {
                nuevoArray[size++] = multiplesArrays[i][j];
            }

        }

        return nuevoArray;
    }

    private int[] concatenarDosArrays(int[] array1, int[] array2) {
        int[] ret = new int[array1.length + array2.length]; //+1 por asignacion

        for(int i = 0; i<array1.length; i++) { //for de 0 a N(M-1)
            ret[i] = array1[i];
        }
        for(
            int i = 0; i<array2.length; i++) { //for de 0 a M-1
            ret[i+array1.length] = array2[i];
        }

        return ret;
    }
}