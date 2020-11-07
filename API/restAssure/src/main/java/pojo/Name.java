package pojo;

import java.util.HashMap;
import java.util.Map;

public class Name {

    public Name(String name) {
        String Finalname = name;
    }

    public String Finalname;

    public Map<String, Object> getName() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "edge");
        return jsonAsMap;
    }
}
