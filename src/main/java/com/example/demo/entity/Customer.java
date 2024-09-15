package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Table(name = "customer")
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    /*
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;
     */
    private String name;

    private String email;
    @Column(name="mobile_number")
    private String mobileNumber;
}
