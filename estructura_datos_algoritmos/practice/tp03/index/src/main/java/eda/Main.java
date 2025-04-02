package eda;

public class Main {
    public static void main(String[] args) {
        IndexWithDuplicates myIndex = new IndexWithDuplicates();

        myIndex.initialize(new int[] {100, 50, 30, 50, 80, 100, 100, 30}); // 30, 30, 50, 50, 80, 100, 100, 100

        int[] range = myIndex.range(10, 20, false, false);

        for(int i : range) {
            System.out.print(i + " ");
        }
        System.out.println(" ");

        System.out.println(myIndex);

        
    }
}