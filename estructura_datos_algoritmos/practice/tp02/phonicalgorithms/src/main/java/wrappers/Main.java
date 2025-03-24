package wrappers;

import core.QGrams;

public class Main {
    public static void main(String[] args) {

        QGramsWrap w = new QGramsWrap(3);
        System.out.println(String.format("Similitud mia: %f", QGrams.qGrams("1234567", "7654321", 3)));
        System.out.println(String.format("Similitud wrap: %f", w.qGrams("1234567", "7654321")));

    }

}