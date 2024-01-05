package hepl.genielogiciel.metrics;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;

public abstract class Metric {
    private final int threshold;

    public Metric(int threshold) {
        this.threshold = threshold;
    }

    public int getThreshold(){return this.threshold;}
    public abstract void walkOnTree(ParseTree tree);
    public abstract String applyTheRule();

}
