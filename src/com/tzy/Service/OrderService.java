package com.tzy.Service;

import java.util.List;
import java.util.Map;

public interface OrderService {// return the id of a order, as the id is randomly generated
    public int submitOrders(List<Map<String, Object>> cart);
}
