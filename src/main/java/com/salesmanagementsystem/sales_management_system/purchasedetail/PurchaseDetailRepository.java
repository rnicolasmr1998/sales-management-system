package com.salesmanagementsystem.sales_management_system.purchasedetail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PurchaseDetailRepository extends CrudRepository<PurchaseDetail, PurchaseDetailId>, PurchaseDetailRepositoryCustom {
}