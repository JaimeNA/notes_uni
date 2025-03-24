package space;

public class Main {
    public static void main(String[] args) {
        
        for (int i = 1; i < 999999; i++) {
            System.out.println(String.format("N%d: %d", i, fibonacci(i)));
        }
    }

    private static long fibonacci(int n) {
        if (n <= 0)
            return 1;

        return fibonacci(n-1) + fibonacci(n-2);
    }
}