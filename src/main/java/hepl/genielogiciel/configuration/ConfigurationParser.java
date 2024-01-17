package hepl.genielogiciel.configuration;

import hepl.genielogiciel.metrics.Metric;
import hepl.genielogiciel.metrics.factory.MetricFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConfigurationParser {

    private final MetricFactory factory;

    public ConfigurationParser(MetricFactory factory) {
        this.factory = factory;
    }

    public List<Metric> parseConfiguration(Map<String, Integer> values) throws Exception {
        List<Metric> metrics =  new LinkedList<>();
        for (var key : values.keySet()) {
            metrics.add(this.factory.createMetric(key, values.get(key)));
        }
        return metrics;
    }
}
