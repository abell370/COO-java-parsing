package hepl.genielogiciel.configuration;

import java.util.Map;

public abstract class ConfigurationLoader {
    private final String path;
    public ConfigurationLoader(String path) {
        this.path = path;
    }
    public abstract Map<String, Integer> getMetricsMap() throws Exception;
    public String getPath() {
        return this.path;
    }
}
