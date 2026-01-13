package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
