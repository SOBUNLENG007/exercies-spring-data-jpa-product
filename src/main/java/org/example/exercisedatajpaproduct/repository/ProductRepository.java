package org.example.exercisedatajpaproduct.repository;

import org.example.exercisedatajpaproduct.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
