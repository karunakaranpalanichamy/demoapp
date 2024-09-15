package com.example.demo.services.impl;

import com.example.demo.constants.AccountsConstants;
import com.example.demo.dto.AccountsDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Accounts;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;


    /**
     * It creates the customer
     *
     * @param customerDTO - CustomerDTO object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO,new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if(existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer is already existing with the given mobile number..."+customerDTO.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customer));


    }

    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts","customerId",String.valueOf(customer.getCustomerId()))
        );
        CustomerDTO customerDTO =  CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        AccountsDTO  accountsDTO = AccountMapper.mapToAccountsDto(accounts,new AccountsDTO());
        customerDTO.setAccountsDTO(accountsDTO);
        return customerDTO;
    }

    @Override
    public boolean updateCustomerDetails(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDto = customerDTO.getAccountsDTO();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Accounts","Mobile",mobileNumber)
        );
       // CustomerDTO
        return false;
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }



}
