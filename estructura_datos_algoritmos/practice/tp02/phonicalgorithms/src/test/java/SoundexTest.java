import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import core.Soundex;

public class SoundexTest {

    @Test
    void getWordCodeTest() {
        assertEquals(Soundex.getWordCode("threshold"), "T624");
        assertEquals(Soundex.getWordCode("hold"), "H430");
        assertEquals(Soundex.getWordCode("zresjoulding"), "Z624");
        assertEquals(Soundex.getWordCode("phone"), "P500");
    }

    @Test
    void getLikenessTest() {
        assertEquals(Soundex.getLikeness("threshold", "hold"), 0.0f);
        assertEquals(Soundex.getLikeness("threshold", "zresjoulding"), 0.75f);
        assertEquals(Soundex.getLikeness("phone", "foun"), 0.75f);
    }
}
