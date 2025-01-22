package com.salesmanagementsystem.sales_management_system.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ClientRepository extends CrudRepository<Client, ClientId>, ClientRepositoryCustom {
}