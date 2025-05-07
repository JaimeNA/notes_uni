import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import core.ExpTreeVars;
import core.ExpressionService;

public class ExpTreeTest {

    @Test
    void evaluateTest() {
        String input = "( 15 + 3 ) \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ExpressionService myExp = new ExpTreeVars();
        double rta= myExp.eval();
        assertEquals( 18, rta);

        input = "( ( 13 ^ 2 ) - ( 1 * 7 ) ) \n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myExp = new ExpTreeVars();
        rta= myExp.eval();
        assertEquals( 162, rta);

        input = "( 2 ^ ( 4 ^ 2 ) ) \n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myExp = new ExpTreeVars();
        rta= myExp.eval();
        assertEquals(65536, rta);

        System.setIn(System.in); // IMPORTANT
    }

    @Test
    void inorderExpressionTest() {
        String input = "( 15 + 3 ) \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ExpressionService myExp = new ExpTreeVars();
        System.setOut(System.out);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.inorder();
        assertEquals( "( 15 + 3 ) ", outContent.toString().replaceAll("\\s+", " "));

        input = "( ( 13 ^ 2 ) - ( 1 * 7 ) ) \n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myExp = new ExpTreeVars();
        System.setOut(System.out);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.inorder();
        assertEquals( "( ( 13 ^ 2 ) - ( 1 * 7 ) ) ", outContent.toString().replaceAll("\\s+", " "));

        System.setIn(System.in);
    
    }

    @Test
    void postorderExpressionTest() {
        String input = "( 15 + 3 ) \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ExpressionService myExp = new ExpTreeVars();
        System.setOut(System.out);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.postorder();
        assertEquals( "15 3 + ", outContent.toString().replaceAll("\\s+", " "));

        input = "( ( 13 ^ 2 ) - ( 1 * 7 ) ) \n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myExp = new ExpTreeVars();
        System.setOut(System.out);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.postorder();
        assertEquals( "13 2 ^ 1 7 * - ", outContent.toString().replaceAll("\\s+", " "));

        System.setIn(System.in);
    }

    @Test
    void preorderExpressionTest() {
        String input = "( 15 + 3 ) \n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        ExpressionService myExp = new ExpTreeVars();
        System.setOut(System.out);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.preorder();
        assertEquals( "+ 15 3 ", outContent.toString().replaceAll("\\s+", " "));

        input = "( ( 13 ^ 2 ) - ( 1 * 7 ) ) \n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        myExp = new ExpTreeVars();
        System.setOut(System.out);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        myExp.preorder();
        assertEquals( "- ^ 13 2 * 1 7 ", outContent.toString().replaceAll("\\s+", " "));

        System.setIn(System.in);
    }

    @Test
    void unbalancedParenthesdisTest () {
        String input = "( 2 + 3 ) \n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        
        ExpTreeVars myTree = new ExpTreeVars();
        assertNotNull(myTree);
        
        System.setIn(System.in);
    }

    @Test
    void unbalancedParenthesisTest () {
        String input = "( 2 + 3 ) ) \n";

        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Exception exception = assertThrows(RuntimeException.class, () -> new ExpTreeVars());
        System.setIn(System.in);
    }
}
