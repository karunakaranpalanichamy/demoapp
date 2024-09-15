package com.example.demo.mapper;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO CustomerDTO) {
        CustomerDTO.setName(customer.getName());
        CustomerDTO.setEmail(customer.getEmail());
        CustomerDTO.setMobileNumber(customer.getMobileNumber());
        return CustomerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO CustomerDTO, Customer customer) {
        customer.setName(CustomerDTO.getName());
        customer.setEmail(CustomerDTO.getEmail());
        customer.setMobileNumber(CustomerDTO.getMobileNumber());
        return customer;
    }
}
