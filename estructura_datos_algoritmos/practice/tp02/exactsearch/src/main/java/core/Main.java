package core;

public class Main {
    public static void main(String[] args) {
        // String query, text;
		// int pos;
		
		// // debe dar 3
		// query= "ABXABU";
		// text= "ABXABXABUF";
		
		// pos= KMP.indexOf(query.toCharArray(), text.toCharArray());
		// System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
	
		
		// // debe dar 5
		// query= "ABAB";
		// text= "SABASABABA";
		// pos= KMP.indexOf(query.toCharArray(), text.toCharArray());
		// System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
		
		
		// // debe dar 0
		// query= "ABAB";
		// text= "ABABYYYY";
		// pos= KMP.indexOf(query.toCharArray(), text.toCharArray());
		// System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));
		
		
		// // debe dar -1
		// query= "ABAB";
		// text= "ABAYYYA";
		// pos= KMP.indexOf(query.toCharArray(), text.toCharArray());
		// System.out.println(String.format("%s in %s at pos %d\n", query, text, pos));


        char[] query = "aaa".toCharArray();
        char[] target = "aaabaaaaab".toCharArray();
		for (Integer i : KMP.findAll(query, target)) {
			System.out.println(i);
		}
    }
}