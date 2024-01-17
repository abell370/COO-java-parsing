package hepl.genielogiciel.metrics.factory;

import hepl.genielogiciel.metrics.Metric;

import java.lang.reflect.Constructor;

public class ConcreteMetricFactory extends MetricFactory {
    @Override
    public Metric createMetric(String className, int threshold) throws Exception {
        Constructor<?> c = Class.forName(className).getDeclaredConstructor(int.class);
        return (Metric) c.newInstance(new Object[]{threshold});
    }
}
