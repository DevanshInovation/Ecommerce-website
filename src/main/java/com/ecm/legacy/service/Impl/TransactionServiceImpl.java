package com.ecm.legacy.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.SellerReport;
import com.ecm.legacy.model.Transaction;
import com.ecm.legacy.repository.SellerReportRepository;
import com.ecm.legacy.repository.SellerRepository;
import com.ecm.legacy.repository.TransactionRepository;
import com.ecm.legacy.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
	private final SellerRepository sellerRepository;

	@Override
	public Transaction createTransaction(Order order) {

		Seller seller = sellerRepository.findById(order.getSellerId())
		        .orElseThrow(() -> new RuntimeException("Seller not found"));


	    Transaction transaction = new Transaction();
	    transaction.setSeller(seller);
//	    transaction.setCustomer(order.getUserId());
	    transaction.setOrder(order);

	    return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionBySellerId(Seller seller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
}
