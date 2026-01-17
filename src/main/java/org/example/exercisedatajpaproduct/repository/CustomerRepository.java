package org.example.exercisedatajpaproduct.repository;

import org.example.exercisedatajpaproduct.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
