package com.mk.backend.assignement.banking.entities.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mk.backend.assignement.banking.entities.account.Account;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Data
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;


    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account sourceAccount ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_account")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Account destinationAccount ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false , updatable = false)
    private Date creationDate  ;


    @Column(name = "in_cash")
    private boolean inCash ;








}
