package hepl.genielogiciel;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
    public static void main(String[] args) {
        String javaClassContent = "public class GreatClass { private int i = 0; void greatJob(){} void littleJob(){} void littleJobBis(){} } public class GreatClassBis { void helloWorld(){} }";
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();
        Collector collector = Collector.getInstance();
        WeigthedMethodCountVisitor visitor = new WeigthedMethodCountVisitor();
        visitor.visit(tree);
        collector.print();

    }
}
