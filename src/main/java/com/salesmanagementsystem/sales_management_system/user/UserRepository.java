package com.salesmanagementsystem.sales_management_system.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, UserId>, PagingAndSortingRepository<User, UserId>, UserRepositoryCustom {
}