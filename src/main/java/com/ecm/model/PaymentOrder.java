package com.ecm.model;

import java.util.HashSet;
import java.util.Set;

import com.ecm.domain.PaymentMethod;
import com.ecm.domain.PaymentOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
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
public class PaymentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long amount;
	
	private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
	
	private PaymentMethod paymentMethod;
	
	private String paymentLinkId;
	
	@ManyToOne
	private User user;
    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_order_id")
	private Set<Order> orders = new HashSet<Order>();
	
	
}
