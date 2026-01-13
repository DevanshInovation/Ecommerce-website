package com.ecm.legacy.model;

import java.util.HashSet;
import java.util.Set;

import com.ecm.legacy.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private String email;
	
	private String fullName;
	
	private String mobile;
	
	private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;
	
	@OneToMany
	private Set<Address> address = new HashSet<>();
	
	@ManyToMany
	@JsonIgnore
	private Set<Coupon> usedCoupon = new HashSet<>(); 
	
}
