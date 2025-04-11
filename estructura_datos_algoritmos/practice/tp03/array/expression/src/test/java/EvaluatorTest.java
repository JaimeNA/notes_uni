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

        input = "13 ^ 2 - 1 * 7";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myEval = new Evaluator();
        rta= myEval.evaluate();
        assertEquals( 162, rta);

        input = "2 ^ 4 ^ 2";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myEval = new Evaluator();
        rta= myEval.evaluate();
        assertEquals(65536, rta);
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
        Method myMethod = Evaluator.class.getDeclaredMethod( "infixToPostfix", String.class);
        myMethod.setAccessible(true);

        String result = (String) myMethod.invoke(ev, "3 + 10 * 2 / 1");
        assertEquals("3 10 2 * 1 / +", result.trim().replaceAll("\\s+", " "));

        result = (String) myMethod.invoke(ev, "13 ^ 2 - 1 * 7");
        assertEquals("13 2 ^ 1 7 * -", result.trim().replaceAll("\\s+", " "));

        result = (String) myMethod.invoke(ev, "5 ^ 2 ^ 3 - 1");
        assertEquals("5 2 3 ^ ^ 1 -", result.trim().replaceAll("\\s+", " "));
    }
}

