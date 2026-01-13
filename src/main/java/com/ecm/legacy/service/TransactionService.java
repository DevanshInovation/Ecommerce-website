package com.ecm.legacy.service;

import java.util.List;

import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.Transaction;

public interface TransactionService {

	Transaction createTransaction(Order order);
	List<Transaction> getTransactionBySellerId(Seller seller);
	List<Transaction> getAllTransaction();
}
