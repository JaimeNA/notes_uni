import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Levenshtein;

public class LevenshteinTest {

    @Test
    void getDistanceTest() {
        assertEquals(Levenshtein.getDistance("exkusa", "ex_amigo"), 6);
        assertEquals(Levenshtein.getDistance("big data", "bigdata"), 1);
    }

}
