package by.epam.jwd.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Criteria for finding an appliance
 */
public class Criteria {

    private Map<String, String> paramsMap = new HashMap<>();

    public Criteria(String param, String value) {
        paramsMap.put(param, value);
    }

    public Criteria() {

    }

    /**
     * @param param desired parameter of appliance
     * @param value value of the desired parameter of appliance
     */
    public void addCriteria(String param, String value) {

        param = param.trim();

        paramsMap.put(param, value);
    }

    public Map<String, String> getParamsList() {
        return paramsMap;
    }
}

