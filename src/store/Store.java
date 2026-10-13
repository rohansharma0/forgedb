package store;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private static final Map<String, String> storage = new HashMap<>();

    private Store(){
    }

    public static void set(String key, String value) {
        storage.put(key, value);
    }

    public static String get(String key){
        return storage.get(key);
    }
}