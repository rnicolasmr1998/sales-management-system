package com.salesmanagementsystem.sales_management_system.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CustomerRepository extends CrudRepository<Customer, CustomerId>, CustomerRepositoryCustom {
}