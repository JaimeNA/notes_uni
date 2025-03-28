package eda;

public class Main {
    public static void main(String[] args) {
        IndexWithDuplicates myIndex = new IndexWithDuplicates();

        myIndex.delete(10);


        myIndex.insert(10);
        myIndex.insert(10);
        myIndex.insert(22);
        myIndex.insert(30);

        myIndex.delete(34);
        
        myIndex.insert(76);
        myIndex.insert(76);
        myIndex.insert(76);

        myIndex.insert(10);
        myIndex.insert(4);
        myIndex.insert(76);

        int[] range = myIndex.range(10, 76, false, true);

        for(int i : range) {
            System.out.print(i + " ");
        }
        System.out.println(" ");

        System.out.println(myIndex);

        
    }
}