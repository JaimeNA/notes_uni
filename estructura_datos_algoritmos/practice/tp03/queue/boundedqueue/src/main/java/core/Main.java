package core;

public class Main {
    public static void main(String[] args) {
        BoundedQueue<Integer> myQueue = new BoundedQueue<>(10);
        myQueue.enqueue(10);
        myQueue.enqueue(20);
        myQueue.enqueue(30);
        myQueue.enqueue(40);
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        myQueue.enqueue(50);
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        myQueue.enqueue(70);
        myQueue.enqueue(70);
        myQueue.enqueue(70);
        myQueue.enqueue(70);
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        System.out.println(myQueue.dequeue() );
        System.out.println(myQueue.dequeue() );
        myQueue.enqueue(60);
        myQueue.enqueue(70);
        System.out.println("\nquedaron 5 elementos");
        myQueue.dump();
    }
}