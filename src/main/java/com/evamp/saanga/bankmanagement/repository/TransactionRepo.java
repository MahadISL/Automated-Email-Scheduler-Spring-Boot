package com.evamp.saanga.bankmanagement.repository;

import com.evamp.saanga.bankmanagement.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepo extends CrudRepository<Transaction, Integer > {

    List<Transaction> findAllByObjAccountNoIn(Iterable<Integer> userid);

    List<Transaction> findAll();
    List<Transaction> findAllByTransactionReferenceNumber(Integer trn);
//     findObjAccountNoByTransactionReferenceNumber(Integer trn);
}
