package by.epam.jwd.entity;

import java.io.Serializable;
import java.util.*;

/**
 * Criteria for finding an appliance
 */
public class Criteria implements Serializable{

	private static final long serialVersionUID = 1L;
	private Map<String, String> paramsMap = new HashMap<>();
    private static final List<String> carParam = Arrays.asList();
    private static final List<String> orderParam = Arrays.asList("id", "criteria", "request_date", "depart_place", "start_date",
            "end_date", "order_status", "travel_distance", "total_amount", "payment_status", "client_id", "cars_id", "driver_id");

    public Criteria(String param, String value) {

    }

    public Criteria() {}

    /**
     * @param param desired parameter of appliance
     * @param value value of the desired parameter of appliance
     */
    public void addOrderCriteria(String param, String value) {
        if(orderParam.contains(param)){
            param = param.trim();
            paramsMap.put(param, value);
        }
    }

    public Map<String, String> getParamsList() {
        return paramsMap;
    }
}

