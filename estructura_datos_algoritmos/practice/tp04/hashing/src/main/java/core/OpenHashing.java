package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;

public class OpenHashing<K, V> implements IndexParametricService<K, V> {
	final private int initialLookupSize= 10;

	@SuppressWarnings({"unchecked"})
	private LinkedList<Slot<K,V>>[] Lookup= (LinkedList<Slot<K,V>>[]) new LinkedList[initialLookupSize];

	private Function<? super K, Integer> prehash;

	private static final float THRESHOLD = 0.75f;
	private int size;

	public OpenHashing( Function<? super K, Integer> mappingFn) {
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

        if (Lookup[hash(key)] == null) 
            Lookup[hash(key)] = new LinkedList<>();
        
        Slot<K, V> aux = new Slot<K, V>(key, data);
        int idx = Lookup[hash(key)].indexOf(aux);

        if (idx < 0) {
            Lookup[hash(key)].addLast(new Slot<K, V>(key, data));
            size++;
        } else
            Lookup[hash(key)].get(idx).value = data;


		if (loadFactor() > THRESHOLD) 
			rehash();
	}

	// Simple version, aux array
	@SuppressWarnings({"unchecked"})
	private void rehash(){
		LinkedList<Slot<K,V>>[] aux = Lookup;
		Lookup = (LinkedList<Slot<K,V>>[]) new LinkedList[Lookup.length * 2];
		size = 0;

		for (LinkedList<Slot<K,V>> list : aux) {
			if (list != null) {
                for (Slot<K, V> e : list) {
                    insertOrUpdate(e.key, e.value);
                }
            }
		}
	}
	
	// find or get
	public V find(K key) {
		if (key == null)
			return null;
        
        if (Lookup[hash(key)] == null) 
            return null;

        Slot<K, V> aux = new Slot<K, V>(key, null);
        int idx = Lookup[hash(key)].indexOf(aux);
    
        if (idx >= 0) 
            return Lookup[hash(key)].get(idx).value;

		return null;
	}

    // Maybe a map would be better
	public boolean remove(K key) {
		if (key == null)
			return false;
		
        if (Lookup[hash(key)] == null) 
            return false;

        Slot<K, V> aux = new Slot<K, V>(key, null);
        int idx = Lookup[hash(key)].indexOf(aux);

        if (idx < 0) 
            return false;
        else
            Lookup[hash(key)].remove(idx);
        
        size--;
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

    // Doing local load factor
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

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (o instanceof Slot) {
                Slot<K, V> slot = (Slot<K, V>) o;
                return slot.key.equals(key);
            }

            return false;
        }
	
		public String toString() {
		 return String.format("(key=%s, value=%s)", key, value );
		}
	}
	
	
	public static void main(String[] args) {
		OpenHashing<Integer, String> myHash= new OpenHashing<>(f->f);
		myHash.insertOrUpdate(55, "Ana");
		myHash.insertOrUpdate(44, "Juan");
		myHash.insertOrUpdate(18, "Paula");
		myHash.insertOrUpdate(19, "Lucas");
		myHash.insertOrUpdate(30, "Lo");
		myHash.insertOrUpdate(32, "Misa");
		myHash.insertOrUpdate(52, "Mi");
		myHash.insertOrUpdate(63, "Esther");
		myHash.insertOrUpdate(67, "Layla");
		myHash.insertOrUpdate(66, "Alex");
		myHash.dump();

		System.out.println(String.format("Current size is %d, and load factor is %f", myHash.size(), myHash.loadFactor()));
		
	}

}