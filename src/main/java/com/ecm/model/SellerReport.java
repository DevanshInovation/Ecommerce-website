package com.ecm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SellerReport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 @OneToOne
	    private Seller seller;
	    // Seller entity linked with this report.
	    // Represents which seller this analytics report belongs to.

	    private Long totalEarnings = 0L;
	    // Total revenue earned before deductions (gross earnings).

	    private Long totalSales = 0L;
	    // Total monetary value of all completed orders by seller.

	    private Long totalRefunds = 0L;
	    // Total amount refunded to customers for returned/cancelled orders.

	    private Long totalTax = 0L;
	    // Total tax collected from orders (GST/VAT etc.).

	    private Long netEarnings = 0L;
	    // Earnings after subtracting refunds, taxes, fees from total earnings.

	    private Integer totalOrders = 0;
	    // Total number of orders placed for this seller.

	    private Integer canceledOrders = 0;
	    // Total number of orders cancelled (by buyer or system).

	    private Integer totalTransactions = 0;
	    // Total number of payment transactions (successful + failed).
}
