package com.salesmanagementsystem.sales_management_system.purchase_detail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface purchase_detailRepository extends CrudRepository<purchase_detail, purchase_detailId>, purchase_detailRepositoryCustom {
}