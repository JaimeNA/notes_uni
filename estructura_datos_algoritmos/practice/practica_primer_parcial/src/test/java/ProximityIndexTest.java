

import org.junit.jupiter.api.Test;

import core.C1_2022.ProximityIndex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class ProximityIndexTest {
    private ProximityIndex index;

    @BeforeEach
    void initIndex(){
        index = new ProximityIndex();
        index.initialize(new String[] {"Ana", "Carlos", "Juan", "Yolanda"});
    }

    @Test
    void indexTest1() {
        assertEquals(index.search("Carlos", 2), "Yolanda");
    }

    @Test
    void indexTest2() {
        assertEquals(index.search("Carlos", 0), "Carlos");
    }

    @Test
    void indexTest3() {
        assertEquals(index.search("Carlos", 3), "Ana");
    }

    @Test
    void indexTest4() {
        assertEquals(index.search("Ana", 14), "Juan");
    }

    @Test
    void indexTest5() {
        assertEquals(index.search("Ana", -2), "Juan");
    }

    @Test
    void indexTest6() {
        assertEquals(index.search("Ana", -17), "Yolanda");
    }

    @Test
    void indexTest7() {
        assertEquals(index.search("Juan", -4), "Juan");
    }

    @Test
    void notInElementsTest(){
        assertNull(index.search("XXX", -4));
    }

    @Test
    void nullElementsTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            index.initialize(null);
        });
    }

    @Test
    void emptyElementsTest(){
        index.initialize(new String[]{});
        assertNull(index.search("XXX", 2));
    }

    @Test
    void unsortedArrayTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            index.initialize(new String[]{"Carlos", "Ana", "Yolanda"});
        });
    }

    @Test
    void repeatedArrayTest(){
        assertThrows(IllegalArgumentException.class, () -> {
            index.initialize(new String[]{"Ana", "Ana", "Carlos", "Yolanda"});
        });
    }
}
