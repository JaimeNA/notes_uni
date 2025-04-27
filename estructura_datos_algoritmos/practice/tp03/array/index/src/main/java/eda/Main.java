package eda;

public class Main {
    public static void main(String[] args) {
        IndexWithDuplicates<Integer> myIndex = new IndexWithDuplicates<Integer>();

        myIndex.initialize(new Integer[] {100, 50, 30, 50, 80, 100, 100, 30}); // 30, 30, 50, 50, 80, 100, 100, 100

        Integer[] range = myIndex.range(50, 100, false, true);

        for(int i : range) {
            System.out.print(i + " ");
        }
        System.out.println(" ");

        System.out.println(myIndex);

        
    }
}