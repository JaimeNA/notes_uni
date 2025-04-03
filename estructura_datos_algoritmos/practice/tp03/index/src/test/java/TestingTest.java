import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import eda.IndexService;
import eda.IndexWithDuplicates;

public class TestingTest {
    IndexService myIndex = new IndexWithDuplicates();


    @Test
    void ocurrencesTest() {
        assertEquals(myIndex.occurrences(10), 0);
    }
}
