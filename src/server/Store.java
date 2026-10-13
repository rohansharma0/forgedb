package server;

import java.util.HashMap;

public class Store {

    private static final HashMap<String,String> map = new HashMap<>();

    private Store(){
    }

    public static String get(String key){
        return map.get(key);
    }

    public static void set(String key, String value){
        map.put(key,value);
    }
}
