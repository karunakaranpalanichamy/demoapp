package com.example.demo.controller;

import com.example.demo.constants.AccountsConstants;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.services.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(
   name = "CRUD REST APIs for Accounts in Karuna app",
   description = "CRUD REST APIS in demo app to Create,update,fetch and delete account details"
)
@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class DemoController {

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String mobileNumber) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.fetchAccountDetails(mobileNumber));
    }

    @PutMapping("update")
    public ResponseEntity<Boolean> updateCustomerDetails(@RequestBody CustomerDTO customerDTO)  {
        accountService.updateCustomerDetails(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(true);
    }
}
