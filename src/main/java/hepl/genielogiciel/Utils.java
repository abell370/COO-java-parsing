package hepl.genielogiciel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    public static String convertFilesToString(String projectPath,Set<String> files) {
        return files.stream()
                .map(file -> {
                    try {
                        return Files.readString(Path.of(projectPath+"/"+file), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining());
    }

    // Code tiré de : https://www.baeldung.com/java-list-directory-files
    public static Set<String> getFilesNames(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public static String join(String[] array, String delimiter) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(delimiter);
            }
        }
        return result.toString();
    }
}
