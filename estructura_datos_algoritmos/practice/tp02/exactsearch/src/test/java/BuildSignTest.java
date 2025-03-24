import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import core.BuildSign;

public class BuildSignTest {
    @Test
    public void findAllTest() {
        int[] query = {5, 3, 5, 3};
        int[] target = {10, 5, 3, 5, 3, 5, 3, 5, 5, 5, 3, 5, 3, 5};
        ArrayList<Integer> res1 = BuildSign.findAll(query, target);

        Integer[] expectetd = {1, 3, 9};

        assertArrayEquals(expectetd, res1.toArray());

        int[] query2 = {5, 5, 5};
        int[] target2 = {10, 5, 3, 5, 3, 5, 3, 5, 5, 5, 3, 5, 3, 5};
        ArrayList<Integer> res2 = BuildSign.findAll(query2, target2);

        Integer[] expected = {7};

        assertArrayEquals(expected, res2.toArray());
    }
}
