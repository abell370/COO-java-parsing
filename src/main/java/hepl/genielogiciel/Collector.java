package hepl.genielogiciel;

import java.util.HashMap;
import java.util.Map;

public class Collector {
    public static Collector instance = null;
    private final Map<String, Integer> wmcMap = new HashMap<>();

    public static Collector getInstance() {
        if (instance == null)
            instance = new Collector();
        return instance;
    }

    private Collector(){};

    public void addElement(String key, Integer value) {
        if (wmcMap.containsKey(key)) {
            wmcMap.replace(key, value);
        } else {
            wmcMap.put(key, value);
        }
    }

    public void print() {
        var keys = wmcMap.keySet();
        for(String key : keys) {
            System.out.println(key+" has "+wmcMap.get(key)+" method");
        }
    }
}
