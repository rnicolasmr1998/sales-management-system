package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanagementsystem.sales_management_system.entities.Sale;

@Transactional(readOnly = true)
public interface SaleRepository extends JpaRepository<Sale, UUID>{

}
