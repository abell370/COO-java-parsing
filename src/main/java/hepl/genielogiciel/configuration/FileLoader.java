package hepl.genielogiciel.configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileLoader extends ConfigurationLoader {


    public FileLoader(String path) {
        super(path);
    }

    @Override
    public Map<String, Integer> getMetricsMap() throws Exception {
        Map<String, Integer> data = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(super.getPath()));
        String line = null;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(":");
            data.put(values[0], Integer.parseInt(values[1]));
        }
        return data;
    }
}
