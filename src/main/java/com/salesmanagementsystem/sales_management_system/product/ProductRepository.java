package com.salesmanagementsystem.sales_management_system.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProductRepository extends CrudRepository<Product, ProductId>, ProductRepositoryCustom {
}