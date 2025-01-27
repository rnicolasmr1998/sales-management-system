package com.salesmanagementsystem.sales_management_system.saledetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SaleDetailRepository extends CrudRepository<SaleDetail, SaleDetailId>, SaleDetailRepositoryCustom {
}