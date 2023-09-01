package com.api.marmitas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.marmitas.entities.Costumer;

public interface CostumerRepository extends JpaRepository<Costumer, Long>{
    
}
