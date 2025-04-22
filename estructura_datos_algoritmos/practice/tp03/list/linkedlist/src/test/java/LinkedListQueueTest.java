
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.LoopLinkedListQueue;
import core.SortedLinkedList;


public class LinkedListQueueTest {
    LoopLinkedListQueue<String> l;

    @BeforeEach
    void initialize() {
		l = new LoopLinkedListQueue<>();
	
		l.enqueue("hola");
		l.enqueue("tal");
		l.enqueue("ah");
		l.enqueue("veo");
    }

    @Test
    void dequeueTest() {

        assertEquals("hola", l.dequeue());
        assertEquals("tal", l.dequeue());

    }

    @Test
    void enqueueTest() {

        l.dequeue();
        l.dequeue();
        l.dequeue();
        l.dequeue();

		l.enqueue("hola");
		l.enqueue("tal");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");
		l.enqueue("ah");

        assertEquals("hola", l.dequeue());
        assertEquals("tal", l.dequeue());

    }
}

