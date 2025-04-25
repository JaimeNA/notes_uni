package core;

import java.util.Arrays;
import java.util.function.Function;

public class ClosedHashingWithColision<K, V> implements IndexParametricService<K, V> {
	final private int initialLookupSize= 10;

	@SuppressWarnings({"unchecked"})
	private Slot<K,V>[] Lookup = (Slot<K,V>[]) new Slot[initialLookupSize];

	private Function<? super K, Integer> prehash;

	private static final float THRESHOLD = 0.75f;
	private int size;

	public ClosedHashingWithColision( Function<? super K, Integer> mappingFn) {
		if (mappingFn == null)
			throw new RuntimeException("fn not provided");

		prehash= mappingFn;
	}

	// ajuste al tama�o de la tabla
	private int hash(final K key) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");

		return prehash.apply(key) % Lookup.length;
	}
	
	public void insertOrUpdate(final K key, final V data) {
		if (key == null || data == null) {
			String msg= String.format("inserting or updating (%s,%s). ", key, data);
			if (key==null)
				msg+= "Key cannot be null. ";
			
			if (data==null)
				msg+= "Data cannot be null.";
		
			throw new IllegalArgumentException(msg);
		}
		
		if (loadFactor() > THRESHOLD)
			rehash();

		int i = hash( key);
		int firstDeleted = -1;

		while (Lookup[i] != null && !Lookup[i].key.equals(key)) {	// Go until match or physically removed
			if (firstDeleted == -1 && Lookup[i].deleted)	// Store first deleted
				firstDeleted = i;

			i = (i+1) % Lookup.length;
		}

		if (Lookup[i] == null) {
			if (firstDeleted != -1)
				Lookup[firstDeleted] = new Slot<K, V>(key, data);
			else {
				Lookup[i] = new Slot<K, V>(key, data);
			}
			size++;
		} else if (Lookup[i].deleted) {
			Lookup[i] = new Slot<K, V>(key, data);
			size++;
		} else {
			Lookup[i].value = data;	// Update value
		}
 
	}

	// Simple version, aux array
	@SuppressWarnings({"unchecked"})
	private void rehash(){
		Slot<K,V>[] aux = Lookup;
		Lookup = (Slot<K,V>[]) new Slot[Lookup.length * 2];

		size = 0;

		for (int i = 0; i < aux.length; i++) {
			Slot<K,V> e = aux[i];

			if (e != null && !e.deleted)
				insertOrUpdate(e.key, e.value);
		}
	}
	
	// find or get
	public V find(final K key) {
		if (key == null)
			return null;

		int i = hash( key);
		int stopIndex =  (hash(key) + Lookup.length-1) % Lookup.length;	// So it doesnt loop
		while (Lookup[i] != null && i != stopIndex) {

			if (Lookup[i].key.equals(key) && !Lookup[i].deleted)
				return Lookup[i].value;

			i = (i+1) % Lookup.length;
		}

		return null;
	}

	public boolean remove(final K key) {
		if (key == null)
			return false;
		
		// lo encontre?
		if (Lookup[ hash( key) ] == null)	// Physically removed
			return false;
		

		int i = hash( key);
		int stopIndex =  (hash(key) + Lookup.length-1) % Lookup.length;	// So it doesnt loop
		while (Lookup[i] != null) {
			if (i == stopIndex)
				return false;

			if (Lookup[i].key.equals(key) && !Lookup[i].deleted) {
				if (Lookup[(i+1) % Lookup.length] == null)
					Lookup[ i ] = null;	// Remove it physically
				else
					Lookup[i].deleted = true;	// Remove it logically

				size--;
				return true;
			}

			i = (i+1) % Lookup.length;
		}

		return false;
	}

	
	public void dump()  {
		for(int rec= 0; rec < Lookup.length; rec++) {
			if (Lookup[rec] == null)
 				System.out.println(String.format("slot %d is empty", rec));
			else if (Lookup[rec].deleted)
				System.out.println(String.format("slot %d is logically deleted", rec));
			else
				System.out.println(String.format("slot %d contains %s",rec, Lookup[rec]));
		}
	}
	

	public int size() {
		return this.size;
	}

	private float loadFactor() {
		return (float)size() / Lookup.length;
	}

	static private final class Slot<K, V>	{
		private final K key;
		private boolean deleted;
		private V value;
		
		private Slot(K theKey, V theValue){
			key= theKey;
			value= theValue;
			deleted = false;
		}

	
		public String toString() {
		 return String.format("(key=%s, value=%s)", key, value );
		}
	}
	
	
	public static void main(String[] args) {
		ClosedHashingWithColision<Integer, String> myHash= new ClosedHashingWithColision<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(44, "Juan");
		myHash.insertOrUpdate(18, "Paula");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(32, "Lo");
		myHash.insertOrUpdate(52, "Mi");
		myHash.insertOrUpdate(63, "Esther");
		myHash.insertOrUpdate(67, "Layla");
		myHash.insertOrUpdate(77, "Layla");
		myHash.insertOrUpdate(66, "Alex");

		myHash.remove(32);
		myHash.dump();

		System.out.println(String.format("Current size is %d, and load factor is %f", myHash.size(), myHash.loadFactor()));
		
	}
	

	// private static Integer hash(String elem) {
	// 	int sum = 0;

	// 	for(char c: elem.toCharArray()) {
	// 		sum += (int)c;
	// 	}

	// 	return sum;
	// }

	// public static void main(String[] args) {

	// 	// Read from file
	// 	String fileName = "amazon-categories30.txt";
	// 	InputStream is = ClosedHashing.class.getClassLoader().getResourceAsStream(fileName);

	// 	Reader in = new InputStreamReader(is);
	// 	BufferedReader br = new BufferedReader(in);

	// 	String line;

	// 	// Method 1
	// 	ClosedHashingWithColision<String, String> myHash1= new ClosedHashingWithColision<>(f->(int)f.charAt(0));

	// 	int colisions = 0;
	// 	try {
	// 		while ((line = br.readLine()) != null){
	// 			myHash1.insertOrUpdate(line.split("#")[0], line);
	// 		}
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}

	// 	System.out.println(String.format("Method 1 had %d colisions", colisions));

	// 	// Method 2
	// 	ClosedHashingWithColision<String, String> myHash2= new ClosedHashingWithColision<>(f->hash(f));

	// 	br = new BufferedReader(in);
	// 	colisions = 0;
	// 	try {
	// 		while ((line = br.readLine()) != null){
	// 			myHash2.insertOrUpdate(line.split("#")[0], line);
	// 		}
	// 	} catch (IOException e) {
	// 		// TODO Auto-generated catch block
	// 		e.printStackTrace();
	// 	}
		
	// 	System.out.println(String.format("Method 2 had %d colisions", colisions));

	// }
}
