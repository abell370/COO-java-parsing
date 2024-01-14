package hepl.genielogiciel.project;

import hepl.genielogiciel.Java8Lexer;
import hepl.genielogiciel.Java8Parser;
import hepl.genielogiciel.configuration.ConfigurationLoader;
import hepl.genielogiciel.configuration.ConfigurationParser;
import hepl.genielogiciel.configuration.FileLoader;
import hepl.genielogiciel.metrics.Metric;
import hepl.genielogiciel.metrics.factory.ConcreteMetricFactory;
import hepl.genielogiciel.metrics.factory.MetricFactory;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;

public class ProjectLoader implements FileVisitor<Path> {
    private final Map<String, Integer> configurationData;
    public ProjectLoader(Map<String, Integer> configurationData) {
        this.configurationData = configurationData;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        ParseTree tree = generateTree(file);
        try {
            applyMetrics(tree);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return FileVisitResult.CONTINUE;
    }

    private void applyMetrics(ParseTree tree) throws Exception {
        MetricFactory factory = new ConcreteMetricFactory();
        ConfigurationParser configurationParser = new ConfigurationParser(factory);
        List<Metric> metrics = configurationParser.parseConfiguration(this.configurationData);
        for (var metric : metrics) {
            metric.walkOnTree(tree);
            String result = metric.applyTheRule();
            System.out.println(result);
        }
    }

    private static ParseTree generateTree(Path file) throws IOException {
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromPath(file));
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        return parser.compilationUnit();
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
