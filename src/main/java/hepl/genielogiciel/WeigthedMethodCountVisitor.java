package hepl.genielogiciel;

public class WeigthedMethodCountVisitor extends Java8BaseVisitor<Void> {
    private int methodCount = 0;

    @Override
    public Void visitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        String currentClassName =  ctx.normalClassDeclaration().Identifier().getText();
        methodCount = 0;
        super.visitClassDeclaration(ctx);
        Collector.getInstance().addElement(currentClassName, methodCount);
        return null;
    }

    @Override
    public Void visitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        methodCount++;
        return super.visitMethodDeclaration(ctx);
    }


}
