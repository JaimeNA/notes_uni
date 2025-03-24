import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import wrappers.*;

public class SoundexTestWrap {
    private SoundexWrap s = new SoundexWrap();

    @Test
    void getWordCodeTest() {
        assertEquals(s.getWordCode("threshold"), "T624");
        assertEquals(s.getWordCode("hold"), "H430");
        assertEquals(s.getWordCode("zresjoulding"), "Z624");
        assertEquals(s.getWordCode("phone"), "P500");
    }

    @Test
    void getLikenessTest() {
        assertEquals(s.getLikeness("threshold", "hold"), 0.0f);
        assertEquals(s.getLikeness("threshold", "zresjoulding"), 0.75f);
        assertEquals(s.getLikeness("phone", "foun"), 0.75f);
    }
}
