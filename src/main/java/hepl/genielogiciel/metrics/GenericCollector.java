package hepl.genielogiciel.metrics;

public class GenericCollector<T> {

    private final T data;

    public GenericCollector(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }


}
