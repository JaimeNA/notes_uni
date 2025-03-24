package core;

import ar.edu.itba.eda.MyTimer;

public class Main {
    public static void main(String[] args) {
        MyTimer myCrono = new MyTimer(10);
        myCrono.stop(10 + 93623040); 
        System.out.println(myCrono);
    }
}