package core.C2_2022;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Corredores {
    private static final int CHUNK_DIM = 100;

    private int getClosestPosition(int[] array, int key) {
        return getClosestPositionRec(array, key, 0, array.length - 1);
    }

    @SuppressWarnings("unchecked")
    private int getClosestPositionRec(int[] array, int key, int low, int high) {
        if (low > high) {
            return low;
        }

        int mid = (high + low) / 2;

        if (array[mid] == key)  
            return mid;

        if (key <= array[mid])
            return getClosestPositionRec(array, key, low, mid - 1);

        return getClosestPositionRec(array, key, mid + 1, high);
    }

    @SuppressWarnings("unchecked")
    private int lastOcurrence(int[] array, int key) {
        int i = getClosestPosition(array, key);
        while (i < array.length && array[i] <= key)
            i++;

        return i-1;
    }
    
    private int firstOcurrence(int[] array, int key) {
        int i = getClosestPosition(array, key);
        while (i >= 0 && array[i] >= key)
            i--;

        return i+1;
    }

    // Ot(N), Oe()
    @SuppressWarnings("unchecked")
    public int elementsInRange(int[] array, int leftKey, int rightKey) {

        if (leftKey > rightKey || rightKey < array[0] || leftKey > array[array.length-1]) {
            return 0;  
        }

        int toReturn = 0;

        int low = -1;
        int high = -1;

        low = firstOcurrence(array, leftKey);
        high = lastOcurrence(array, rightKey);

        return high-low + 1;
    }

    public int[] tiemposEntre(int[] tiempos, Pedido[] p) {
        if (p == null || tiempos == null)
            throw new IllegalArgumentException("Array must not be null");

        int[] toReturn = new int[CHUNK_DIM];
        int size = 0;

        for (Pedido pedido : p) {
            if (size == toReturn.length)
                toReturn = Arrays.copyOf(toReturn, toReturn.length + CHUNK_DIM);

            toReturn[size++] = elementsInRange(tiempos, pedido.desde, pedido.hasta);
            
        }

        return Arrays.copyOf(toReturn, size);
    }
    
    public static void main(String[] args) {
        Corredores c = new Corredores();

        Pedido[] pedidos = new Pedido[] {
                new Pedido(200, 240),
                new Pedido(180, 210),
                new Pedido(220, 280),
                new Pedido(0, 200),
                new Pedido(290, 10000)
        };

        int[] tiempos = new int[] {
                192,
                200,
                210,
                221,
                229,
                232,
                240,
                240,
                243,
                247,
                280,
                285
        };

        int [] respuestas = c.tiemposEntre(tiempos, pedidos);
        for(int i=0; i< respuestas.length; i++) {
            System.out.println(respuestas[i]);
        }

    }
}

class Pedido {
    public int desde;
    public int hasta;
    public Pedido(int desde, int hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }
}
