package com.salesmanagementsystem.sales_management_system.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.salesmanagementsystem.sales_management_system.entities.SaleDetail;

@Transactional(readOnly = true)
public interface SaleDetailRepository extends JpaRepository<SaleDetail, UUID>{

}
