package com.salesmanagementsystem.sales_management_system.sale_detail;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface sale_detailRepository extends CrudRepository<sale_detail, sale_detailId>, sale_detailRepositoryCustom {
}