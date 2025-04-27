package core.C1_2022;

public class URLfy {

    public static void main(String[] args) {

        URLfy urlfy = new URLfy();
        
        char [] ejemplo = new char[] { 'e', 's', ' ', 'u', 'n', ' ', 'e', 'j', 'e', 'm', 'p', 'l', 'o', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(ejemplo);
        System.out.println(ejemplo);

    
       ejemplo= new char [] {'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', ' ', 'h', 'o', 'l', 'a', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
       urlfy.reemplazarEspacios(ejemplo);
       System.out.println(ejemplo);

    
       ejemplo= new char [] {' ', ' ', 'e', 's', 'p', 'a', 'c', 'i', 'o', 's', ' ', ' ', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
       urlfy.reemplazarEspacios(ejemplo);
       System.out.println(ejemplo);

    }

    public void reemplazarEspacios(char [] str) {
        char[] toReplace = {'0', '2', '%'};
        int i = str.length-1;

        // Find last char
        for (; i >= 0 && str[i] == '\0'; i--);
            
        int j = str.length-1;
        while(j >= 0) {
            if (str[i] == ' ') {
                for (char c : toReplace) {
                        str[j--] = c;
                }
            } else {
                str[j--] = str[i];
            }

            i--;
        }
        
    }
}