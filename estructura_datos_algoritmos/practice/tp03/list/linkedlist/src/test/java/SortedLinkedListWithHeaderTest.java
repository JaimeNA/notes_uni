import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.SortedLinkedListWithHeader;


public class SortedLinkedListWithHeaderTest {
    SortedLinkedListWithHeader<String> l;

    @BeforeEach
    void initialize() {
		l = new SortedLinkedListWithHeader<>();
	
		System.out.println(l.insert("hola"));
		System.out.println(l.insert("tal"));
		System.out.println(l.insert("ah"));
		System.out.println(l.insert("veo"));
    }

    @Test
    void insertTest() {

        assertEquals(false, l.insert("tal"));
        assertEquals(false, l.insert("veo"));

		l.insert("bio");
		l.insert("tito");
		l.insert("hola");
		l.insert("aca");

        assertEquals("aca", l.getMin());
        assertEquals("veo", l.getMax());
    }

    @Test
    void sizeTest() {
        assertEquals(4, l.size());
    }

    @Test
    void removeTest() {
		l.insert("bio");
		l.insert("tito");
		l.insert("hola");
		l.insert("aca");
        
		assertEquals(true, l.remove("veo"));
		assertEquals(false, l.find("veo"));
        assertEquals("tito", l.getMax());
    }
}

