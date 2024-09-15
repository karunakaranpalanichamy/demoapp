package com.example.demo.services;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

public interface AccountService {

    /**
     * It creates the customer
     * @param customerDTO - CustomerDTO object
     */
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchAccountDetails(String mobileNumber);

    boolean updateCustomerDetails(CustomerDTO customerDTO);

    boolean deleteAccount(String mobileNumber);
}
