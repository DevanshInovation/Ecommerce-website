package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
