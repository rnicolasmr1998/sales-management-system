package com.salesmanagementsystem.sales_management_system.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PaymentRepository extends CrudRepository<Payment, PaymentId>, PaymentRepositoryCustom {
}