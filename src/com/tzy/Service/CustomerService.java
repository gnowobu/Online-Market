package com.tzy.Service;

import com.tzy.Entity.Customer;

public interface CustomerService {
    public boolean login(Customer customer);

    public void register(Customer customer);
}
