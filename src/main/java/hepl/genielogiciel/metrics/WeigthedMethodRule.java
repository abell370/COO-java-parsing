package hepl.genielogiciel.metrics;

import hepl.genielogiciel.Java8BaseVisitor;
import hepl.genielogiciel.visitor.WeigthedMethodCountVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class WeigthedMethodRule extends Metric {
    private final Map<String, Integer> wmcMap = new HashMap<>();
    private final Java8BaseVisitor<?> myVisitor = new WeigthedMethodCountVisitor(this.wmcMap);

    public WeigthedMethodRule(int threshold){
        super(threshold);
    }

    @Override
    public void walkOnTree(ParseTree tree) {
        this.myVisitor.visit(tree);
    }

    @Override
    public String applyTheRule() {
        StringBuilder result = new StringBuilder(this.getClass().getName() + "\n");
        var keys = wmcMap.keySet();
        for(String key : keys) {
            String tmp = wmcMap.get(key) >= super.getThreshold() ? key + " is not conform\n" : key + " is conform\n";
            result.append(tmp);
        }
        return result.toString();
    }
}
