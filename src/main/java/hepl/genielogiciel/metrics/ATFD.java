package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;

public class ATFD extends Metric {
    public ATFD(int threshold) {
        super(threshold);
    }

    @Override
    public void walkOnTree(ParseTree tree) {

    }

    @Override
    public String applyTheRule() {
        return this.getClass().getName() + "\nNONE";
    }
}
