package core.C1_2025;

// Jaime Nazar Anchorena - 65064

public class LaberintoEntregado {

    private static boolean isInMAtrix(int[][] matrix, int posX, int posY) {
        return posX >= 0 && posY >= 0 && posX < matrix[0].length && posY < matrix.length;
    }

    public static boolean existeCamino(int[][] laberinto, int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        
        // Chequear input
        if (!isInMAtrix(laberinto, filaFin, columnaFin) || !isInMAtrix(laberinto, filaInicio, columnaInicio))
            throw new IllegalArgumentException("Posicion fuera de laberinto");

        laberinto[filaInicio][columnaInicio] = -1; // Empiezo aca

        // Recorre matriz, si llega al final dejara un -1 en el mismo
        for (int i = 0; i < laberinto.length; i++){
            for (int j = 0; j < laberinto[0].length; j++) {

                int goUp, goDown, goRight, goLeft;
                goUp = goDown = goRight = goLeft = 1;   // Empiezan como mov ilegales

                if (i > 0)
                    goUp = laberinto[i-1][j];
                
                if (j > 0)
                    goLeft = laberinto[i][j-1];
                
                if (i < laberinto.length-1)
                    goDown = laberinto[i+1][j];

                if (j < laberinto[0].length-1)
                    goRight = laberinto[i][j+1];

                int min = Math.min(Math.min(goUp, goLeft), Math.min(goDown, goRight));
                if (laberinto[i][j] == 0 && min < 0 )   // Si todavia no lo recorri, marcar si se puede llegar hacia el
                    laberinto[i][j] = -1;
                
            }
            // Repasar fila
            for (int j = 0; j < laberinto[0].length; j++) {
                
                int goRight, goLeft;
                goRight = goLeft = 1;   // Empiezan como mov ilegales

                if (j > 0)
                    goLeft = laberinto[i][j-1];
                
                if (j < laberinto[0].length-1)
                    goRight = laberinto[i][j+1];

                int min = Math.min(goLeft, goRight);
                if (laberinto[i][j] == 0 && min < 0 )   // Si todavia no lo recorri, marcar si se puede llegar hacia el
                    laberinto[i][j] = -1;
            }

        }

        return laberinto[filaFin][columnaFin] == -1; // Si llego a la posicion final con un 0 -> existe path
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
