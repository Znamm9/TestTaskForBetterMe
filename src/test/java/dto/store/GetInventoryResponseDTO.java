package dto.store;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;


public class GetInventoryResponseDTO {

    private final Map<String, Integer> properties = new HashMap<>();

    @JsonAnySetter
    public void addStatus(String key, Integer value) {
        properties.put(key, value);
    }

    public Map<String, Integer> getProperties() {
        return properties;
    }
}
