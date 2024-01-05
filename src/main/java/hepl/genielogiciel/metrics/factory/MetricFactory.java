package hepl.genielogiciel.metrics.factory;

import hepl.genielogiciel.metrics.Metric;

public abstract class MetricFactory {
    public abstract Metric createMetric(String className, int threshold) throws Exception;
}
