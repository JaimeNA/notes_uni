import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.ClosedHashingWithColision;
import core.HashBag;


public class HashBagTest {
    HashBag<Integer> bag;

    @BeforeEach 
    void initializeTest() {
        bag = new HashBag<>();
        bag.add(10);
        bag.add(20);
        bag.add(10);
        bag.add(10);
        
    }

    @Test
    void removeTest() {
		bag.remove(10);
		bag.remove(20);

        assertEquals(2, bag.getCount(10));
        assertEquals(0, bag.getCount(20));
    }

    @Test
    void addTest() {
        bag.add(10);
        bag.add(10);
        bag.add(20);
        bag.add(33);
        bag.add(20);
        
        assertEquals(3, bag.getCount(20));
        assertEquals(0, bag.getCount(5));
    }

}
