package hepl.genielogiciel;

import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.ArrayList;
import java.util.List;

public class CollectMethodsNames extends Java8BaseListener {

    private List<String> methodsNames= new ArrayList<>();

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        TerminalNode node = ctx.Identifier();
        String methodName = node.getText();
        methodsNames.add(methodName);
    }

    public boolean hasMethod(String name) {
        return methodsNames.contains(name);
    }
}
