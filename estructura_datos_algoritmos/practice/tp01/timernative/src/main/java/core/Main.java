package core;

public class Main {
    public static void main(String[] args) {
        MyTimer timer = new MyTimer(1740833816000L);
        
        timer.stop();

        System.out.println(timer);
    }
}