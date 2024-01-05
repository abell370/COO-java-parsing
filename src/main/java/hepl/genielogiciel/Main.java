package hepl.genielogiciel;

import hepl.genielogiciel.configuration.ConfigurationLoader;
import hepl.genielogiciel.configuration.FileLoader;
import hepl.genielogiciel.metrics.Metric;
import hepl.genielogiciel.metrics.factory.ConcreteMetricFactory;
import hepl.genielogiciel.configuration.ConfigurationParser;
import hepl.genielogiciel.metrics.factory.MetricFactory;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String javaClassContent = "public class GreatClass { private int i = 0; void greatJob(){} void littleJob(){} void littleJobBis(){} } public class GreatClassBis { void helloWorld(){} }";
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(javaClassContent));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();
        String path = "src/main/resources/configuration.txt";
        ConfigurationLoader loader = new FileLoader(path);
        Map<String, Integer> configurationData = loader.getMetricsMap();

        MetricFactory factory = new ConcreteMetricFactory();
        ConfigurationParser configurationParser = new ConfigurationParser(factory);
        List<Metric> metrics = configurationParser.parseConfiguration(configurationData);
        for (var metric : metrics) {
            metric.walkOnTree(tree);
            String result = metric.applyTheRule();
            System.out.println(result);
        }

    }
}
