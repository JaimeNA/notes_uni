package core;

public class Soundex {

    final static int CODE_LENGTH = 4;

    public static String getWordCode(String in_str) {

        // Format string
        in_str = in_str.toUpperCase();
        in_str = in_str.replaceAll("[^A-Z]", "");

        // Step one
        char[] in = in_str.toCharArray();
        char[] out = new char[CODE_LENGTH];

        // Step twp
        out[0] = in[0];

        // Step three 
        int last = getMapping(in[0]);
        int current = 0;
        int j = 1;

        // Step Four
        for(int i = 1; i < in.length; i++) {
            current = getMapping(in[i]);

            if (current != 0 && current != last && j < CODE_LENGTH) {
                out[j++] = (char)(current + '0');     // Check
                last = current;
            }
        }

        // Complete with ceros
        for ( ; j < CODE_LENGTH; j++) {
            out[j] = '0';
        }

        return new String(out);

    }

    public static float getLikeness(String s1, String s2) {
        char[] c1 = getWordCode(s1).toCharArray();
        char[] c2 = getWordCode(s2).toCharArray();  // TODO: Avoid unecessary conversion

        float toReturn = 0.0f;

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (c1[i] == c2[i]) 
                toReturn += 0.25f;
        }

        return toReturn;
    }

    static final int[] map = new int[27];

    // Better implementation of getLetterCode
    private static int getMapping(char c) {
        map['B' - 'A'] = 1;
        map['F' - 'A'] = 1;
        map['P' - 'A'] = 1;
        map['V' - 'A'] = 1;
        
        map['C' - 'A'] = 2;
        map['G' - 'A'] = 2;
        map['J' - 'A'] = 2;
        map['K' - 'A'] = 2;
        map['Q' - 'A'] = 2;
        map['S' - 'A'] = 2;
        map['X' - 'A'] = 2;
        map['Z' - 'A'] = 2;


        map['D' - 'A'] = 3;
        map['T' - 'A'] = 3;

        map['L' - 'A'] = 4;

        map['M' - 'A'] = 5;
        map['N' - 'A'] = 5;

        map['R' - 'A'] = 6;

        return map[c - 'A'];
    }

    // Return Soundex code of a letter, all letters must be uppercase, returns -1 on error. Not the best way.
    private static int getLetterCode(char c) {
        switch (c) {
            case 'A', 'E', 'I', 'O', 'U', 'Y', 'W', 'H':
                return 0;
    
            case 'B', 'F', 'P', 'V':
                return 1;

            case 'C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z':
                return 2;

            case 'D', 'T':
                return 3;

            case 'L':
                return 4;

            case 'M', 'N':
                return 5;

            case 'R':
                return 6;

            default:
                return -1;
        }
    }
}
