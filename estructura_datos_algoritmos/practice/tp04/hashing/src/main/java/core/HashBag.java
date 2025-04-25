package core;

public class HashBag<T> {

	private static final float THRESHOLD = 0.75f;
    private final static int INITIAL_SIZE = 10;
    private int size;
    private Slot<T>[] lookup;

    @SuppressWarnings("unchecked")
    public HashBag() {
        size = 0;
        lookup = (Slot<T>[]) new Slot[INITIAL_SIZE];
    }

    public int getCount(final T value) {
        int i = find(value);
        if (i < 0)
            return 0;

        return lookup[i].count;
    }

    public int size() {
        return size;
    }

    public int find(final T value) {
        int i = hash(value);
        int toReturn = -1;

        int stopIndex = (i + lookup.length - 1) % lookup.length;
        while (lookup[i] != null && !lookup[i].value.equals(value) && i != stopIndex) {
            i = (i + 1) % lookup.length;
        }

        if (lookup[i] != null && lookup[i].value.equals(value))
            toReturn = i;

        return toReturn;
    }

    private void addByAmount(final T value, final int amount) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        int i = hash(value);
        int lastDeleted = -1;

        int stopIndex = (i + lookup.length - 1) % lookup.length;
        while (lookup[i] != null && !lookup[i].value.equals(value) && i != stopIndex) {

            if (lastDeleted == -1 && lookup[i].deleted)
                lastDeleted = i;

            i = (i + 1) % lookup.length;
        }

        if (lookup[i] == null) {
            Slot<T> toAdd = new Slot<>(value);
            toAdd.count = amount;

            if (lastDeleted != -1) 
                lookup[lastDeleted] = toAdd;
            else 
                lookup[i] = toAdd;

            size++;
        } else {
            lookup[i].count += amount;
        }

        if (loadFactor() > THRESHOLD)
            rehash();
    }

    public void add(final T value) {
        addByAmount(value, 1);
    }

    public void remove(final T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        int i = find(value);

        if (i != -1) {
            if (lookup[i].count > 1) {  // If bag not empty, reduce size
                lookup[i].count--;
            } else {    // If bag empty, delete it
                int next = (i + 1) % lookup.length;

                if (lookup[next] != null) { // Is a step
                    lookup[i].deleted = true;
                } else {
                    lookup[i] = null;
                }
                size--;
            }
        }
    }

    @SuppressWarnings({"unchecked"})
	private void rehash(){
		Slot<T>[] aux =lookup;
		lookup = (Slot<T>[]) new Slot[lookup.length * 2];

		size = 0;

		for (int i = 0; i < aux.length; i++) {
			Slot<T> e = aux[i];

			if (e != null && !e.deleted)
				addByAmount(e.value, e.count);
		}
	}

	private float loadFactor() {
		return (float)size() / lookup.length;
	}

    private int hash (final T value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");

        return value.hashCode() % lookup.length;
    }

	static private final class Slot<K>	{
		private final K value;
        private int count;
		private boolean deleted;
		
		private Slot(K value){
			this.value = value;
            this.count = 1;
			this.deleted = false;
		}

        @Override
		public String toString() {
		    return String.format("(value=%s)", value);
		}
	}
	
}
