package com.salesmanagementsystem.sales_management_system.purchase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PurchaseRepository extends CrudRepository<Purchase, PurchaseId>, PurchaseRepositoryCustom {
}