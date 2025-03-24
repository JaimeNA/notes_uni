import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.KMP;

public class KMPTest {
    @Test
    public void findAllTest() {
        char[] query = "no".toCharArray();
        char[] target = "sino se los digo no se si es nocivo".toCharArray();
        ArrayList<Integer> res1 = KMP.findAll(query, target);

        Integer[] expectetd = {2, 17, 29};

        assertArrayEquals(expectetd, res1.toArray());

        query = "aaa".toCharArray();
        target = "aaabaaaaab".toCharArray();
        ArrayList<Integer> res2 = KMP.findAll(query, target);

        Integer[] expected = {0, 4, 5, 6};

        assertArrayEquals(expected, res2.toArray());
    }
}
