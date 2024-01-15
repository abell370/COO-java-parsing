package hepl.genielogiciel.visitor;

import hepl.genielogiciel.Java8BaseVisitor;
import hepl.genielogiciel.Java8Parser;

import java.util.Map;

public class WeigthedMethodCountVisitor extends Java8BaseVisitor<Void> {
    private int methodCount = 0;
    private Map<String, Integer> data;

    public WeigthedMethodCountVisitor(Map<String, Integer> data){
        this.data = data;
    }

    @Override
    public Void visitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        String currentClassName =  ctx.normalClassDeclaration().Identifier().getText();
        methodCount = 0;
        super.visitClassDeclaration(ctx);
        data.put(currentClassName, methodCount);
        return null;
    }

    @Override
    public Void visitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        methodCount++;
        return super.visitMethodDeclaration(ctx);
    }


}
