package com.salesmanagementsystem.sales_management_system.supplier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SupplierRepository extends CrudRepository<Supplier, SupplierId>, SupplierRepositoryCustom {
}