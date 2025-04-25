package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.Function;

public class ClosedHashing<K, V> implements IndexParametricService<K, V> {
	final private int initialLookupSize= 10;

	@SuppressWarnings({"unchecked"})
	private Slot<K,V>[] Lookup= (Slot<K,V>[]) new Slot[initialLookupSize];

	private Function<? super K, Integer> prehash;

	private static final float THRESHOLD = 0.75f;
	private int size;

	public ClosedHashing( Function<? super K, Integer> mappingFn) {
		if (mappingFn == null)
			throw new RuntimeException("fn not provided");

		prehash= mappingFn;
	}

	// ajuste al tama�o de la tabla
	private int hash(K key) {
		if (key == null)
			throw new IllegalArgumentException("key cannot be null");

		return prehash.apply(key) % Lookup.length;
	}
	
	public void insertOrUpdate(K key, V data) {
		if (key == null || data == null) {
			String msg= String.format("inserting or updating (%s,%s). ", key, data);
			if (key==null)
				msg+= "Key cannot be null. ";
			
			if (data==null)
				msg+= "Data cannot be null.";
		
			throw new IllegalArgumentException(msg);
		}
		
		if (Lookup[ hash( key) ] == null) {	// If there is nothing there yet, increase number of elements
			Lookup[ hash( key) ] = new Slot<K, V>(key, data);
			size ++;
		} else if (key == Lookup[hash(key)].key){ // Same key, update data
			Lookup[ hash( key) ].value = data;
		} else {	// If the keys arent the same yet they get the same hash
			throw new RuntimeException(String.format("Colision entre %s y %s con hash %d", data, Lookup[hash(key)].value, hash(key)));
		}

		if (loadFactor() > THRESHOLD) 
			rehash();
	}

	// Simple version, aux array
	@SuppressWarnings({"unchecked"})
	private void rehash(){
		Slot<K,V>[] aux = Lookup;
		Lookup = (Slot<K,V>[]) new Slot[Lookup.length * 2];
		size = 0;

		for (Slot<K, V> e : aux) {
			if (e != null)
				insertOrUpdate(e.key, e.value);
		}
	}
	
	// find or get
	public V find(K key) {
		if (key == null)
			return null;

		Slot<K, V> entry = Lookup[hash(key)];
		if (entry == null)
			return null;

		return entry.value;
	}

	public boolean remove(K key) {
		if (key == null)
			return false;
		
		// lo encontre?
		if (Lookup[ hash( key) ] == null)
			return false;
		
		Lookup[ hash( key) ] = null;
		return true;
	}

	
	public void dump()  {
		for(int rec= 0; rec < Lookup.length; rec++) {
			if (Lookup[rec] == null)
 				System.out.println(String.format("slot %d is empty", rec));
			else
				System.out.println(String.format("slot %d contains %s",rec, Lookup[rec]));
		}
	}
	

	public int size() {
		return this.size;
	}

	public float loadFactor() {
		return (float)size() / Lookup.length;
	}

	static private final class Slot<K, V>	{
		private final K key;
		private V value;
		
		private Slot(K theKey, V theValue){
			key= theKey;
			value= theValue;
		}

	
		public String toString() {
		 return String.format("(key=%s, value=%s)", key, value );
		}
	}
	
	
	// public static void main(String[] args) {
	// 	ClosedHashing<Integer, String> myHash= new ClosedHashing<>(f->f);
	// 	myHash.insertOrUpdate(55, "Ana");
	// 	myHash.insertOrUpdate(44, "Juan");
	// 	myHash.insertOrUpdate(18, "Paula");
	// 	myHash.insertOrUpdate(19, "Lucas");
	// 	myHash.insertOrUpdate(30, "Lo");
	// 	myHash.insertOrUpdate(52, "Mi");
	// 	myHash.insertOrUpdate(63, "Esther");
	// 	myHash.insertOrUpdate(67, "Layla");
	// 	myHash.insertOrUpdate(66, "Alex");
	// 	myHash.dump();

	// 	System.out.println(String.format("Current size is %d, and load factor is %f", myHash.size(), myHash.loadFactor()));
		
	// }
	

	private static Integer hash(String elem) {
		int sum = 0;

		for(char c: elem.toCharArray()) {
			sum += (int)c;
		}

		return sum;
	}

	public static void main(String[] args) {

		// Read from file
		String fileName = "amazon-categories30.txt";
		InputStream is = ClosedHashing.class.getClassLoader().getResourceAsStream(fileName);

		Reader in = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(in);

		String line;

		// Method 1
		ClosedHashing<String, String> myHash1= new ClosedHashing<>(f->(int)f.charAt(0));

		int colisions = 0;
		try {
			while ((line = br.readLine()) != null){
				try {
					myHash1.insertOrUpdate(line.split("#")[0], line);
				} catch(RuntimeException e) {
					colisions++;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(String.format("Method 1 had %d colisions", colisions));

		// Method 2
		ClosedHashing<String, String> myHash2= new ClosedHashing<>(f->hash(f));

		br = new BufferedReader(in);
		colisions = 0;
		try {
			while ((line = br.readLine()) != null){
				try {
					myHash2.insertOrUpdate(line.split("#")[0], line);
				} catch(RuntimeException e) {
					colisions++;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(String.format("Method 2 had %d colisions", colisions));

	}

}