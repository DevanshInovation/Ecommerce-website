package com.ecm.service;

import java.util.List;

import com.ecm.model.Order;
import com.ecm.model.Seller;
import com.ecm.model.Transaction;

public interface TransactionService {

	Transaction createTransaction(Order order);
	List<Transaction> getTransactionBySellerId(Seller seller);
	List<Transaction> getAllTransaction();
}
