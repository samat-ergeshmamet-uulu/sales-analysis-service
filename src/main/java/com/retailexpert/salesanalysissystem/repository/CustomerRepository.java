package com.retailexpert.salesanalysissystem.repository;

import com.retailexpert.salesanalysissystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
