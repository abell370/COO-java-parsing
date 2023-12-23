package hepl.genielogiciel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Before;
import org.junit.Test;


public class CollectMethodsNamesTest {

    ParseTree tree;
    CustomVisitor visitor = new CustomVisitor();
    @Before
    public void setUp() throws Exception {
        String javaClassContent = "public class GreatClass { private int i = 0; void greatJob(){} void littleJob(){} }";
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        tree = parser.compilationUnit();
    }

    @Test
    public void collectMethodsNames() {
        visitor.visit(tree);
        assertTrue(visitor.hasMethod("greatJob"));
        assertFalse(visitor.hasMethod("method"));
    }

}
