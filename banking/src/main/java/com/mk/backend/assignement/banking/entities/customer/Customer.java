package com.mk.backend.assignement.banking.entities.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mk.backend.assignement.banking.entities.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bank_customer")
@Data
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id  ;


    @Column(name = "username")
    private String  username ;

    @Column(name = "first_name")
    private String firstName  ;

    @Column(name = "last_name")
    private String lastName  ;

    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth  ;

    @Column(name = "user_address")
    private String address ;

    @Column(name = "password_hash")
    @JsonIgnore
    private String passwordHash  ;

    private String email;

    @Column(name = "mobile_number")
    private Long mobileNumber;

    private Boolean deleted ;

}
