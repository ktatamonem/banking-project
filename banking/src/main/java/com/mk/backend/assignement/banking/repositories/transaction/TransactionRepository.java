package com.mk.backend.assignement.banking.repositories.transaction;

import com.mk.backend.assignement.banking.entities.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select  t from Transaction t left join t.sourceAccount src left join t.destinationAccount destAccount WHERE destAccount.customer.id = ?1 OR src.customer.id=?1   order by t.creationDate  DESC")
    List<Transaction> retrieveTransactionsByCustomerId(Long customerId);

}
