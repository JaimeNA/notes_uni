import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eda.IndexService;
import eda.IndexWithDuplicates;


public class IndexWithDuplicatesTest {

    IndexService<Integer> myIndex = new IndexWithDuplicates<>();


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

        myIndex.initialize(new Integer[] {100, 50, 30, 50, 80, 100, 100, 30}); // 30, 30, 50, 50, 80, 100, 100, 100

        Integer[] range = myIndex.range(50, 100, false, false);
        Integer[] expected = {80};

        assertArrayEquals(expected, range);
        
        range = myIndex.range(30, 50, true, false);
        Integer[] expected1 = {30, 30};

        assertArrayEquals(expected1, range);
        
        range = myIndex.range(45, 100, false, false);
        Integer[] expected2 = {50, 50, 80};

        assertArrayEquals(expected2, range);
        
        range = myIndex.range(45, 100, true, false);
        Integer[] expected3 = {50, 50, 80};

        assertArrayEquals(expected3, range);

        range = myIndex.range(10, 20, false, false);
        Integer[] expected4 = {};

        assertArrayEquals(expected4, range);

        range = myIndex.range(100, 45, false, false);
        Integer[] expected5 = {};

        assertArrayEquals(expected5, range);
    }
}

