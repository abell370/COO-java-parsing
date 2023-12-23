package hepl.genielogiciel;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class CustomVisitor extends Java8BaseVisitor<String> {

    private List<String> methodsNames= new ArrayList<>();

    @Override
    public String visitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        Java8Parser.MethodDeclaratorContext header = ctx.methodDeclarator();
        TerminalNode node = header.Identifier();
        String methodName = node.getText();
        methodsNames.add(methodName);
        return super.visitMethodHeader(ctx);
    }

    public boolean hasMethod(String name) {
        return methodsNames.contains(name);
    }

}
