package com.mk.backend.assignement.banking.entities.account;

import com.mk.backend.assignement.banking.entities.customer.Customer;
import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bank_account")
@Data
public class Account  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id  ;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_balance")
    private Double  balance ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer ;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate ;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update_date")
    private Date lastUpdateDate ;





}
