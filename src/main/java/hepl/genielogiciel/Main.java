package hepl.genielogiciel;

import hepl.genielogiciel.configuration.ConfigurationLoader;
import hepl.genielogiciel.configuration.FileLoader;
import hepl.genielogiciel.project.ProjectLoader;
import hepl.genielogiciel.visitor.AccesstoForeignDataVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        String projectPath = "src/main/resources/project";
        String path = "src/main/resources/configuration.txt";

        ConfigurationLoader loader = new FileLoader(path);
        FileVisitor<Path> fileVisitor = new ProjectLoader(loader.getMetricsMap());

        Files.walkFileTree(Paths.get(projectPath), fileVisitor);

    }
}
