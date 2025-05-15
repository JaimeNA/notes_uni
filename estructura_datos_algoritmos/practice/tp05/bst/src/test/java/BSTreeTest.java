

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import core.BST;
import core.BSTreeInterface;

public class BSTreeTest {

    BSTreeInterface<Integer> tree;

    @BeforeAll
    void initialize() {
        tree = new BST<>();

        tree.insert(50);
		tree.insert(60);
		tree.insert(80);
		tree.insert(20);
		tree.insert(70);
		tree.insert(40);
		tree.insert(44);
		tree.insert(10);
		tree.insert(40);
    }

    @Test
    void containsTest () {
        assertEquals(true, tree.contains(10));
        assertEquals(false, tree.contains(15));
    }

    @Test
    void minTest () {
        assertEquals(10, tree.getMin());
		tree.insert(5);
        assertEquals(5, tree.getMin());
    }

    @Test
    void maxTest () {
        assertEquals(80, tree.getMax());
		tree.insert(100);
        assertEquals(100, tree.getMax());
    }
}
