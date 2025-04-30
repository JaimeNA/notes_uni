package core.C1_2025;

// Jaime Nazar Anchorena - 65064

public class LaberintoFix {

    private static boolean isInMAtrix(int[][] matrix, int posX, int posY) {
        return posX >= 0 && posY >= 0 && posX < matrix[0].length && posY < matrix.length;
    }

    // Using stack wasnt an option
    public static boolean existeCamino(int[][] laberinto, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        
        // Chequear input
        if (!isInMAtrix(laberinto, filaFin, columnaFin) || !isInMAtrix(laberinto, filaInicio, columnaInicio))
            throw new IllegalArgumentException("Position out of maze");

        laberinto[filaInicio][columnaInicio] = -1; // Start here

        // Go through maze, complete the accessible positions with -1
        for (int i = 0; i < laberinto.length; i++){
            for (int j = 0; j < laberinto[0].length; j++) {

                int goUp, goDown, goRight, goLeft;
                goUp = goDown = goRight = goLeft = 1;   // Start being illegal movements

                if (i > 0)
                    goUp = laberinto[i-1][j];
                
                if (j > 0)
                    goLeft = laberinto[i][j-1];
                
                if (i < laberinto.length-1)
                    goDown = laberinto[i+1][j];

                if (j < laberinto[0].length-1)
                    goRight = laberinto[i][j+1];

                int min = Math.min(Math.min(goUp, goLeft), Math.min(goDown, goRight));
                if (laberinto[i][j] == 0 && min < 0) { // If its accessible, set as visited
                    laberinto[i][j] = -1;
                }
                
            }

            // Row second pass to cover any strays
            for (int k = 0; k < laberinto.length; k++) {
                if (laberinto[i][k] == 0 && isAccessible(laberinto[i], k))
                    laberinto[i][k] = -1;
            }

        }

        return laberinto[filaFin][columnaFin] == -1; // Si llego a la posicion final con un 0 -> existe path
    }

    // If there is an accesible index further ahead return true
    private static boolean isAccessible(int[] row, int idx) {
        for (int i = idx; i < row.length && row[i] != 1; i++) {
            if (row[i] == -1)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] laberinto = {
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };

        boolean existe = existeCamino(laberinto, 0, 0, 3, 0);
        if (existe) {
            System.out.println("Existe un camino en el laberinto.");
        } else {
            System.out.println("No existe un camino en el laberinto.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberinto);

        int[][] laberintoSinSalida = {
                {0, 0, 1, 0},
                {1, 0, 1, 1},
                {0, 0, 0, 0},
                {0, 1, 1, 1}
        };
        boolean existeSinSalida = existeCamino(laberintoSinSalida, 0, 0, 0, 3);
        if (existeSinSalida) {
            System.out.println("Existe un camino en el laberinto sin salida (¡error!).");
        } else {
            System.out.println("No existe un camino en el laberinto sin salida.");
        }
        System.out.println("Caminos recorridos:");
        imprimirLaberinto(laberintoSinSalida);
    }

    public static void imprimirLaberinto(int[][] laberinto) {
        for (int[] fila : laberinto) {
            for (int celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
