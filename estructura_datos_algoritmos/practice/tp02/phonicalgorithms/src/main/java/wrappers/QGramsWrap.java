package wrappers;

import info.debatty.java.stringsimilarity.*;

public class QGramsWrap {

    private NGram q;

    public QGramsWrap(int n) {
        q = new NGram(n);
    }

    public float qGrams(String str1, String str2) {

        return (float)q.distance(str1, str2);
    }

}
