
import org.junit.jupiter.api.Test;

import core.C1_2024.IndexWithDuplicates;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

class IndexWithDuplicatesTest {

    IndexWithDuplicates index = new IndexWithDuplicates();

    @Test
    void testCase1() {
        index.initialize(new int[] {1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8});
        index.merge(index2);
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        assertArrayEquals(expected, index.getIndexedData());
    }

    @Test
    void testCase2() {
        index.initialize(new int[] {1, 1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 4, 6, 8});
        index.merge(index2);
        int[] expected = {1, 1, 2, 3, 4, 4, 5, 6, 7, 8};

        assertArrayEquals(expected, index.getIndexedData());
    }

    @Test
    void testCase3() {
        index.initialize(new int[] {1, 3, 5});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8, 10});
        index.merge(index2);
        int[] expected = {1, 2, 3, 4, 5, 6, 8, 10};

        assertArrayEquals(expected, index.getIndexedData());
    }

}