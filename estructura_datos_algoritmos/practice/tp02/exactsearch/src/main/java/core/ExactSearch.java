package core;

public class ExactSearch {

    public static int indexOf(String str, String pattern) {
        
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();

        int j = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] == p[j]) {
                j++;
            } else {
                i -= j;
                j = 0;
            }

            if (j == p.length) {
                return i - j + 1;
            }
        }
        return -1;
    }

}
