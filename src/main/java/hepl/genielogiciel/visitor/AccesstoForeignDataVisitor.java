
package hepl.genielogiciel.visitor;

import hepl.genielogiciel.Java8BaseVisitor;
import hepl.genielogiciel.Java8Parser;

import java.util.Map;

public class AccesstoForeignDataVisitor extends Java8BaseVisitor<Void> {
    private int attributeCount = 0;
    String currentClassName;
    private Map<String, Integer> data;

    public AccesstoForeignDataVisitor(Map<String, Integer> data){
        this.data = data;
    }

    @Override
    public Void visitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        currentClassName = ctx.normalClassDeclaration().Identifier().getText();
        attributeCount = 0;
        super.visitClassDeclaration(ctx);
        data.put(currentClassName, attributeCount);
        return null;
    }

    @Override
    public Void visitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {
        attributeCount++;
        return super.visitFieldDeclaration(ctx);
    }

    public Map<String, Integer> getData() {
        return data;
    }

}
