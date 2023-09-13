package com.api.marmitas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.marmitas.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
