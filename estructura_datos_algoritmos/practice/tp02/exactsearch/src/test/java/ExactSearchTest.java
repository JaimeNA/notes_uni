import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.ExactSearch;

public class ExactSearchTest {

    @Test
    void idexOftest() {
        assertEquals(ExactSearch.indexOf("abracadabra", "ra"), 2);
        assertEquals(ExactSearch.indexOf("abracadabra", "abra"), 0);
        assertEquals(ExactSearch.indexOf("abracadabra", "aba"), -1);
        assertEquals(ExactSearch.indexOf("ab", "aba"), -1);
        assertEquals(ExactSearch.indexOf("xa", "aba"), -1);
        assertEquals(ExactSearch.indexOf("abracadabras", "abras"), 7);
    }

}
