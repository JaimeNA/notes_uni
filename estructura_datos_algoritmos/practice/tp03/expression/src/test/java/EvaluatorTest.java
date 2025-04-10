import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import core.Evaluator;

public class EvaluatorTest {

    @Test
    void evaluateTest() {
        String input = "15 + 3";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Evaluator myEval = new Evaluator();
        double rta= myEval.evaluate();
        assertEquals( 18, rta);
    }

    // Weird way to test private methods(looks awful)
    @Test
    void infixToPostfixTest() throws NoSuchMethodException, SecurityException,
        IllegalAccessException, IllegalArgumentException,
        InvocationTargetException {

        String input = " ";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Evaluator ev = new Evaluator();
        Method myMethod = Evaluator.class.getDeclaredMethod( "infixoPostfix", String.class);
        myMethod.setAccessible(true);

        String result = (String) myMethod.invoke(ev, "3 + 10 * 2 / 1");
        assertEquals("3 10 2 * 1 / +", result.trim().replaceAll("\\s+", " "));
    }
}

