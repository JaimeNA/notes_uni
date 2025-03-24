import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Levenshtein;
import wrappers.LevenshteinWrap;

public class LevenshteinTestWrap {

    private LevenshteinWrap l = new LevenshteinWrap();

    @Test
    void getDistanceTest() {
        assertEquals(Levenshtein.getDistance("exkusa", "ex_amigo"), 6);
        assertEquals(Levenshtein.getDistance("big data", "bigdata"), 1);
    }

}
