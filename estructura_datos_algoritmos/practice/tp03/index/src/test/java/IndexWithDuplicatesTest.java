import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eda.IndexService;
import eda.IndexWithDuplicates;


public class IndexWithDuplicatesTest {

    IndexService myIndex = new IndexWithDuplicates();


    @Test
    void ocurrencesTest() {
        assertEquals(myIndex.occurrences(10), 0);
    }

    @Test
    void insertAndDeleteTest() {

        myIndex.delete(10);
        assertEquals(myIndex.occurrences(10), 0);


        myIndex.insert(10);
        myIndex.insert(10);
        myIndex.insert(10);
        assertEquals(myIndex.occurrences(10), 3);

        myIndex.delete(10);
        assertEquals(myIndex.occurrences(10), 2);

        myIndex.insert(10);
        myIndex.insert(4);
        myIndex.insert(76);
        assertEquals(myIndex.getMax(), 76);
        assertEquals(myIndex.getMin(), 4);
    }

    @Test
    void rangeTest() {

        myIndex.insert(10);
        myIndex.insert(10);
        myIndex.insert(10);
        myIndex.insert(4);
        myIndex.insert(76);

        int[] range = myIndex.range(10, 76, true, false);

        
        int[] expectetd = {10, 10, 10};

        assertArrayEquals(expectetd, range);
    }
}

