import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import core.BST;

public class BSTreeTest {

    BST<Integer> tree;

    @BeforeEach
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
    void LCATest () {

        BST<Integer> LCAtree = new BST<>();

        LCAtree.insert(5);
		LCAtree.insert(70);
		LCAtree.insert(30);
		LCAtree.insert(35);
		LCAtree.insert(20);
		LCAtree.insert(40);
		LCAtree.insert(80);
		LCAtree.insert(90);
		LCAtree.insert(85);
        
        assertNull(LCAtree.getCommonNode(35, 35));
        assertNull(LCAtree.getCommonNode(0, 85));
        assertEquals(70, LCAtree.getCommonNode(70, 35));
        assertEquals(70, LCAtree.getCommonNode(40, 80));
    }

    @Test
    void containsTest () {
        assertEquals(true, tree.contains(10));
        assertEquals(false, tree.contains(15));
    }

    @Test
    void deleteTest () {
		tree.delete(40);
        assertEquals(true, tree.contains(40));
		tree.delete(40);
        assertEquals(false, tree.contains(40));
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
