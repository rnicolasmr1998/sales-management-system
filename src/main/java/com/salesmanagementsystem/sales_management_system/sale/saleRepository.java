package com.salesmanagementsystem.sales_management_system.sale;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SaleRepository extends CrudRepository<Sale, SaleId>, SaleRepositoryCustom {
}