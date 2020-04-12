package com.tzy.Service;

import com.tzy.DAO.CustomerDAO;
import com.tzy.DAO.imp.CustomerDAOimp;
import com.tzy.Entity.Customer;

public class CustomerServiceimp implements CustomerService {
    @Override
    public boolean login(Customer customer) throws ServiceException{
        CustomerDAO dao = new CustomerDAOimp();
        Customer verify = dao.findbyID(customer.getId());
        if (verify.getPassword().equals(customer.getPassword())) {
            customer.setPhone(verify.getPhone());
            customer.setAddress(verify.getAddress());
            customer.setName(verify.getName());
            return true;
        }

        return false;


    }

    @Override
    public void register(Customer customer) {
        CustomerDAO imp = new CustomerDAOimp();
        imp.create(customer);

    }
}
