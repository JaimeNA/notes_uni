import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.ClosedHashingWithColision;


public class ClosedHashingTest {
    ClosedHashingWithColision<Integer, String> myHash;

    @BeforeEach 
    void initializeTest() {
        myHash = new ClosedHashingWithColision<>(f->f);

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
    }

    @Test
    void removeTest() {
		myHash.remove(32);
		myHash.remove(52);

        assertEquals(8, myHash.size());
    }

    @Test
    void insertTest() {
		myHash.insertOrUpdate(32, "Lo");
		myHash.remove(52);
        
        assertEquals(10, myHash.size());
    }

}


